package com.kalela.InnovexSolutions.service;

import com.google.cloud.firestore.CollectionReference;
import com.kalela.InnovexSolutions.data.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@EnableAsync
@Service
public class ScheduledTasks {

    @Autowired
    FirebaseInitializer db;

    int upperBound;
    int lowerBound;
    int runningServers;
    int taskId = 1;

    private static final String START = "START";
    private static final String STOP = "STOP";
    private static final String REPORT = "REPORT";

    Random random = new Random();

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Async
    @Scheduled(fixedDelay = 30000)
    public void startServers() {
        Task task = new Task();
        CollectionReference tasksRef = db.getFirestoreDb().collection("tasks");

        upperBound = 21;
        lowerBound = 10;

        int newInt = random.nextInt(upperBound - lowerBound) + lowerBound;

        runningServers = runningServers + newInt;

        task.setActual_time(new Timestamp(new Date().getTime()));
        task.setName(START);
        task.setTask_definition("Started " + newInt + " servers");
        task.setApplication_time(new Timestamp(0));
        task.setId(taskId);
        task.setRunning(true);

        tasksRef.document().set(task);

    }

    @Async
    @Scheduled(fixedDelay = 40000, initialDelay = 40000)
    public void stopServers() {
        Task task = new Task();

        CollectionReference tasksRef = db.getFirestoreDb().collection("tasks");

        upperBound = runningServers;
        lowerBound = 5;
        int newInt = random.nextInt(upperBound - lowerBound) + lowerBound;

        runningServers = runningServers - newInt;

        task.setActual_time(new Timestamp(new Date().getTime()));
        task.setName(STOP);
        task.setTask_definition("Stopped " + newInt + " servers");
        task.setApplication_time(new Timestamp(0));
        task.setId(taskId);
        task.setRunning(true);

        tasksRef.document().set(task);

    }

    @Async
    @Scheduled(fixedDelay = 50000, initialDelay = 50000)
    public void reportServers() {
        Task task = new Task();
        CollectionReference tasksRef = db.getFirestoreDb().collection("tasks");

        task.setActual_time(new Timestamp(new Date().getTime()));
        task.setName(REPORT);
        task.setTask_definition("Currently running " + runningServers + " servers");
        task.setApplication_time(new Timestamp(0));
        task.setId(taskId);
        task.setRunning(true);

        tasksRef.document().set(task);
    }
}
