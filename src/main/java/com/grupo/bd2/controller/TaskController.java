package com.grupo.bd2.controller;
import com.grupo.bd2.dto.TaskResponseDto;
import com.grupo.bd2.model.Task;
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
    public ResponseEntity<List<TaskResponseDto>> getAllEmployee(){
        return ResponseEntity.ok().body(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok().body(taskService.getTaskById(id));
    }
    @PostMapping
    public ResponseEntity<TaskResponseDto> saveEmployee(@RequestBody Task task){
        return ResponseEntity.ok().body(taskService.createOrUpdateTask(task));
    }
    @PutMapping
    public ResponseEntity<TaskResponseDto> updateEmployee(@RequestBody Task task){
        return ResponseEntity.ok().body(taskService.createOrUpdateTask(task));
    }
}
