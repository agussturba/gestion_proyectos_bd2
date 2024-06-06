package com.grupo.bd2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
public class Project {
    @jakarta.persistence.Id
    Long id;
    String name;
    String description;
    LocalDate startDate;
    LocalDate endDate;
    Boolean isActive;
    @OneToMany
    List<Task> task;

}
