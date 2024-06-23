package com.grupo.bd2.service.comment;

import com.grupo.bd2.dto.CommentRequestDto;
import com.grupo.bd2.dto.CommentResponseDto;
import com.grupo.bd2.exceptions.NotFoundException;
import com.grupo.bd2.model.Comment;

import com.grupo.bd2.model.Employee;
import com.grupo.bd2.model.Task;
import com.grupo.bd2.repository.CommentRepository;
import com.grupo.bd2.repository.EmployeeRepository;
import com.grupo.bd2.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
                .orElseThrow(() -> new NotFoundException("Comment not found"));
    }

    @Override
    public CommentResponseDto createOrUpdateComment(CommentRequestDto comment) {
        Task task = null;
        Employee employee = null;
        if(comment.taskId() != null){
            task = taskRepository.findById(comment.taskId()).orElseThrow(() -> new NotFoundException("Task not found"));
        }
        if(comment.employeeId() != null){
            employee = employeeRepository.findById(comment.employeeId()).orElseThrow(() -> new NotFoundException("Employee not found"));
        }
        Comment comment1 = new Comment(task, employee, comment.content(), LocalDate.now());
        if(comment.id() != null){
            commentRepository.findById(comment.id()).orElseThrow(() -> new NotFoundException("Comment not found"));
            comment1.setId(comment.id());
        }
        return convertToDto(commentRepository.save(comment1));
    }
    @Override
    public List<CommentResponseDto> getCommentsByTaskId(Long taskId) {
        return commentRepository.findByTaskId(taskId).stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public List<CommentResponseDto> getCommentsByTaskIdAndEmployeeId(Long taskId, Long employeeId) {
        return commentRepository.findByTaskIdAndEmployeeId(taskId, employeeId).stream()
                .map(this::convertToDto)
                .toList();
    }
    private CommentResponseDto convertToDto(Comment comment) {
        return CommentResponseDto
                .builder()
                .id(comment.getId())
                .content(comment.getComment())
                .employeeId(comment.getEmployee().getId())
                .taskId(comment.getTask().getId())
                .createdAt(comment.getDate())
                .build();
    }
}
