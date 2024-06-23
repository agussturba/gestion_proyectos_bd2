package com.grupo.bd2.controller;
import com.grupo.bd2.dto.TaskRequestDto;
import com.grupo.bd2.dto.TaskResponseDto;
import com.grupo.bd2.service.task.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTasks(){
        return ResponseEntity.ok().body(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id){
        return ResponseEntity.ok().body(taskService.getTaskById(id));
    }
    @PostMapping
    public ResponseEntity<TaskResponseDto> saveTask(@RequestBody TaskRequestDto task){
        return ResponseEntity.ok().body(taskService.createOrUpdateTask(task));
    }
    @PutMapping
    public ResponseEntity<TaskResponseDto> updateTasK(@RequestBody TaskRequestDto task){
        return ResponseEntity.ok().body(taskService.createOrUpdateTask(task));
    }
    @PutMapping("{taskId}/employee/{employeeId}")
    public ResponseEntity<TaskResponseDto> assignEmployee(@PathVariable Long taskId,@PathVariable Long employeeId){
        return ResponseEntity.ok().body(taskService.asignEmployeeToTask(taskId,employeeId));
    }
    @PutMapping("/assign/{taskId}")
    public ResponseEntity<TaskResponseDto> automaticAssignEmployee(@PathVariable Long taskId){
        return ResponseEntity.ok().body(taskService.automaticalyAsignEmployeeToTask(taskId));
    }
}
