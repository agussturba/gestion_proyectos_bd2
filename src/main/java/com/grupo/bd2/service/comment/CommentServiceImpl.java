package com.grupo.bd2.service.comment;

import com.grupo.bd2.dto.CommentRequestDto;
import com.grupo.bd2.dto.CommentResponseDto;
import com.grupo.bd2.exceptions.NotFoundException;
import com.grupo.bd2.model.Comment;

import com.grupo.bd2.repository.CommentRepository;
import com.grupo.bd2.repository.EmployeeRepository;
import com.grupo.bd2.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<CommentResponseDto> getAllComments() {
        return commentRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public CommentResponseDto getCommentById(String id) {
        return commentRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public CommentResponseDto createOrUpdateComment(CommentRequestDto comment) {
        var task =taskRepository.findById(comment.taskId()).orElseThrow(NotFoundException::new);
        var employee = employeeRepository.findById(comment.employeeId()).orElseThrow(NotFoundException::new);
        Comment comment1 = new Comment(task, employee, comment.content(), comment.createdAt());
        return convertToDto(commentRepository.save(comment1));
    }
    @Override
    public List<CommentResponseDto> getCommentsByTaskId(String taskId) {
        return commentRepository.findByTaskId(taskId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentResponseDto> getCommentsByTaskIdAndEmployeeId(String taskId, String employeeId) {
        return commentRepository.findByTaskIdAndEmployeeId(taskId, employeeId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private CommentResponseDto convertToDto(Comment comment) {
        return CommentResponseDto
                .builder()
                .content(comment.getComment())
                .employeeId(comment.getEmployee().getId())
                .taskId(comment.getTask().getId())
                .build();
    }
}
