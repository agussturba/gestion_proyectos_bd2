package com.grupo.bd2.dto;

import java.time.LocalDate;

public record EmployeeResponseDto(
        Long id,
        String name,
        String email,
        String phone,
        LocalDate createdAt,
        LocalDate updatedAt
) {
}
