package com.grupo.bd2.dto;

public record CommentResponseDto(
    String id,
    String taskId,
    String employeeId,
    String content,
    String createdAt
) {
}
