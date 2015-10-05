package com.gyosh.kemangi.core.factory;

import com.gyosh.kemangi.core.task.Stem;
import com.gyosh.kemangi.core.task.Task;
import com.gyosh.kemangi.core.utility.Util;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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
        return Util.getResourceContent("taskDescription/Stem.html");
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

    public List<String> validateInput() {
        return new ArrayList<String>();
    }

    public String toString() {
        return Stem.TASK_NAME;
    }
}

