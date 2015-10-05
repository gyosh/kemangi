package com.gyosh.kemangi.core.task;

import com.gyosh.kemangi.core.utility.Util;

import java.util.Arrays;
import java.util.List;

public class NonAlphaNumericRemoval implements Task {
    public static final String TASK_NAME = "Non alphanumeric removal";
    private int stringProcessed;
    private int totalString;

    public List<List<String>> exec(List<List<String>> doc) {
        stringProcessed = 0;
        totalString = doc.size();

        for (int i = 0; i < doc.size(); i++) {
            doc.set(i, removeNonAlphaNumeric(doc.get(i)));
            stringProcessed++;
        }
        return doc;
    }

    private List<String> removeNonAlphaNumeric(List<String> tokens) {
        String sentence = Util.join(tokens, " ");
        String cleanedSentence = sentence.replaceAll("[^0-9A-Za-z]", " ").trim();
        return Arrays.asList(cleanedSentence.split("\\s+"));
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
