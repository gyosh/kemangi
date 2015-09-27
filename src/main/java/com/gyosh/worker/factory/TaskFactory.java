package com.gyosh.worker.factory;

import com.gyosh.worker.task.Task;

import javax.swing.*;
import java.util.List;

public interface TaskFactory {
    public static final String NO_PARAMETER = "No input necessary";

    String getDescription();
    String getIncludedTask();
    JPanel getParameterPanel();
    Task createTask();
    List<String> validateInput();
}
