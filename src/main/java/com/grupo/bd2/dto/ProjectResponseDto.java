package com.grupo.bd2.dto;


import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record ProjectResponseDto(
        Long id,
        String name,
        String description,
        Boolean isActive,
        LocalDate startDate,
        LocalDate endDate,
        String createdAt,
        String updatedAt,
        List<Long> tasksIds,
        List<Long> employeesIds,
        Integer totalTasks,
        Integer doneTasks,
        Float percentageEnded
    ) {
}
