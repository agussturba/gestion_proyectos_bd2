package com.grupo.bd2.controller;
import com.grupo.bd2.dto.TaskRequestDto;
import com.grupo.bd2.dto.TaskResponseDto;
import com.grupo.bd2.service.task.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService taskService;
    @Operation(summary = "Get all tasks")
    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTasks(){
        return ResponseEntity.ok().body(taskService.getAllTasks());
    }
    @Operation(summary = "Get a task by its Id")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id){
        return ResponseEntity.ok().body(taskService.getTaskById(id));
    }
    @Operation(summary = "Create a task")
    @PostMapping
    public ResponseEntity<TaskResponseDto> saveTask(@RequestBody TaskRequestDto task){
        return ResponseEntity.ok().body(taskService.createOrUpdateTask(task));
    }
    @Operation(summary = "Update a task")
    @PutMapping
    public ResponseEntity<TaskResponseDto> updateTasK(@RequestBody TaskRequestDto task){
        return ResponseEntity.ok().body(taskService.createOrUpdateTask(task));
    }
    @Operation(summary = "Assign a employee to a task")
    @PutMapping("{taskId}/employee/{employeeId}")
    public ResponseEntity<TaskResponseDto> assignEmployee(@PathVariable Long taskId,@PathVariable Long employeeId){
        return ResponseEntity.ok().body(taskService.asignEmployeeToTask(taskId,employeeId));
    }
    @Operation(summary = "Automatically assign a employee to a task")
    @PutMapping("/assign/{taskId}")
    public ResponseEntity<TaskResponseDto> automaticAssignEmployee(@PathVariable Long taskId){
        return ResponseEntity.ok().body(taskService.automaticalyAsignEmployeeToTask(taskId));
    }
}
