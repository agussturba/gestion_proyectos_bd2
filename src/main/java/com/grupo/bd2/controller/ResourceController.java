package com.grupo.bd2.controller;

import com.grupo.bd2.dto.ResourceRequestDto;
import com.grupo.bd2.dto.ResourceResponseDto;
import com.grupo.bd2.service.resource.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/resource")
@AllArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping("/{id}")
    public ResponseEntity<ResourceResponseDto> getResourceById(@PathVariable Long id) {
        ResourceResponseDto resource = resourceService.getResourceById(id);
        return ResponseEntity.ok(resource);
    }

    @PostMapping
    public ResponseEntity<ResourceResponseDto> createResource(@RequestBody ResourceRequestDto resource) {
        ResourceResponseDto newResource = resourceService.createOrUpdateResource(resource);
        return ResponseEntity.ok(newResource);
    }

    @PutMapping
    public ResponseEntity<ResourceResponseDto> updateResource(@RequestBody ResourceRequestDto resource) {
        ResourceResponseDto newResource = resourceService.createOrUpdateResource(resource);
        return ResponseEntity.ok(newResource);
    }
}
