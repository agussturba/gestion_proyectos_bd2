package com.grupo.bd2.dto;

import java.time.LocalDate;

public record ProjectResponseDto(
        Long id,
        String name,
        String description,
        String status,
        LocalDate startDate,
        LocalDate endDate,
        LocalDate createdAt,
        LocalDate updatedAt
) {
}
