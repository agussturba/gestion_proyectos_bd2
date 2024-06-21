package com.grupo.bd2.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
@Builder
public record ProjectRequestDto(
        String name,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        Boolean isActive,
        List<Long> taskIds,
        List<Long> employeesIds
) {
}
