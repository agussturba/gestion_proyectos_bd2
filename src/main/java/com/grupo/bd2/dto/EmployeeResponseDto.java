package com.grupo.bd2.dto;

public record EmployeeResponseDto(
        Long id,
        String name,
        String skills,
        String availability
) {
}
