package com.gyosh.worker.factory;

import com.gyosh.worker.task.NonAlphaNumericRemoval;
import com.gyosh.worker.task.Stem;
import com.gyosh.worker.task.Task;

import javax.swing.*;

/**
 * Created by gyosh on 9/27/15.
 */
public class StemFactory implements TaskFactory{
    // Singleton
    private static JPanel parameterPanel;

    public StemFactory() {
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
        return new Stem();
    }

    private void initParameterPanel() {
        parameterPanel = new JPanel();
        parameterPanel.add(new JLabel(NO_PARAMETER));
    }

    public String toString() {
        return Stem.TASK_NAME;
    }
}

