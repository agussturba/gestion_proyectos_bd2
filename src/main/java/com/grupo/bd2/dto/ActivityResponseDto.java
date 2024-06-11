package com.grupo.bd2.dto;

import java.time.LocalDate;

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
