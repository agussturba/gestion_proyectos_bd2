package com.grupo.bd2.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@org.springframework.data.mongodb.core.mapping.Document
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Activity {
    Long id;
    Employee employee;
    Project project;
    Task task;
    String description;
}
