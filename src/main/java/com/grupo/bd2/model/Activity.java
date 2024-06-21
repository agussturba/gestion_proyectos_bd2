package com.grupo.bd2.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@org.springframework.data.mongodb.core.mapping.Document
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Activity {
    @Id
    String id;
    Employee employee;
    Project project;
    Task task;
    String description;
    LocalDate creationDate;

    public Activity(Employee employee, Project project, Task task, String description, LocalDate creationDate) {
        this.employee = employee;
        this.project = project;
        this.task = task;
        this.description = description;
        this.creationDate = creationDate;
    }
}
