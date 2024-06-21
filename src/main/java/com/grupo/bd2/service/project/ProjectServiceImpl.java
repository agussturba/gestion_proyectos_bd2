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

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

import static java.util.Objects.isNull;

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
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public ProjectResponseDto createOrUpdateProject(ProjectRequestDto project) {
        List<Task> tasks = project.taskIds().stream().map(taskId -> taskRepository.findById(taskId).orElseThrow(NotFoundException::new)).toList();
        List<Employee> employees = project.employeesIds().stream().map(employeeId -> employeeRepository.findById(employeeId).orElseThrow(NotFoundException::new)).toList();
        Project savedProject = new Project(project.name(), project.description(), project.startDate(), project.endDate(), project.isActive(), tasks,employees);
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
