package com.sportflow.booking.dto.response;

import com.sportflow.booking.model.Address;
import com.sportflow.booking.model.enums.SportType;

import java.math.BigDecimal;
import java.util.UUID;

public record CourtResponseDto(
        UUID id,
        String name,
        SportType sport,
        BigDecimal hourlyRate,
        Address address
) {
}
