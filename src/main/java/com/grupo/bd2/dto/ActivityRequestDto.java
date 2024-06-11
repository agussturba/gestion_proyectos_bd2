package com.grupo.bd2.dto;

import java.time.LocalDate;

public record ActivityRequestDto(
        Long id,
        Long projectId,
        Long taskId,
        Long employeeId,
        String activityType,
        String description,
        LocalDate dateTime
) {
}