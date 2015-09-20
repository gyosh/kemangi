package com.gyosh.worker.tasks;

import com.gyosh.worker.Parameter;
import com.gyosh.worker.Task;
import com.gyosh.worker.Utility;

import java.util.ArrayList;
import java.util.List;

public class OwnStopWordRemoval implements Task {
    public List<List<String>> exec(List<List<String>> doc, Parameter param) {
        if (!param.isUseOwnStopwordRemoval()) {
            return doc;
        }
        List<String> ownStopWords = Utility.readList(param.getOwnStopwordFilename());

        for (int i = 0; i < doc.size(); i++) {
            doc.set(i, removeForbiddenWords(doc.get(i), ownStopWords));
        }

        return doc;
    }

    private List<String> removeForbiddenWords(List<String> tokens, List<String> ownStopWords) {
        List<String> cleanedTokens = null;
        try {
            cleanedTokens = new ArrayList<String>();
            for (String token : tokens) {
                if (!isStopWord(token, ownStopWords)) {
                    cleanedTokens.add(token);
                }
            }
        } catch (Exception e) {
            // TODO: logging
            e.printStackTrace();
        }
        return cleanedTokens;
    }

    private boolean isStopWord(String str, List<String> ownStopWords) {
        for (String pattern : ownStopWords) {
            if (str.matches(pattern)) {
                return true;
            }
        }
        return false;
    }
}
