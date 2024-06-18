package com.grupo.bd2.service.activity;

import com.grupo.bd2.dto.ActivityRequestDto;
import com.grupo.bd2.dto.ActivityResponseDto;
import com.grupo.bd2.exceptions.NotFoundException;
import com.grupo.bd2.model.Activity;
import com.grupo.bd2.model.Employee;
import com.grupo.bd2.model.Project;
import com.grupo.bd2.model.Task;
import com.grupo.bd2.repository.ActivityRepository;
import com.grupo.bd2.repository.EmployeeRepository;
import com.grupo.bd2.repository.ProjectRepository;
import com.grupo.bd2.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<ActivityResponseDto> getAllActivities() {
        return activityRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public ActivityResponseDto getActivityById(String id) {
        return activityRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public ActivityResponseDto createOrUpdateActivity(ActivityRequestDto activity) {
        Task task = taskRepository.findById(activity.taskId()).orElseThrow(NotFoundException::new);
        Project project = projectRepository.findById(activity.projectId()).orElseThrow(NotFoundException::new);
        Employee employee = employeeRepository.findById(activity.employeeId()).orElseThrow(NotFoundException::new);
        Activity activity1 = new Activity(employee, project, task, activity.description());
        return convertToDto(activityRepository.save(activity1));
    }
    private ActivityResponseDto convertToDto(Activity activity) {
        return ActivityResponseDto
                .builder()
                .description(activity.getDescription())
                .id(activity.getId())
                .build();
    }
}
