package com.gyosh.worker;

import com.gyosh.worker.tasks.OwnStopWordRemoval;

import java.util.List;

public class TaskRunner {
    private Parameter param;

    private TaskRunner(Parameter param) {
        this.param = param;
    }

    public static void main(String args[]) {
        String filename = "data/test-input.txt";
        List<List<String>> doc = Utility.loadAndSanitizeDocument(filename);

        Parameter param = new Parameter();
        param.setOwnStopwordFilename("data/own-stopword.txt");

        Task wow = new OwnStopWordRemoval();
        doc = wow.exec(doc, param);

        for (List<String> tokens : doc) {
            for (String token : tokens) {
                System.out.print(token + " ");
            }
            System.out.println();
        }
    }
}
