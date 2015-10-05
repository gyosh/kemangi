package com.gyosh.kemangi.core.task;

import java.util.List;

public class CaseFolding implements Task {
    public static final String TASK_NAME = "Case folding";
    private int stringProcessed;
    private int totalString;

    public List<List<String>> exec(List<List<String>> doc) {
        stringProcessed = 0;
        totalString = doc.size();
        for (List<String> tokens : doc) {
            for (int i = 0; i < tokens.size(); i++) {
                tokens.set(i, tokens.get(i).toLowerCase());
            }
            stringProcessed++;
        }
        return doc;
    }

    public int getProgressPercentage() {
        return 100 * stringProcessed / totalString;
    }

    public String getCurrentActivity() {
        return TASK_NAME + " (" + stringProcessed + "/" + totalString + ")";
    }

    public String toString() {
        return TASK_NAME;
    }
}