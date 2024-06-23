package com.grupo.bd2.dto;

import lombok.Builder;

@Builder
public record ResourceResponseDto(
    String id,
    String description,
    Long taskId,
    Long projectId,
    Long employeeId
)
{}
