package com.gyosh.kemangi.core.factory;

import com.gyosh.kemangi.core.task.Task;

import javax.swing.*;
import java.util.List;

public interface TaskFactory {
    String NO_PARAMETER = "No input necessary";

    String getDescription();
    JPanel getParameterPanel();
    Task createTask();
    List<String> validateInput();
}
