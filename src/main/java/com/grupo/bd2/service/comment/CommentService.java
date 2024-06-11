package com.grupo.bd2.service.comment;

import com.grupo.bd2.dto.CommentRequestDto;
import com.grupo.bd2.dto.CommentResponseDto;

import java.util.List;

public interface CommentService {
    List<CommentResponseDto> getAllComments();
    CommentResponseDto getCommentById(String id);
    CommentResponseDto createOrUpdateComment(CommentRequestDto comment);
    List<CommentResponseDto> getCommentsByTaskId(String taskId);
    List<CommentResponseDto> getCommentsByTaskIdAndEmployeeId(String taskId, String employeeId);
}
