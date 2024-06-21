package com.grupo.bd2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String description;
    TaskState taskState;
    @OneToOne
    Task fatherTask;
    @ManyToMany
    Set<Employee> assignedEmployees;
    LocalDate createDate;
    LocalDate startDate;
    LocalDate endDate;
    Integer storyPoints;
    String necessarySkills;//Se dividen por , y su formato seria java,python,etc

    public Task(String description, TaskState taskState, Task fatherTask, Set<Employee> assignedEmployees, LocalDate createDate, LocalDate startDate, LocalDate endDate , Integer storyPoints, String necessarySkills) {
        this.description = description;
        this.taskState = taskState;
        this.fatherTask = fatherTask;
        this.assignedEmployees = assignedEmployees;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.storyPoints = storyPoints;
        this.necessarySkills = necessarySkills;
    }
    public void addEmployee(Employee employee){
        assignedEmployees.add(employee);
    }
}
