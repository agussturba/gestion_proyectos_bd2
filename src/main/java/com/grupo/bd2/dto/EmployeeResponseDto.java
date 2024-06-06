package com.grupo.bd2.dto;

public record EmployeeResponseDto(
        Long id,
        String name,
        String email,
        String phone,
        String createdAt,
        String updatedAt
) {
}
