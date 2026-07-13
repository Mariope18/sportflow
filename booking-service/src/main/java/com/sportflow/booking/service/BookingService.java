package com.sportflow.booking.service;

import com.sportflow.booking.dto.request.BookingCreateDto;
import com.sportflow.booking.dto.response.AvailableSlotDto;
import com.sportflow.booking.dto.response.BookingResponseDto;
import com.sportflow.booking.dto.response.CourtResponseDto;
import com.sportflow.booking.mapper.BookingMapper;
import com.sportflow.booking.model.Booking;
import com.sportflow.booking.model.enums.BookingStatus;
import com.sportflow.booking.repository.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BookingService {

    private final CourtService courtService;
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final PlayerService playerService;

    public AvailableSlotDto getAllAvailableSlot(UUID courtId, LocalDate date) {

        CourtResponseDto courtResponseDto = courtService.getCourt(courtId);

        List<Booking> confirmedBooking =
                bookingRepository.findAllByCourtIdAndDateAndStatus(courtId, date, BookingStatus.CONFIRMED);

        List<LocalTime> generatedSlots = this.getGeneratedSlots();

        Set<LocalTime> confirmedSlots = confirmedBooking.stream().map(Booking::getStartTime)
                .collect(Collectors.toSet());

        List<LocalTime> availableSlots = generatedSlots
                .stream()
                .filter(localTime -> !confirmedSlots.contains(localTime))
                .toList();

        return new AvailableSlotDto(courtResponseDto, date, availableSlots);
    }

    private List<LocalTime> getGeneratedSlots() {

        LocalTime orarioApertura = LocalTime.of(9, 0);
        LocalTime orarioChiusura = LocalTime.of(22, 0);

        return Stream.iterate(
                orarioApertura,
                t -> !t.isAfter(orarioChiusura),
                t -> t.plusHours(1)
        ).toList();
    }

    @Transactional
    public BookingResponseDto createBooking(BookingCreateDto request) {

        courtService.getCourt(request.courtId());

        playerService.getPlayer(request.playerId());

        List<LocalTime> availableSlots = this.getAllAvailableSlot(request.courtId(), request.date())
                .availableStartTimes();

        if (!availableSlots.contains(request.startTime())) {
            throw new IllegalArgumentException(String.format("Slot (%s - %s) non disponibile", request.startTime(), request.endTime()));
        }

        Booking booking = bookingMapper.toEntity(request);

        booking.setStatus(BookingStatus.CONFIRMED);

        Booking savedBooking = bookingRepository.save(booking);

        return bookingMapper.toResponse(savedBooking);
    }

    @Transactional
    public void cancelBooking(UUID bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Booking con id %s non trovato", bookingId)));

        booking.setStatus(BookingStatus.CANCELLED);
    }
}
