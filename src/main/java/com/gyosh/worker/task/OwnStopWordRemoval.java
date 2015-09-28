package com.gyosh.worker.task;

import com.gyosh.worker.Utility;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OwnStopWordRemoval implements Task {
    public static final String TASK_NAME = "Own stop words removal";
    private static final Logger logger = Logger.getLogger(OwnStopWordRemoval.class);

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
            logger.error(e.getMessage());
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

    public String toString() {
        return TASK_NAME + " (" + ownStopWordFilename + ")";
    }
}
