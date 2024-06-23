package com.grupo.bd2.controller;

import com.grupo.bd2.dto.CommentRequestDto;
import com.grupo.bd2.dto.CommentResponseDto;
import com.grupo.bd2.service.comment.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "Create a Comment")
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto comment) {
        CommentResponseDto newComment = commentService.createOrUpdateComment(comment);
        return ResponseEntity.ok(newComment);
    }
    @Operation(summary = "Update a Comment")
    @PutMapping
    public ResponseEntity<CommentResponseDto> updateComment(@RequestBody CommentRequestDto comment) {
        CommentResponseDto newComment = commentService.createOrUpdateComment(comment);
        return ResponseEntity.ok(newComment);
    }
    @Operation(summary = "Get all Comments")
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getAllComments() {
        List<CommentResponseDto> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @Operation(summary = "Get a Comment by its Id")
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> getCommentById(@PathVariable String id) {
        CommentResponseDto comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }
    @Operation(summary = "Get a  list of Comments by its TaskId")
    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<CommentResponseDto>> getCommentsByTaskId(@PathVariable Long taskId) {
        List<CommentResponseDto> comments = commentService.getCommentsByTaskId(taskId);
        return ResponseEntity.ok(comments);
    }
    @Operation(summary = "Get a  list of Comments by its TaskId and EmployeeId")
    @GetMapping("/task/{taskId}/employee/{employeeId}")
    public ResponseEntity<List<CommentResponseDto>> getCommentsByTaskIdAndEmployeeId(@PathVariable Long taskId, @PathVariable Long employeeId) {
        List<CommentResponseDto> comments = commentService.getCommentsByTaskIdAndEmployeeId(taskId, employeeId);
        return ResponseEntity.ok(comments);
    }
}
