package com.grupo.bd2.dto;

import java.time.LocalDate;

public record ActivityRequestDto(
        String id,
        Long projectId,
        Long taskId,
        Long employeeId,
        String description,
        LocalDate dateTime
) {
}