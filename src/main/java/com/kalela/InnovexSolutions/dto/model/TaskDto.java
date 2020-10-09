package com.kalela.InnovexSolutions.dto.model;

import com.google.cloud.Timestamp;
import lombok.Data;

@Data
public class TaskDto {
    private int id;
    private String name;
    private Timestamp actual_time;
    private Timestamp application_time;
    private String task_definition;
    private boolean isRunning;
    private int color;
}
