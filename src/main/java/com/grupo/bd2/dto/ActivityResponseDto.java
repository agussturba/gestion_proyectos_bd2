package com.grupo.bd2.dto;

import lombok.Builder;

import java.time.LocalDate;
@Builder
public record ActivityResponseDto(
        Long id,
        Long projectId,
        Long taskId,
        Long employeeId,
        String activityType,
        String description,
        LocalDate dateTime
) {
}
