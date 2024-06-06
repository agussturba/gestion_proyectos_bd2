package com.grupo.bd2.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Builder
public class TaskProgress {
    Task task;
    Double timeWorkedOn;
    Double percentageCompleted;
}
