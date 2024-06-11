package com.grupo.bd2.dto;

import java.time.LocalDate;

public record EmployeeResponseDto(
        Long id,
        String name,
        String skills,
        String availability) {
}
