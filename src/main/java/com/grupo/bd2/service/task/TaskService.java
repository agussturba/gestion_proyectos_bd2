package com.grupo.bd2.service.task;
import com.grupo.bd2.dto.TaskRequestDto;
import com.grupo.bd2.dto.TaskResponseDto;

import java.util.List;

public interface TaskService {
    List<TaskResponseDto> getAllTasks();
    TaskResponseDto getTaskById(Long id);
    TaskResponseDto createOrUpdateTask(TaskRequestDto taskRequestDto);
    TaskResponseDto automaticalyAsignEmployeeToTask(Long taskId);
    TaskResponseDto asignEmployeeToTask(Long taskId, Long employeeId);
}
