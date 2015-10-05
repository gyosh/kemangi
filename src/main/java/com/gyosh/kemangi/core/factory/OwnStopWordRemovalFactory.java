package com.gyosh.kemangi.core.factory;


import com.gyosh.kemangi.core.task.OwnStopWordRemoval;
import com.gyosh.kemangi.core.task.Task;
import com.gyosh.kemangi.core.utility.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OwnStopWordRemovalFactory implements TaskFactory {
    private static final String NO_STOP_WORD_FILE = "Please specify your stop word list file!";

    // Singletons
    private static JPanel parameterPanel;
    private static JTextField filePath;
    private static JButton browseFile;

    public OwnStopWordRemovalFactory() {
        initParameterPanel();
    }

    public String getDescription() {
        return Util.getResourceContent("taskDescription/OwnStopWordRemoval.html");
    }

    public JPanel getParameterPanel() {
        if (parameterPanel == null) {
            initParameterPanel();
        }
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

    public List<String> validateInput() {
        List<String> errors = new ArrayList<String>();
        if (filePath.getText().isEmpty()) {
            errors.add(NO_STOP_WORD_FILE);
        }
        return errors;
    }

    public String toString() {
        return OwnStopWordRemoval.TASK_NAME;
    }
}
