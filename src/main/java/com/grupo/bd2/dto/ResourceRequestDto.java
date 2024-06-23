package com.grupo.bd2.dto;

public record ResourceRequestDto (
    String id,
    String description,
    Long taskId,
    Long projectId,
    Long employeeId
) {}
