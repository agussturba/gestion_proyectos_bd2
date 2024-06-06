package com.grupo.bd2.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Builder
public class Resource {
    Long id;
    String description;
    //TODO Ver que atributos entran en esta clase
}
