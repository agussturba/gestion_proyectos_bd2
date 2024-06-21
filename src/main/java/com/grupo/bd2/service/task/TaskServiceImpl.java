package com.grupo.bd2.service.task;
import com.grupo.bd2.dto.TaskRequestDto;
import com.grupo.bd2.dto.TaskResponseDto;
import com.grupo.bd2.exceptions.BestEmployeeIsAlreadyAssigned;
import com.grupo.bd2.exceptions.EmployeeIsAlreadyAssignedException;
import com.grupo.bd2.exceptions.NotFoundException;
import com.grupo.bd2.model.Employee;
import com.grupo.bd2.model.Project;
import com.grupo.bd2.model.Task;
import com.grupo.bd2.model.TaskState;
import com.grupo.bd2.repository.EmployeeRepository;
import com.grupo.bd2.repository.ProjectRepository;
import com.grupo.bd2.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.grupo.bd2.model.TaskState.ON_GOING;
import static com.grupo.bd2.model.TaskState.TODO;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private static final List<TaskState> TASK_STATE = List.of(TODO,ON_GOING);

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
       Set<Employee> employees = task.employeesId().stream().map(id -> employeeRepository.findById(id).orElseThrow(NotFoundException::new)).collect(Collectors.toSet());
       Task fatherTask = task.fatherTaskId() != null ? taskRepository.findById(task.fatherTaskId()).orElseThrow(NotFoundException::new) : null;
       Task task1 = new Task(task.description(), task.taskState(), fatherTask, employees, LocalDate.now(),task.startDate(),task.endDate(),task.storyPoints(), task.necessarySkills());
       return convertToDto(taskRepository.save(task1));
     }

    @Override
    public TaskResponseDto asignEmployeeToTask(Long taskId,Long employeeId) {
        Task task = taskRepository.findById(taskId).orElseThrow(NotFoundException::new);
        Employee futureAsignedEmployee = employeeRepository.findById(employeeId).orElseThrow(NotFoundException::new);
        if (task.getAssignedEmployees().stream().anyMatch(employee -> employee.equals(futureAsignedEmployee))){
            throw new EmployeeIsAlreadyAssignedException();
        }
        task.addEmployee(futureAsignedEmployee);
        taskRepository.save(task);
        return convertToDto(task);
    }

    @Override
    public TaskResponseDto automaticalyAsignEmployeeToTask(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(NotFoundException::new);
        Project project = projectRepository.findByTask(task);
        List<Employee> availableEmployees = project
                .getEmployees()
                .stream()
                .filter(employee -> taskRepository.findByAssignedEmployeesAndTaskStateIn(employee, TASK_STATE).size() < 3)
                .toList();
        Employee bestEmployee = findBestEmployee(task, availableEmployees);
        task.addEmployee(bestEmployee);
        taskRepository.save(task);
        return convertToDto(task);
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

    private Employee findBestEmployee(Task task, List<Employee> availableEmployees){
        String[] necessarySkills = task.getNecessarySkills().split(",");

        // Variables to keep track of the employee with the most matching skills
        Employee bestEmployee = null;
        int maxMatchingSkills = -1;

        // Iterate over available employees
        for (Employee employee : availableEmployees) {
            List<String> employeeSkills = Arrays.asList(employee.getSkills().split(","));

            int matchingSkillsCount = 0;
            for (String skill : necessarySkills) {
                if (employeeSkills.contains(skill)) {
                    matchingSkillsCount++;
                }
            }
            if (matchingSkillsCount > maxMatchingSkills) {
                maxMatchingSkills = matchingSkillsCount;
                bestEmployee = employee;
            }
        }
        if (bestEmployee == null) {
            throw new BestEmployeeIsAlreadyAssigned();
        }
        return bestEmployee;
    }

}
