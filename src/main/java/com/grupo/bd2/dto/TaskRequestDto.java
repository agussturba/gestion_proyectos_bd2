package com.grupo.bd2.dto;

import com.grupo.bd2.model.TaskState;

import java.time.LocalDate;
import java.util.List;

public record TaskRequestDto(
    String description,
    List<Long> employeesId,
    Long fatherTaskId,
    Integer storyPoints,
    TaskState taskState,
    LocalDate startDate,
    LocalDate endDate
) {
}
