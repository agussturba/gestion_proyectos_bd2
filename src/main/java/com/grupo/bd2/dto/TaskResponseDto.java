package com.grupo.bd2.dto;

import com.grupo.bd2.model.Employee;
import com.grupo.bd2.model.Task;
import com.grupo.bd2.model.TaskState;
import java.util.List;


    public class TaskResponseDto {
    Long id;
    String description;
    TaskState taskState;
    Task fatherTask;
    List<Employee> assignedEmployees;
    Integer storyPoints;

}
