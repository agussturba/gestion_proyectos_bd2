package com.grupo.bd2.dto;

import lombok.Builder;

@Builder
public record TaskProgressResponseDto(
        Long id,
        Double timeWorked,
        Double percentageCompleted
) {}
