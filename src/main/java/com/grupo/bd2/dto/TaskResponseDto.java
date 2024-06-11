package com.grupo.bd2.dto;


import com.grupo.bd2.model.TaskState;
import lombok.Builder;

import java.util.List;

@Builder
public record TaskResponseDto(
    Long id,
    String description,
    TaskState taskState,
    List<Long> assignedEmployees,
    Long fatherTask,
    Integer storyPoints){}
