package com.gyosh.worker.factory;

import com.gyosh.worker.task.CaseFolding;
import com.gyosh.worker.task.NonAlphaNumericRemoval;
import com.gyosh.worker.task.Task;

import javax.swing.*;

public class NonAlphaNumericRemovalFactory implements TaskFactory{
    // Singleton
    private static JPanel parameterPanel;

    public NonAlphaNumericRemovalFactory() {
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
        return new NonAlphaNumericRemoval();
    }

    private void initParameterPanel() {
        parameterPanel = new JPanel();
        parameterPanel.add(new JLabel(NO_PARAMETER));
    }

    public String toString() {
        return NonAlphaNumericRemoval.TASK_NAME;
    }
}
