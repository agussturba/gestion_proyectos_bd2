package com.grupo.bd2.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Builder
@Document
public class Resource {
    Long id;
    String description;
    Task task;
    Project project;
    Employee employee;

    public Resource(String description, Task task, Project project, Employee employee){
        this.description = description;
        this.task = task;
        this.project = project;
        this.employee = employee;
    }
}
