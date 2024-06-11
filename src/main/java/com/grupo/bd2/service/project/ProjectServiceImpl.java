package com.grupo.bd2.service.project;

import com.grupo.bd2.dto.ProjectRequestDto;
import com.grupo.bd2.dto.ProjectResponseDto;
import com.grupo.bd2.exceptions.NotFoundException;
import com.grupo.bd2.model.Project;
import com.grupo.bd2.model.Task;
import com.grupo.bd2.repository.ProjectRepository;
import com.grupo.bd2.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService{
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

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
        Project project1 = new Project(project.name(), project.description(), project.startDate(), project.endDate(), project.isActive(), tasks);
        return convertToDto(projectRepository.save(project1));
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
}
