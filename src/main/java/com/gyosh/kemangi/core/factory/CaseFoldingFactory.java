package com.gyosh.kemangi.core.factory;

import com.gyosh.kemangi.core.task.CaseFolding;
import com.gyosh.kemangi.core.task.Task;
import com.gyosh.kemangi.core.utility.Util;

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
