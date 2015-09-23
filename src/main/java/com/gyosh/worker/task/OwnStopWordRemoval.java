package com.gyosh.worker.task;

import com.gyosh.worker.Utility;

import java.util.ArrayList;
import java.util.List;

public class OwnStopWordRemoval implements Task {
    private String ownStopWordFilename;
    private List<String> ownStopWords;

    public OwnStopWordRemoval(String ownStopWordFilename) {
        this.ownStopWordFilename = ownStopWordFilename;
    }

    public List<List<String>> exec(List<List<String>> doc) {
        ownStopWords = Utility.readList(ownStopWordFilename);

        for (int i = 0; i < doc.size(); i++) {
            doc.set(i, removeOwnStopWord(doc.get(i)));
        }
        return doc;
    }

    private List<String> removeOwnStopWord(List<String> tokens) {
        List<String> cleanedTokens = null;
        try {
            cleanedTokens = new ArrayList<String>();
            for (String token : tokens) {
                if (!isStopWord(token)) {
                    cleanedTokens.add(token);
                }
            }
        } catch (Exception e) {
            // TODO: logging
            e.printStackTrace();
        }
        return cleanedTokens;
    }

    private boolean isStopWord(String str) {
        for (String pattern : ownStopWords) {
            if (str.matches(pattern)) {
                return true;
            }
        }
        return false;
    }
}
