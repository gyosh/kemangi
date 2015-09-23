package com.gyosh.worker.factory;

import com.gyosh.worker.task.CaseFolding;
import com.gyosh.worker.task.OwnStopWordRemoval;
import com.gyosh.worker.task.Task;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CaseFoldingFactory implements TaskFactory{
    private JPanel parameterPanel;

    public CaseFoldingFactory() {
        initParameterPanel();
    }

    public String getDescription() {
        // TODO: real description
        return "Case Folding Description";
    }

    public String getIncludedTask() {
        // TODO: real included task
        return "Case Folding Task";
    }

    public JPanel getParameterPanel() {
        return parameterPanel;
    }

    public Task createTask() {
        return new CaseFolding();
    }

    private void initParameterPanel() {
        parameterPanel = new JPanel();
        // TODO: refactor these repeated text
        parameterPanel.add(new JLabel("No Input Necessary"));
    }
}
