package com.grupo.bd2.controller;

import com.grupo.bd2.dto.ActivityResponseDto;
import com.grupo.bd2.model.Activity;
import com.grupo.bd2.service.activity.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@AllArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponseDto> createActivity(@RequestBody Activity activity) {
        ActivityResponseDto newActivity = activityService.createOrUpdateActivity(activity);
        return ResponseEntity.ok(newActivity);
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponseDto>> getAllActivities() {
        List<ActivityResponseDto> activities = activityService.getAllActivities();
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityResponseDto> getActivityById(@PathVariable String id) {
        ActivityResponseDto activity = activityService.getActivityById(id);
        return ResponseEntity.ok(activity);
    }
}
