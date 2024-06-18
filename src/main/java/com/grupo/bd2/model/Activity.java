package com.grupo.bd2.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@org.springframework.data.mongodb.core.mapping.Document
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Activity {
    Long id;
    Employee employee;
    Project project;
    Task task;
    String description;

    public Activity(Employee employee, Project project, Task task, String description) {
        this.employee = employee;
        this.project = project;
        this.task = task;
        this.description = description;
    }
}
