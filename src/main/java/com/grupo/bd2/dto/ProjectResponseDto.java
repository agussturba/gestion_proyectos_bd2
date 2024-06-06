package com.grupo.bd2.dto;

public record ProjectResponseDto(
        Long id,
        String name,
        String description,
        String status,
        String startDate,
        String endDate,
        String createdAt,
        String updatedAt
) {
}
