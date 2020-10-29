package com.kalela.InnovexSolutions.controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.kalela.InnovexSolutions.data.model.Task;
import com.kalela.InnovexSolutions.dto.model.TaskDto;
import com.kalela.InnovexSolutions.service.FirebaseInitializer;
import com.kalela.InnovexSolutions.service.ScheduledTasks;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    @Autowired
    FirebaseInitializer db;

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

    @GetMapping("/running")
    public Object getCurrentlyRunningTasks() {
        CollectionReference tasksRef = db.getFirestoreDb().collection("tasks");

        ApiFuture<QuerySnapshot> future = tasksRef.whereEqualTo("running", true).get();

        List<QueryDocumentSnapshot> documents = null;

        ArrayList<Object> array = new ArrayList<>();
        try {
            documents = future.get().getDocuments();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "An error occured while reading from the database.");
            error.put("error", e.getMessage());
            array.add(error);
            return array;
        }

        for (QueryDocumentSnapshot document : documents) {
            TaskDto task = document.toObject(TaskDto.class);
            array.add(task);
        }

        return array;
    }

    @GetMapping("/report")
    public Object getReportOfAllTasks() {
        CollectionReference tasksRef = db.getFirestoreDb().collection("tasks");

        ApiFuture<QuerySnapshot> future = tasksRef.get();

        List<QueryDocumentSnapshot> documents = null;
        ArrayList<Object> array = new ArrayList<>();
        try {
            documents = future.get().getDocuments();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "An error occured while reading from the database.");
            error.put("error", e.getMessage());
            array.add(error);
            return array;
        }



        for (QueryDocumentSnapshot document : documents) {
            TaskDto task = document.toObject(TaskDto.class);
            array.add(task);
        }

        return array;
    }

    @GetMapping("/clear")
    public HashMap<String, String> removeAllTasks() {
        HashMap<String, String> response = new HashMap<>();
        CollectionReference tasksRef = db.getFirestoreDb().collection("tasks");
        try {
            tasksRef.listDocuments().forEach(DocumentReference::delete);
        } catch (Exception e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "An error occured while reading from the database.");
            error.put("error", e.getMessage());
            return error;
        }



        response.put("message", "All tasks cleared");
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
