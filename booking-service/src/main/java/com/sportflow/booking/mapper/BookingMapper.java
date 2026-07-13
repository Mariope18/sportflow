package com.sportflow.booking.mapper;

import com.sportflow.booking.dto.request.BookingCreateDto;
import com.sportflow.booking.dto.response.BookingResponseDto;
import com.sportflow.booking.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingResponseDto toResponse(Booking booking);

    @Mapping(target = "court.id", source = "courtId")
    @Mapping(target = "player.id", source = "playerId")
    Booking toEntity(BookingCreateDto request);
}
