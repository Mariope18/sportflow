package com.sportflow.booking.service;

import com.sportflow.booking.dto.request.PlayerCreateDto;
import com.sportflow.booking.dto.response.PlayerResponseDto;
import com.sportflow.booking.mapper.PlayerMapper;
import com.sportflow.booking.model.Player;
import com.sportflow.booking.repository.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    @Transactional
    public PlayerResponseDto createPlayer(PlayerCreateDto request) {

        Player player = playerMapper.toEntity(request);

        Player savedPlayer = playerRepository.save(player);

        return playerMapper.toResponse(savedPlayer);
    }


    public PlayerResponseDto getPlayer(UUID id) {

        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Player con id %s non trovato", id)));

        return playerMapper.toResponse(player);
    }
}
