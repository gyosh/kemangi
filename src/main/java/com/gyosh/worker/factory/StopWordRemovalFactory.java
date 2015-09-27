package com.gyosh.worker.factory;

import com.gyosh.worker.task.Stem;
import com.gyosh.worker.task.StopWordRemoval;
import com.gyosh.worker.task.Task;

import javax.swing.*;

public class StopWordRemovalFactory implements TaskFactory{
    // Singleton
    private static JPanel parameterPanel;

    public StopWordRemovalFactory() {
        initParameterPanel();
    }

    public String getDescription() {
        // TODO: real description
        return "Description";
    }

    public String getIncludedTask() {
        // TODO: real included task
        return "Task";
    }

    public JPanel getParameterPanel() {
        if (parameterPanel == null) {
            initParameterPanel();
        }
        return parameterPanel;
    }

    public Task createTask() {
        return new StopWordRemoval();
    }

    private void initParameterPanel() {
        parameterPanel = new JPanel();
        parameterPanel.add(new JLabel(NO_PARAMETER));
    }

    public String toString() {
        return StopWordRemoval.TASK_NAME;
    }
}
