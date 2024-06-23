package com.grupo.bd2.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import com.grupo.bd2.dto.ProjectRequestDto;
import com.grupo.bd2.dto.ProjectResponseDto;
import com.grupo.bd2.model.Project;
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
@RequestMapping("/project")
public class    ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects(){
        return ResponseEntity.ok().body(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable Long id){
        return ResponseEntity.ok().body(projectService.getProjectById(id));
    }
    @PostMapping
    public ResponseEntity<ProjectResponseDto> saveProject(@RequestBody ProjectRequestDto project){
        return ResponseEntity.ok().body(projectService.createOrUpdateProject(project));
    }
    @PutMapping
    public ResponseEntity<ProjectResponseDto> updateProject(@RequestBody ProjectRequestDto project){
        return ResponseEntity.ok().body(projectService.createOrUpdateProject(project));
    }

    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPdf() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("projectsReport", "projectsReport.pdf");
        return ResponseEntity.ok().headers(headers).body(projectService.exportPdf());
    }

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
