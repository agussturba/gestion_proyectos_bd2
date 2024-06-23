package com.grupo.bd2.controller;

import com.grupo.bd2.dto.ActivityRequestDto;
import com.grupo.bd2.dto.ActivityResponseDto;
import com.grupo.bd2.service.activity.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
@AllArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @Operation(summary = "Create a Activity")
    @PostMapping
    public ResponseEntity<ActivityResponseDto> createActivity(@RequestBody ActivityRequestDto activity) {
        ActivityResponseDto newActivity = activityService.createOrUpdateActivity(activity);
        return ResponseEntity.ok(newActivity);
    }
    @Operation(summary = "Update a Activity")
    @PutMapping
    public ResponseEntity<ActivityResponseDto> updateActivity(@RequestBody ActivityRequestDto activity) {
        ActivityResponseDto newActivity = activityService.createOrUpdateActivity(activity);
        return ResponseEntity.ok(newActivity);
    }
    @Operation(summary = "Get all Activities")
    @GetMapping
    public ResponseEntity<List<ActivityResponseDto>> getAllActivities() {
        List<ActivityResponseDto> activities = activityService.getAllActivities();
        return ResponseEntity.ok(activities);
    }
    @Operation(summary = "Get a Activity by its id")
    @GetMapping("/{id}")
    public ResponseEntity<ActivityResponseDto> getActivityById(@PathVariable String id) {
        ActivityResponseDto activity = activityService.getActivityById(id);
        return ResponseEntity.ok(activity);
    }
}
