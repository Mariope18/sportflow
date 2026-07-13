package com.sportflow.booking.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record AvailableSlotDto(
        CourtResponseDto court,
        LocalDate date,
        List<LocalTime> availableStartTimes
) {
}
