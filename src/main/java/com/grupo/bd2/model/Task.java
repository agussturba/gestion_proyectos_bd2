package com.grupo.bd2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Entity
public class Task {
    @Id
    Long id;
    String description;
    TaskState taskState;
    @OneToOne
    Task fatherTask;
    @ManyToMany
    List<Employee> assignedEmployees;
    Integer storyPoints;
}
