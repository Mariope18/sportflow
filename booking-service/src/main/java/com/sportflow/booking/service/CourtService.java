package com.sportflow.booking.service;

import com.sportflow.booking.dto.request.CourtCreateDto;
import com.sportflow.booking.dto.response.CourtResponseDto;
import com.sportflow.booking.mapper.CourtMapper;
import com.sportflow.booking.model.Court;
import com.sportflow.booking.model.enums.SportType;
import com.sportflow.booking.repository.CourtRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CourtService {

    private final CourtRepository courtRepository;
    private final CourtMapper courtMapper;

    @Transactional
    public CourtResponseDto createCourt(CourtCreateDto request) {

        Court court = courtMapper.toEntity(request);

        Court savedCourt = courtRepository.save(court);

        return courtMapper.toResponse(savedCourt);
    }

    public CourtResponseDto getCourt(UUID id) {

        Court court = courtRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Court con id %s non trovato", id)));

        return courtMapper.toResponse(court);
    }

    public List<CourtResponseDto> getAllCourtBySport(SportType sport) {

        return courtRepository.findAllBySport(sport)
                .stream()
                .map(courtMapper::toResponse)
                .toList();
    }
}
