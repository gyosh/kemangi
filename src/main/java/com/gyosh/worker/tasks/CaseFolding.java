package com.gyosh.worker.tasks;

import com.gyosh.worker.Task;

import java.util.List;

public class CaseFolding implements Task {
    public List<List<String>> exec(List<List<String>> doc) {
        for (List<String> tokens : doc) {
            for (int i = 0; i < tokens.size(); i++) {
                tokens.set(i, tokens.get(i).toLowerCase());
            }
        }
        return doc;
    }
}