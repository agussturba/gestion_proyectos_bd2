package com.grupo.bd2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Entity
public class Employee {
    @Id
    Long id;
    String name;
    String skills;
    String availability;
}
