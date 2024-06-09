package com.grupo.bd2.service.task;
import com.grupo.bd2.dto.TaskResponseDto;
import com.grupo.bd2.model.Task;

import java.util.List;

public interface TaskService {
    List<TaskResponseDto> getAllTasks();
    TaskResponseDto getTaskById(Long id);
    TaskResponseDto createOrUpdateTask(Task employee);
}
