package com.gyosh.worker.tasks;

import com.gyosh.worker.Task;
import com.gyosh.worker.Utility;

import java.util.Arrays;
import java.util.List;

public class NonAlphaNumericRemoval implements Task {
    public List<List<String>> exec(List<List<String>> doc) {
        for (int i = 0; i < doc.size(); i++) {
            doc.set(i, removeNonAlphaNumeric(doc.get(i)));
        }
        return doc;
    }

    private List<String> removeNonAlphaNumeric(List<String> tokens) {
        String sentence = Utility.join(tokens, " ");
        String cleanedSentence = sentence.replaceAll("[^0-9A-Za-z]", " ").trim();
        return Arrays.asList(cleanedSentence.split("\\s+"));
    }
}
