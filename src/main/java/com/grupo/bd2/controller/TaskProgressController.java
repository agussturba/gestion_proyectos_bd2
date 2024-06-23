package com.grupo.bd2.controller;
import com.grupo.bd2.dto.TaskProgressResponseDto;
import com.grupo.bd2.service.progress.TaskProgressService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/progress")
@AllArgsConstructor
public class TaskProgressController {

    private final TaskProgressService taskProgressService;

    @Operation(summary = "Get Progress of a task")
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskProgressResponseDto> getProgress(@PathVariable Long taskId) {
        return ResponseEntity.ok().body(taskProgressService.getProgressById(taskId));
    }


}
