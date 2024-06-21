package com.grupo.bd2.controller;
import com.grupo.bd2.dto.TaskProgressResponseDto;
import com.grupo.bd2.service.progress.TaskProgressService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/progress")
@AllArgsConstructor
public class TaskProgressController {

    private final TaskProgressService taskProgressService;

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskProgressResponseDto> getProgress(@PathVariable Long taskId) {
        return ResponseEntity.ok().body(taskProgressService.getProgressById(taskId));
    }


}
