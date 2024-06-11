package com.grupo.bd2.dto;

import java.time.LocalDate;

public record CommentResponseDto(
    Long id,
    Long taskId,
    Long employeeId,
    String content,
    LocalDate createdAt
) {
}
