package com.grupo.bd2.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "comentarios")
@Data
public class Comment {
    @Id
    private String id;
    private Task task;
    private Employee employee;
    private String comment;
    private LocalDate date;

    public Comment(Task task, Employee employee, String comment, LocalDate date) {
        this.task = task;
        this.employee = employee;
        this.comment = comment;
        this.date = date;
    }
}
