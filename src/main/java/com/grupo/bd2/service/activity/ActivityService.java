package com.grupo.bd2.service.activity;

import com.grupo.bd2.dto.ActivityResponseDto;
import com.grupo.bd2.model.Activity;

import java.util.List;

public interface ActivityService {
    List<ActivityResponseDto> getAllActivities();
    ActivityResponseDto getActivityById(String id);
    ActivityResponseDto createOrUpdateActivity(Activity activity);
}
