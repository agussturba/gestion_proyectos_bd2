package com.grupo.bd2.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import com.grupo.bd2.dto.ProjectRequestDto;
import com.grupo.bd2.dto.ProjectResponseDto;
import com.grupo.bd2.service.project.ProjectService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;

import org.springframework.http.ContentDisposition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@AllArgsConstructor
<<<<<<< HEAD
@RequestMapping("/project")
public class    ProjectController {
=======
@RequestMapping("/api/project")
public class ProjectController {
>>>>>>> 865c75a82a9fba9bfb861c7b2590794679d6b575
    private final ProjectService projectService;

    @Operation(summary = "Get all projects")
    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects(){
        return ResponseEntity.ok().body(projectService.getAllProjects());
    }
    @Operation(summary = "Get a project by its Id")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable Long id){
        return ResponseEntity.ok().body(projectService.getProjectById(id));
    }
    @Operation(summary = "Create a Project")
    @PostMapping
    public ResponseEntity<ProjectResponseDto> saveProject(@RequestBody ProjectRequestDto project){
        return ResponseEntity.ok().body(projectService.createOrUpdateProject(project));
    }
    @Operation(summary = "Update a Project")
    @PutMapping
    public ResponseEntity<ProjectResponseDto> updateProject(@RequestBody ProjectRequestDto project){
        return ResponseEntity.ok().body(projectService.createOrUpdateProject(project));
    }
    @Operation(summary = "Get reports of the projects in pdf format")
    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPdf() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("projectsReport", "projectsReport.pdf");
        return ResponseEntity.ok().headers(headers).body(projectService.exportPdf());
    }
    @Operation(summary = "Get reports of the projects in xls format")
    @GetMapping("/export-xls")
    public ResponseEntity<byte[]> exportXls() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        var contentDisposition = ContentDisposition.builder("attachment")
                .filename("projectsReport" + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(projectService.exportXls());
    }

}
