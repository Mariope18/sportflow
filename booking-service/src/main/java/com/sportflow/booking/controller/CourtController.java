package com.sportflow.booking.controller;

import com.sportflow.booking.dto.request.CourtCreateDto;
import com.sportflow.booking.dto.response.AvailableSlotDto;
import com.sportflow.booking.dto.response.CourtResponseDto;
import com.sportflow.booking.model.enums.SportType;
import com.sportflow.booking.service.BookingService;
import com.sportflow.booking.service.CourtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/courts")
public class CourtController {

    private final CourtService courtService;
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<CourtResponseDto> createCourt(@RequestBody CourtCreateDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courtService.createCourt(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourtResponseDto> getCourtById(@PathVariable UUID id) {
        return ResponseEntity.ok(courtService.getCourt(id));
    }

    @GetMapping
    public ResponseEntity<List<CourtResponseDto>> getAllCourtBySport(@RequestParam SportType sport) {
        return ResponseEntity.ok(courtService.getAllCourtBySport(sport));
    }

    @GetMapping("/{courtId}/available-slots")
    public ResponseEntity<AvailableSlotDto> getAvailableSlots(@PathVariable UUID courtId, @RequestParam LocalDate date) {
        return ResponseEntity.ok(bookingService.getAllAvailableSlot(courtId, date));
    }
}
