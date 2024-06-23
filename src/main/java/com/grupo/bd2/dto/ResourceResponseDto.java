package com.grupo.bd2.dto;

import lombok.Builder;

@Builder
public record ResourceResponseDto(
    Long id,
    String description,
    Long taskId,
    Long projectId,
    Long employeeId
)
{}
