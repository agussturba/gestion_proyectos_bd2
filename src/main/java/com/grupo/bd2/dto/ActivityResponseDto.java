package com.grupo.bd2.dto;

public record ActivityResponseDto(
        String id,
        String projectId,
        String taskId,
        String employeeId,
        String activityType,
        String description,
        String dateTime
) {
}
