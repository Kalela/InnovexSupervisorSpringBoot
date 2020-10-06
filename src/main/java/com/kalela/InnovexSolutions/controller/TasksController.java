package com.kalela.InnovexSolutions.controller;

import com.kalela.InnovexSolutions.service.ScheduledTasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Set;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private static final Logger log = LoggerFactory.getLogger(TasksController.class);

    private static final String SCHEDULED_TASKS = "scheduledTasks";

    @Autowired
    private ScheduledAnnotationBeanPostProcessor postProcessor;

    @Autowired
    ScheduledTasks scheduledTasks;


    @GetMapping("/start")
    public HashMap<String, String> startSchedule() {
        HashMap<String, Integer> taskNoMap = getNumberOfTasks();
        HashMap<String, String> response = new HashMap<>();

        if (taskNoMap.get("no_of_running_tasks") > 0) { // Check if there are tasks already running first
            postProcessor.postProcessBeforeDestruction(scheduledTasks, SCHEDULED_TASKS); // If running, stop them
        }

        postProcessor.postProcessAfterInitialization(scheduledTasks, SCHEDULED_TASKS);
        response.put("message", "OK");

        return response;
    }

    @GetMapping("/stop")
    public HashMap<String, String> stopSchedule() {
        HashMap<String, String> response = new HashMap<>();
        postProcessor.postProcessBeforeDestruction(scheduledTasks, SCHEDULED_TASKS);
        response.put("message", "OK");

        return response;
    }

    @GetMapping
    public HashMap<String, Integer> getNumberOfTasks() {
        Set<ScheduledTask> setTasks = postProcessor.getScheduledTasks();
        HashMap<String, Integer> response = new HashMap<>();

        response.put("no_of_running_tasks", setTasks.size());

        return response;
    }
}
