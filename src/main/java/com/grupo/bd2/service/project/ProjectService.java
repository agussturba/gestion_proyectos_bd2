package com.grupo.bd2.service.project;

import com.grupo.bd2.dto.ProjectRequestDto;
import com.grupo.bd2.dto.ProjectResponseDto;
import com.grupo.bd2.model.Project;

import java.util.List;

public interface ProjectService {
    List<ProjectResponseDto> getAllProjects();
    ProjectResponseDto getProjectById(Long id);
    ProjectResponseDto createOrUpdateProject(ProjectRequestDto project);
}
