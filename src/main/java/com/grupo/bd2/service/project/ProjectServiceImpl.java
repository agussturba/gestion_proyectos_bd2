package com.grupo.bd2.service.project;

import com.grupo.bd2.util.projectReportGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo.bd2.dto.ProjectResponseDto;
import com.grupo.bd2.exceptions.NotFoundException;
import com.grupo.bd2.model.Project;
import com.grupo.bd2.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService{
    private final ProjectRepository projectRepository;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<ProjectResponseDto> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(project -> objectMapper.convertValue(project, ProjectResponseDto.class))
                .toList();
    }

    @Override
    public ProjectResponseDto getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(project -> objectMapper.convertValue(project,ProjectResponseDto.class))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public ProjectResponseDto createOrUpdateProject(Project project) {
        Project savedProject = projectRepository.save(project);
        return objectMapper.convertValue(savedProject, ProjectResponseDto.class);
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
