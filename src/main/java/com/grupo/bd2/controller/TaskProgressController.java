package com.grupo.bd2.controller;
import com.grupo.bd2.dto.TaskProgressRequestDto;
import com.grupo.bd2.dto.TaskProgressResponseDto;
import com.grupo.bd2.service.progress.TaskProgressService;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/progress")
@AllArgsConstructor
public class TaskProgressController {

    private final TaskProgressService taskProgressService;



    @PostMapping("/{taskId}")
    public ResponseEntity<TaskProgressResponseDto> createProgress(@PathVariable Long taskId) {
        return ResponseEntity.ok().body(taskProgressService.getProgressById(taskId));
    }


}
