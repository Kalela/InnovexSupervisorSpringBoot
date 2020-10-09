package com.kalela.InnovexSolutions.data.model;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class Task {
    private int id;
    private String name;
    private Timestamp actual_time;
    private Timestamp application_time;
    private String task_definition;
    private boolean isRunning;
    private int color;
}
