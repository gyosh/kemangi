package com.gyosh.worker.factory;


import com.gyosh.worker.task.OwnStopWordRemoval;
import com.gyosh.worker.task.Task;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OwnStopWordRemovalFactory implements TaskFactory {
    private JPanel parameterPanel;
    private JTextField filePath;
    private JButton browseFile;

    public OwnStopWordRemovalFactory() {
        initParameterPanel();
    }

    public String getDescription() {
        // TODO: real description
        return "Own Stop Word Description";
    }

    public String getIncludedTask() {
        // TODO: real included task
        return "Own Stop Word Task";
    }

    public JPanel getParameterPanel() {
        return parameterPanel;
    }

    public Task createTask() {
        return new OwnStopWordRemoval(filePath.getText());
    }

    private void initParameterPanel() {
        filePath = new JTextField();
        filePath.setEditable(false);

        browseFile = new JButton("Browse");

        parameterPanel = new JPanel();
        parameterPanel.setLayout(new BoxLayout(parameterPanel, BoxLayout.Y_AXIS));
        parameterPanel.add(new JLabel("Own stop words file:"));
        parameterPanel.add(filePath);
        parameterPanel.add(browseFile);

        browseFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                final JFileChooser fileChooser = new JFileChooser();

                int returnVal = fileChooser.showOpenDialog(parameterPanel);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File inputFile = fileChooser.getSelectedFile();
                    filePath.setText(inputFile.getAbsolutePath());
                }
            }
        });
    }
}
