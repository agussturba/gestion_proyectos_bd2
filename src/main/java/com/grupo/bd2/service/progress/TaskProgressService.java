package com.grupo.bd2.service.progress;

import com.grupo.bd2.dto.TaskProgressRequestDto;
import com.grupo.bd2.dto.TaskProgressResponseDto;


import java.util.List;

public interface TaskProgressService {
    TaskProgressResponseDto getProgressById(Long id); // Progreso de una task
}
