package com.sportflow.booking.mapper;

import com.sportflow.booking.dto.request.CourtCreateDto;
import com.sportflow.booking.dto.response.CourtResponseDto;
import com.sportflow.booking.model.Court;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourtMapper {

    Court toEntity(CourtCreateDto request);

    CourtResponseDto toResponse(Court court);

}
