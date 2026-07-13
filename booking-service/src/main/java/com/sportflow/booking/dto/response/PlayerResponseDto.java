package com.sportflow.booking.dto.response;

import java.util.UUID;

public record PlayerResponseDto(
        UUID id,
        String name,
        String surname,
        String username,
        String email
) {
}
