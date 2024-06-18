package com.grupo.bd2.service.task;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo.bd2.dto.EmployeeResponseDto;
import com.grupo.bd2.dto.TaskRequestDto;
import com.grupo.bd2.dto.TaskResponseDto;
import com.grupo.bd2.exceptions.NotFoundException;
import com.grupo.bd2.model.Employee;
import com.grupo.bd2.model.Task;
import com.grupo.bd2.repository.EmployeeRepository;
import com.grupo.bd2.repository.TaskRepository;
import com.grupo.bd2.service.employee.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<TaskResponseDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();}
    @Override
    public TaskResponseDto getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(NotFoundException::new);}

    @Override
    public TaskResponseDto createOrUpdateTask(TaskRequestDto task) {
       List<Employee> employees = task.employeesId().stream().map(id -> employeeRepository.findById(id).orElseThrow(NotFoundException::new)).toList();
       Task fatherTask = task.fatherTaskId() != null ? taskRepository.findById(task.fatherTaskId()).orElseThrow(NotFoundException::new) : null;
       Task task1 = new Task(task.description(), task.taskState(), fatherTask, employees, LocalDate.now(),task.startDate(),task.endDate(),task.storyPoints());
       return convertToDto(taskRepository.save(task1));
     }

    private TaskResponseDto convertToDto(Task task) {
        return TaskResponseDto
                .builder()
                .assignedEmployees(task.getAssignedEmployees().stream().map(Employee::getId).toList())
                .description(task.getDescription())
                .fatherTask(task.getFatherTask() != null ? task.getFatherTask().getId() : null)
                .taskState(task.getTaskState())
                .storyPoints(task.getStoryPoints())
                .id(task.getId())
                .build();
    }
}
