package com.grupo.bd2.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.MongoId;
@Data
@org.springframework.data.mongodb.core.mapping.Document
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Document {
    @MongoId
    private Long id;
    private String name;

}
