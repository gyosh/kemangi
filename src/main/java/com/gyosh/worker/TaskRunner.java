package com.gyosh.worker;

import com.gyosh.worker.tasks.OwnStopWordRemoval;
import com.gyosh.worker.tasks.StopWordRemoval;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TaskRunner {
    private String filename;
    private Queue<Task> taskQueue;

    public TaskRunner(String filename) {
        this.filename = filename;
        taskQueue = new LinkedList<Task>();
    }

    public void addTask(Task task) {
        taskQueue.add(task);
    }

    public void run() {
        List<List<String>> doc = Utility.loadAndSanitizeDocument(filename);

        while (!taskQueue.isEmpty()) {
            Task task = taskQueue.poll();
            doc = task.exec(doc);
        }

        for (List<String> tokens : doc) {
            for (String token : tokens) {
                System.out.print(token + " ");
            }
            System.out.println();
        }
    }

    public static void main(String args[]) {
        String filename = "data/test-input.txt";

        TaskRunner taskRunner = new TaskRunner(filename);

        taskRunner.addTask(new OwnStopWordRemoval("data/own-stopword.txt"));
        taskRunner.addTask(new StopWordRemoval());

        taskRunner.run();
    }
}
