package com.grupo.bd2.controller;

import com.grupo.bd2.dto.CommentResponseDto;
import com.grupo.bd2.model.Comment;
import com.grupo.bd2.service.comment.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody Comment comment) {
        CommentResponseDto newComment = commentService.createOrUpdateComment(comment);
        return ResponseEntity.ok(newComment);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getAllComments() {
        List<CommentResponseDto> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> getCommentById(@PathVariable String id) {
        CommentResponseDto comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }
}
