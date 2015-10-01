package com.gyosh.worker.factory;

import com.gyosh.worker.task.CaseFolding;
import com.gyosh.worker.task.Task;
import com.gyosh.worker.utility.Util;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CaseFoldingFactory implements TaskFactory{
    // Singleton
    private static JPanel parameterPanel;

    public CaseFoldingFactory() {
        initParameterPanel();
    }

    public String getDescription() {
        return Util.getResourceContent("taskDescription/CaseFolding.html");
    }

    public JPanel getParameterPanel() {
        if (parameterPanel == null) {
            initParameterPanel();
        }
        return parameterPanel;
    }

    public Task createTask() {
        return new CaseFolding();
    }

    private void initParameterPanel() {
        parameterPanel = new JPanel();
        parameterPanel.add(new JLabel(NO_PARAMETER));
    }

    public List<String> validateInput() {
        return new ArrayList<String>();
    }

    public String toString() {
        return CaseFolding.TASK_NAME;
    }
}
