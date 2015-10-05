package com.gyosh.kemangi.core.task;

import java.util.List;

public interface Task {
    List<List<String>> exec(List<List<String>> doc);
    int getProgressPercentage();
    String getCurrentActivity();
}
