package com.gyosh.kemangi.core.task;

import com.gyosh.kemangi.core.utility.Util;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OwnStopWordRemoval implements Task {
    public static final String TASK_NAME = "Own stop words removal";
    private static final Logger logger = Logger.getLogger(OwnStopWordRemoval.class);

    private String ownStopWordFilename;
    private List<String> ownStopWords;
    private int stringProcessed;
    private int totalString;

    public OwnStopWordRemoval(String ownStopWordFilename) {
        this.ownStopWordFilename = ownStopWordFilename;
    }

    public List<List<String>> exec(List<List<String>> doc) {
        stringProcessed = 0;
        totalString = doc.size();

        ownStopWords = Util.readList(ownStopWordFilename);

        for (int i = 0; i < doc.size(); i++) {
            doc.set(i, removeOwnStopWord(doc.get(i)));
            stringProcessed++;
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

    public int getProgressPercentage() {
        return 100 * stringProcessed / totalString;
    }

    public String getCurrentActivity() {
        return TASK_NAME + " (" + stringProcessed + "/" + totalString + ")";
    }

    public String toString() {
        return TASK_NAME + " (" + ownStopWordFilename + ")";
    }
}
