package com.grupo.bd2.service.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo.bd2.dto.CommentResponseDto;
import com.grupo.bd2.exceptions.NotFoundException;
import com.grupo.bd2.model.Comment;
import com.grupo.bd2.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<CommentResponseDto> getAllComments() {
        return commentRepository.findAll().stream()
                .map(comment -> objectMapper.convertValue(comment, CommentResponseDto.class))
                .toList();
    }

    @Override
    public CommentResponseDto getCommentById(String id) {
        return commentRepository.findById(id)
                .map(comment -> objectMapper.convertValue(comment, CommentResponseDto.class))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public CommentResponseDto createOrUpdateComment(Comment comment) {
        Comment savedComment = commentRepository.save(comment);
        return objectMapper.convertValue(savedComment, CommentResponseDto.class);
    }
    @Override
    public List<CommentResponseDto> getCommentsByTaskId(String taskId) {
        return commentRepository.findByTaskId(taskId).stream()
                .map(comment -> objectMapper.convertValue(comment, CommentResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentResponseDto> getCommentsByTaskIdAndEmployeeId(String taskId, String employeeId) {
        return commentRepository.findByTaskIdAndEmployeeId(taskId, employeeId).stream()
                .map(comment -> objectMapper.convertValue(comment, CommentResponseDto.class))
                .collect(Collectors.toList());
    }
}
