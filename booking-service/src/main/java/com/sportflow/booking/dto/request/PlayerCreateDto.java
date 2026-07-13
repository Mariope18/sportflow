package com.sportflow.booking.dto.request;

public record PlayerCreateDto(
        String name,
        String surname,
        String username,
        String email
) {
}
