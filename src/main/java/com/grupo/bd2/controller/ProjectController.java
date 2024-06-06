package com.grupo.bd2.controller;

import com.grupo.bd2.dto.ProjectResponseDto;
import com.grupo.bd2.model.Project;
import com.grupo.bd2.service.project.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects(){
        return ResponseEntity.ok().body(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getAllProjects(@PathVariable Long id){
        return ResponseEntity.ok().body(projectService.getProjectById(id));
    }
    @PostMapping
    public ResponseEntity<ProjectResponseDto> saveProject(@RequestBody Project project){
        return ResponseEntity.ok().body(projectService.createOrUpdateProject(project));
    }
    @PutMapping
    public ResponseEntity<ProjectResponseDto> updateProject(@RequestBody Project project){
        return ResponseEntity.ok().body(projectService.createOrUpdateProject(project));
    }
}
