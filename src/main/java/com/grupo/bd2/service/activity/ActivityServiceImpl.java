package com.grupo.bd2.service.activity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo.bd2.dto.ActivityRequestDto;
import com.grupo.bd2.dto.ActivityResponseDto;
import com.grupo.bd2.exceptions.NotFoundException;
import com.grupo.bd2.model.Activity;
import com.grupo.bd2.repository.ActivityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<ActivityResponseDto> getAllActivities() {
        return activityRepository.findAll().stream()
                .map(activity -> objectMapper.convertValue(activity, ActivityResponseDto.class))
                .toList();
    }

    @Override
    public ActivityResponseDto getActivityById(String id) {
        return activityRepository.findById(id)
                .map(activity -> objectMapper.convertValue(activity, ActivityResponseDto.class))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public ActivityResponseDto createOrUpdateActivity(ActivityRequestDto activity) {
        Activity savedActivity = activityRepository.save(activity);
        return objectMapper.convertValue(savedActivity, ActivityResponseDto.class);
    }
}
