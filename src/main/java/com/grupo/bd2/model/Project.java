package com.grupo.bd2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    LocalDate startDate;
    LocalDate endDate;
    Boolean isActive;
    @OneToMany
    List<Task> task;
    @OneToMany
    List<Employee> employees;

    public Project(String name, String description, LocalDate startDate, LocalDate endDate, Boolean isActive, List<Task> task, List<Employee> employees) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.task = task;
        this.employees = employees;
    }
}
