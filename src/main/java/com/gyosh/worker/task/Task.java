package com.gyosh.worker.task;

import java.util.List;

public interface Task {
    List<List<String>> exec(List<List<String>> doc);
}
