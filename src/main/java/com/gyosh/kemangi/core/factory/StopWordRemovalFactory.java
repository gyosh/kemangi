package com.gyosh.kemangi.core.factory;

import com.gyosh.kemangi.core.task.StopWordRemoval;
import com.gyosh.kemangi.core.task.Task;
import com.gyosh.kemangi.core.utility.Util;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StopWordRemovalFactory implements TaskFactory{
    // Singleton
    private static JPanel parameterPanel;

    public StopWordRemovalFactory() {
        initParameterPanel();
    }

    public String getDescription() {
        return Util.getResourceContent("taskDescription/StopWordRemoval.html");
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

    public List<String> validateInput() {
        return new ArrayList<String>();
    }

    public String toString() {
        return StopWordRemoval.TASK_NAME;
    }
}
