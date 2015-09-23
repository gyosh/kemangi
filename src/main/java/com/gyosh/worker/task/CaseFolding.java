package com.gyosh.worker.task;

import java.util.List;

public class CaseFolding implements Task {
    public static final String TASK_NAME = "Case Folding";

    public List<List<String>> exec(List<List<String>> doc) {
        for (List<String> tokens : doc) {
            for (int i = 0; i < tokens.size(); i++) {
                tokens.set(i, tokens.get(i).toLowerCase());
            }
        }
        return doc;
    }

    public String toString() {
        return TASK_NAME;
    }
}