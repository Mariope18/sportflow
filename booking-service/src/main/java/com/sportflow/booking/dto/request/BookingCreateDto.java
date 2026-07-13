package com.sportflow.booking.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record BookingCreateDto(
        UUID courtId,
        UUID playerId,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime
) {
}
