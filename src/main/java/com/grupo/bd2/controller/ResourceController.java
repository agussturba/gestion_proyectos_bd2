package com.grupo.bd2.controller;

import com.grupo.bd2.dto.ResourceRequestDto;
import com.grupo.bd2.dto.ResourceResponseDto;
import com.grupo.bd2.service.resource.ResourceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/resource")
@AllArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @Operation(summary = "Get a Resource by its id")
    @GetMapping("/{id}")
    public ResponseEntity<ResourceResponseDto> getResourceById(@PathVariable String id) {
        ResourceResponseDto resource = resourceService.getResourceById(id);
        return ResponseEntity.ok(resource);
    }
    @Operation(summary = "Create a Resource")
    @PostMapping
    public ResponseEntity<ResourceResponseDto> createResource(@RequestBody ResourceRequestDto resource) {
        ResourceResponseDto newResource = resourceService.createOrUpdateResource(resource);
        return ResponseEntity.ok(newResource);
    }
    @Operation(summary = "Update a Resource")
    @PutMapping
    public ResponseEntity<ResourceResponseDto> updateResource(@RequestBody ResourceRequestDto resource) {
        ResourceResponseDto newResource = resourceService.createOrUpdateResource(resource);
        return ResponseEntity.ok(newResource);
    }
}
