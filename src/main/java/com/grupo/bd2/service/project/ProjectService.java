package com.grupo.bd2.service.project;

import com.grupo.bd2.dto.ProjectRequestDto;
import com.grupo.bd2.dto.ProjectResponseDto;
import com.grupo.bd2.model.Project;

import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;

public interface ProjectService {
    List<ProjectResponseDto> getAllProjects();
    ProjectResponseDto getProjectById(Long id);
    ProjectResponseDto createOrUpdateProject(ProjectRequestDto project);
    byte[] exportXls() throws JRException, FileNotFoundException;
    byte[] exportPdf() throws JRException, FileNotFoundException;
}
