package com.grupo.bd2.service.progress;

import com.grupo.bd2.dto.TaskProgressRequestDto;
import com.grupo.bd2.dto.TaskProgressResponseDto;
import com.grupo.bd2.exceptions.NotFoundException;
import com.grupo.bd2.model.Task;
import com.grupo.bd2.model.TaskProgress;
import com.grupo.bd2.model.TaskState;
import com.grupo.bd2.repository.TaskProgressRepository;
import com.grupo.bd2.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskProgressServiceImpl implements TaskProgressService{
    private final TaskRepository taskRepository;

    @Override
    public TaskProgressResponseDto getProgressById(Long id) {
        final var task = taskRepository.findById(id).orElseThrow(NotFoundException::new);
        final var subtasks = taskRepository.findByFatherTask(task);
        if (subtasks.isEmpty()) {
            return convertToDto(TaskProgress
                    .builder()
                    .task(task)
                    .percentageCompleted(procentajeCompletado(task))
                    .timeWorkedOn(tiempoTrabajado(task))
                    .build());
        }
        Double totalTrabajado = 0.0;
        Double totalTiempoCompletado = 0.0;
        for (Task subtask:subtasks){
            totalTiempoCompletado += tiempoTrabajado(subtask);
            totalTrabajado += procentajeCompletado(subtask);
        }
        return convertToDto(TaskProgress
                .builder()
                .task(task)
                .percentageCompleted(totalTrabajado == 0.0 ? 0.0 : (totalTrabajado / subtasks.size()))
                .timeWorkedOn(totalTiempoCompletado)
                .build());

    }

    private Double procentajeCompletado(Task task){
        if (TaskState.DONE.equals(task.getTaskState())){
            return 100.0;
        }
        return 0.0;
    }

    private Double tiempoTrabajado(Task task){
        if (task.getStartDate() == null){
            return 0.0;
        }
        else if (task.getEndDate() != null){
            return (double) ChronoUnit.HOURS.between(task.getStartDate(), task.getEndDate());
        }
        return (double) ChronoUnit.HOURS.between(task.getStartDate(),LocalDate.now());
    }

    private TaskProgressResponseDto convertToDto(TaskProgress taskProgress) {
        return TaskProgressResponseDto
                .builder()
                .id(taskProgress.getTask().getId())
                .timeWorked(taskProgress.getTimeWorkedOn())
                .percentageCompleted(taskProgress.getPercentageCompleted())
                .build();
    }
}
