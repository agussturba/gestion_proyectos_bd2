package com.grupo.bd2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "comentarios")
public class Comment {
    @Id
    private String id;
    private Task task;
    private Employee employee;
    private String comment;
    private Date date;

}
