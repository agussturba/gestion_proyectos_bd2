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
    //TODO Ver que atributos entran en esta clase
}
