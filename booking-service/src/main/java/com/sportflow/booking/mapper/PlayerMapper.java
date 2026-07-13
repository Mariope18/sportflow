package com.sportflow.booking.mapper;

import com.sportflow.booking.dto.request.PlayerCreateDto;
import com.sportflow.booking.dto.response.PlayerResponseDto;
import com.sportflow.booking.model.Player;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    Player toEntity(PlayerCreateDto request);

    PlayerResponseDto toResponse(Player player);
}
