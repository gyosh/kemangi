package com.gyosh.worker.factory;

import com.gyosh.worker.task.Task;

import javax.swing.*;

public interface TaskFactory {
    String getDescription();
    String getIncludedTask();
    JPanel getParameterPanel();
    Task createTask();
}
