package com.gyosh.worker;

import com.gyosh.worker.task.*;
import com.gyosh.worker.utility.Util;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TaskRunner {
    private static final String STATUS_WAITING = "Waiting for input...";
    private static final String STATUS_READING = "Reading input...";
    private static final String STATUS_WRITING = "Writing output...";
    private static final String STATUS_DONE = "Finished";

    private static final int WEIGHT_READ_INPUT = 1;
    private static final int WEIGHT_WRITE_OUTPUT = 1;

    private static final Logger logger = Logger.getLogger(TaskRunner.class);

    private String inputFilePath;
    private String outputFilePath;
    private Queue<Task> taskQueue;
    private List<List<String>> doc;

    private int totalTaskWeight;
    private int currentTaskWeight;
    private String currentActivity;
    private Task currentTask;

    public TaskRunner(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;

        taskQueue = new LinkedList<Task>();
        currentActivity = STATUS_WAITING;
    }

    public void addTask(Task task) {
        taskQueue.add(task);
    }

    public void run() {
        initRun();
        readInput();

        while (!taskQueue.isEmpty()) {
            currentTask = taskQueue.poll();

            logger.info("Executing " + currentTask.toString());
            doc = currentTask.exec(doc);
            currentTaskWeight++;
            currentActivity = currentTask.toString();
        }

        writeOutput();

        currentActivity = STATUS_DONE;
        logger.info(STATUS_DONE);
    }

    private void initRun() {
        logger.info("Initializing task runner");
        totalTaskWeight = WEIGHT_READ_INPUT + taskQueue.size() + WEIGHT_WRITE_OUTPUT;
        currentTaskWeight = 0;
    }

    private void readInput() {
        currentTask = null;
        currentActivity = STATUS_READING;
        logger.info(STATUS_READING);
        doc = Util.loadAndSanitizeDocument(inputFilePath);
        currentTaskWeight += WEIGHT_READ_INPUT;
    }

    private void writeOutput() {
        currentTask = null;
        currentActivity = STATUS_WRITING;
        logger.info(STATUS_WRITING);
        exportDocument();
        currentTaskWeight += WEIGHT_WRITE_OUTPUT;
    }

    public int getProgressPercentage() {
        double progress = (double)currentTaskWeight/totalTaskWeight;

        if (currentTask != null) {
            progress += currentTask.getProgressPercentage()/(100.0 * totalTaskWeight);
        }

        return (int)Math.ceil(progress*100);
    }

    public String getCurrentActivity() {
        if (currentTask != null) {
            return currentTask.getCurrentActivity();
        }
        return currentActivity;
    }

    private void exportDocument() {
        try {
            FileWriter fw = new FileWriter(outputFilePath);
            BufferedWriter bw = new BufferedWriter(fw);

            for (List<String> tokens : doc) {
                for (int i = 0; i < tokens.size(); i++) {
                    bw.write(tokens.get(i));
                    if (i + 1 < tokens.size()) {
                        bw.write(" ");
                    }
                }
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
