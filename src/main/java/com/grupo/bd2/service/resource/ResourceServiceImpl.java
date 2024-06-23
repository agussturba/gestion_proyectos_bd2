package com.grupo.bd2.service.resource;

import com.grupo.bd2.model.Employee;
import com.grupo.bd2.model.Project;
import com.grupo.bd2.model.Resource;
import com.grupo.bd2.model.Task;

import com.grupo.bd2.dto.ResourceRequestDto;
import com.grupo.bd2.dto.ResourceResponseDto;

import com.grupo.bd2.exceptions.NotFoundException;

import com.grupo.bd2.repository.EmployeeRepository;
import com.grupo.bd2.repository.ProjectRepository;
import com.grupo.bd2.repository.ResourceRepository;
import com.grupo.bd2.repository.TaskRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ResourceServiceImpl implements ResourceService{
    private final ResourceRepository resourceRepository;
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public ResourceResponseDto getResourceById(String id) {
        return resourceRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(()-> new NotFoundException("Resource not found"));
    }

    @Override
    public ResourceResponseDto createOrUpdateResource(ResourceRequestDto resource) {
        Task task = null;
        Project project = null;
        if (resource.taskId() != null){
            task = taskRepository.findById(resource.taskId()).orElseThrow(()-> new NotFoundException("Task not found"));
        }
        if (resource.projectId() != null){
            project = projectRepository.findById(resource.projectId()).orElseThrow(()-> new NotFoundException("Project not found"));
        }

        Employee employee = employeeRepository.findById(resource.employeeId()).orElseThrow(()-> new NotFoundException("Employee not found"));
        Resource resource1 = new Resource(resource.description(), task, project, employee);
        if(resource.id() != null){
            resourceRepository.findById(resource.id()).orElseThrow(()-> new NotFoundException("Resource not found"));
            resource1.setId(resource.id());
        }
        return convertToDto(resourceRepository.save(resource1));
    }

    private ResourceResponseDto convertToDto(Resource resource) {
        Task task = resource.getTask();
        Project project = resource.getProject();
        Employee employee = resource.getEmployee();

        return ResourceResponseDto
                .builder()
                .id(resource.getId())
                .description(resource.getDescription())
                .taskId(task == null ? null : task.getId())
                .projectId(project == null ? null : project.getId())
                .employeeId(employee == null ? null : employee.getId())
                .build();
    }
}
