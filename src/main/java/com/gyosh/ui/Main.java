package com.gyosh.ui;

import com.gyosh.worker.Task;
import com.gyosh.worker.tasks.CaseFolding;
import com.gyosh.worker.tasks.NonAlphaNumericRemoval;
import com.gyosh.worker.tasks.OwnStopWordRemoval;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private JList taskList;
    private JButton addTask;
    private JButton removeTask;
    private JButton previousTask;
    private JButton nextTask;
    private JButton start;
    private JProgressBar progressBar;
    private JPanel mainPanel;
    private JTextField inputFilePath;
    private JButton browseInputFile;
    private JLabel progressLog;

    private File inputFile;
    private TaskSelector taskSelector;

    public Main() {
        browseInputFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                final JFileChooser fileChooser = new JFileChooser();

                int returnVal = fileChooser.showOpenDialog(mainPanel);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    inputFile = fileChooser.getSelectedFile();
                    inputFilePath.setText(inputFile.getAbsolutePath());
                }
            }
        });

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                final JFileChooser fileChooser = new JFileChooser();

                int returnVal = fileChooser.showSaveDialog(mainPanel);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File outputFile = fileChooser.getSelectedFile();
                }
            }
        });

        addTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                taskSelector = new TaskSelector();
                taskSelector.pack();
                taskSelector.setVisible(true);
            }
        });
        removeTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Kemangi");

        Main main = new Main();
        frame.setContentPane(main.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
