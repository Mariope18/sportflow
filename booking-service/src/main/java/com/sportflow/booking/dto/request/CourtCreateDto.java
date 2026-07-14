package com.sportflow.booking.dto.request;

import com.sportflow.booking.model.Address;
import com.sportflow.booking.model.enums.SportType;

import java.math.BigDecimal;
import java.time.LocalTime;

public record CourtCreateDto(
        String name,
        SportType sport,
        BigDecimal hourlyRate,
        Address address,
        LocalTime openingTime,
        LocalTime closingTime,
        Integer slotDurationMinutes
) {
}
