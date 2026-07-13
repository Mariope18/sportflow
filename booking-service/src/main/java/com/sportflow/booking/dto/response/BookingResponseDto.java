package com.sportflow.booking.dto.response;

import com.sportflow.booking.model.enums.BookingStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record BookingResponseDto(

        UUID id,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        BookingStatus status,
        CourtResponseDto court,
        PlayerResponseDto player
) {
}
