package com.grupo.bd2.service.project;

import com.grupo.bd2.model.Employee;
import com.grupo.bd2.repository.EmployeeRepository;
import com.grupo.bd2.util.projectReportGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo.bd2.dto.ProjectRequestDto;
import com.grupo.bd2.dto.ProjectResponseDto;
import com.grupo.bd2.exceptions.NotFoundException;
import com.grupo.bd2.model.Project;
import com.grupo.bd2.model.Task;
import com.grupo.bd2.repository.ProjectRepository;
import com.grupo.bd2.repository.TaskRepository;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;


@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService{
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<ProjectResponseDto> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public ProjectResponseDto getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new NotFoundException("Project not found"));
    }

    @Override
    public ProjectResponseDto createOrUpdateProject(ProjectRequestDto project) {
        List<Task> tasks = project.taskIds().stream().map(taskId -> taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException("Task not Found"))).toList();
        List<Employee> employees = project.employeesIds().stream().map(employeeId -> employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException("Employee not found"))).toList();
        if (project.id() == null && !employees.stream().filter(employee -> projectRepository.findByEmployeesContainingAndIsActive(employee,true).isPresent()).toList().isEmpty()){
            throw new NotFoundException("Employee is already in a active project");
        }
        Project savedProject = new Project(project.name(), project.description(), LocalDate.now(), project.endDate(), project.isActive(), tasks,employees);
        if (project.id() != null){
            savedProject.setId(project.id());
        }
        return convertToDto(projectRepository.save(savedProject));
    }
    private ProjectResponseDto convertToDto(Project project) {
        return ProjectResponseDto
                .builder()
                .description(project.getDescription())
                .endDate(project.getEndDate())
                .id(project.getId())
                .isActive(project.getIsActive())
                .name(project.getName())
                .startDate(project.getStartDate())
                .employeesIds(project.getEmployees().stream().map(Employee::getId).toList())
                .tasksIds(project.getTask().stream().map(Task::getId).toList())
                .build();
    }

    @Override
    public byte[] exportPdf() throws JRException, FileNotFoundException {
        projectReportGenerator reportGenerator = new projectReportGenerator();
        return reportGenerator.exportToPdf(projectRepository.findAll());
    }

    @Override
    public byte[] exportXls() throws JRException, FileNotFoundException {
    projectReportGenerator reportGenerator = new projectReportGenerator();
    return reportGenerator.exportToXls(projectRepository.findAll());
}
}
