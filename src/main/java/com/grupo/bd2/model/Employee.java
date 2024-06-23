package com.grupo.bd2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Entity
@EqualsAndHashCode
public class Employee {
    @Id
    @GeneratedValue
    Long id;
    String name;
    String skills;
}
