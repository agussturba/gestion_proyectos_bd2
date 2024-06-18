package com.grupo.bd2.dto;

import lombok.Builder;

import java.time.LocalDate;
@Builder
public record CommentResponseDto(
    Long id,
    Long taskId,
    Long employeeId,
    String content,
    LocalDate createdAt
) {
}
