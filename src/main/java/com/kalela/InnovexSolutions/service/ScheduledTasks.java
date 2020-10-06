package com.kalela.InnovexSolutions.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@EnableAsync
@Service
public class ScheduledTasks {

    int upperBound;
    int lowerBound;
    int runningServers;

    Random random = new Random();

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Async
    @Scheduled(fixedDelay = 30000, initialDelay = 30000)
    public void startServers() {

        upperBound = 21;
        lowerBound = 10;

        int newInt = random.nextInt(upperBound - lowerBound) + lowerBound;

        runningServers = runningServers + newInt;

        log.info("Number of servers started {}", newInt);
        log.info("The time of start task is {}", dateFormat.format(new Date()));
    }

    @Async
    @Scheduled(fixedDelay = 40000, initialDelay = 40000)
    public void stopServers() {
        upperBound = runningServers;
        lowerBound = 5;
        int newInt = random.nextInt(upperBound - lowerBound) + lowerBound;

        runningServers = runningServers - newInt;

        log.info("Number of servers stopped {}", newInt);
        log.info("The time of stop task is {}", dateFormat.format(new Date()));
    }

    @Async
    @Scheduled(fixedDelay = 50000, initialDelay = 50000)
    public void reportServers() {

        log.info("Number of servers running {}", runningServers);
        log.info("The time of report task is {}", dateFormat.format(new Date()));
    }
}
