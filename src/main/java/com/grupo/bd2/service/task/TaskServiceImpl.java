package com.grupo.bd2.service.task;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo.bd2.dto.EmployeeResponseDto;
import com.grupo.bd2.dto.TaskResponseDto;
import com.grupo.bd2.exceptions.NotFoundException;
import com.grupo.bd2.model.Employee;
import com.grupo.bd2.model.Task;
import com.grupo.bd2.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public List<TaskResponseDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(project -> objectMapper.convertValue(project, TaskResponseDto.class))
                .toList();}
    @Override
    public TaskResponseDto getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(project -> objectMapper.convertValue(project,TaskResponseDto.class))
                .orElseThrow(NotFoundException::new);}
    @Override
    public TaskResponseDto createOrUpdateTask(Task task) {
        Task savedTask = taskRepository.save(task);
        return objectMapper.convertValue(savedTask, TaskResponseDto.class);}
}
