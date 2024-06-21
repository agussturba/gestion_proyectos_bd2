package com.grupo.bd2.dto;

import lombok.Builder;

import java.time.LocalDate;
@Builder
public record ActivityResponseDto(
        String id,
        Long projectId,
        Long taskId,
        Long employeeId,
        String description,
        LocalDate dateTime
) {
}
