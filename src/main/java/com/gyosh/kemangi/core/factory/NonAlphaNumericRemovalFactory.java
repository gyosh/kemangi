package com.gyosh.kemangi.core.factory;

import com.gyosh.kemangi.core.task.NonAlphaNumericRemoval;
import com.gyosh.kemangi.core.task.Task;
import com.gyosh.kemangi.core.utility.Util;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class NonAlphaNumericRemovalFactory implements TaskFactory{
    // Singleton
    private static JPanel parameterPanel;

    public NonAlphaNumericRemovalFactory() {
        initParameterPanel();
    }

    public String getDescription() {
        return Util.getResourceContent("taskDescription/NonAlphaNumericRemoval.html");
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

    public List<String> validateInput() {
        return new ArrayList<String>();
    }

    public String toString() {
        return NonAlphaNumericRemoval.TASK_NAME;
    }
}
