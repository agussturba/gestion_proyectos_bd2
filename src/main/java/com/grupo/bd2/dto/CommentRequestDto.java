package com.grupo.bd2.dto;

import java.time.LocalDate;

public record CommentRequestDto(
    String id,
    Long taskId,
    Long employeeId,
    String content,
    LocalDate createdAt
) {
}
