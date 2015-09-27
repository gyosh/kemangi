package com.gyosh.ui;

import com.gyosh.worker.TaskRunner;
import com.gyosh.worker.task.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class Main {
    private static final int TIMER_INTERVAL = 100;

    private JList taskList;
    private JButton addTask;
    private JButton removeTask;
    private JButton previousTask;
    private JButton nextTask;
    private JButton start;
    private JProgressBar progressBar;
    private JPanel mainPanel;
    private JLabel progressLog;
    private JTextField inputFilePath;
    private JButton browseInputFile;
    private JTextField outputFilePath;
    private JButton browseOutputFile;

    private File inputFile;
    private File outputFile;
    private TaskSelector taskSelector;
    private TaskRunner taskRunner;
    private Timer progressTicker;

    private DefaultListModel taskListModel;

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

        browseOutputFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                final JFileChooser fileChooser = new JFileChooser();
                int returnVal = fileChooser.showSaveDialog(mainPanel);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    outputFile = fileChooser.getSelectedFile();
                    outputFilePath.setText(outputFile.getAbsolutePath());
                }
            }
        });

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                taskRunner = new TaskRunner(inputFilePath.getText(), outputFilePath.getText());
                for (int i = 0; i < taskListModel.getSize(); i++) {
                    taskRunner.addTask((Task)taskListModel.get(i));
                }

                progressBar.setMinimum(0);
                progressBar.setMaximum(100);
                progressTicker = new Timer(TIMER_INTERVAL, new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        updateProgress();
                    }
                });

                Runnable taskRunnerWrapper = new Runnable() {
                    public void run() {
                        progressTicker.start();

                        taskRunner.run();

                        updateProgress();
                        progressTicker.stop();
                    }
                };
                new Thread(taskRunnerWrapper).start();
            }
        });

        addTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                taskSelector = new TaskSelector();
                taskSelector.pack();
                taskSelector.setVisible(true);

                taskListModel.addElement(taskSelector.getCreatedTask());
                taskList.updateUI();
            }
        });

        removeTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int selectedIndex = taskList.getSelectedIndex();

                if ((0 <= selectedIndex) && (selectedIndex < taskListModel.getSize())) {
                    taskListModel.remove(selectedIndex);
                    taskList.setSelectedIndex(Math.min(selectedIndex, taskListModel.getSize() - 1));
                    taskList.updateUI();
                }
            }
        });

        previousTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int selectedIndex = taskList.getSelectedIndex();

                if (selectedIndex - 1 >= 0) {
                    swapTask(selectedIndex - 1, selectedIndex);
                    taskList.setSelectedIndex(selectedIndex - 1);
                    taskList.updateUI();
                }
            }
        });

        nextTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int selectedIndex = taskList.getSelectedIndex();

                if (selectedIndex + 1 < taskListModel.getSize()) {
                    swapTask(selectedIndex, selectedIndex + 1);
                    taskList.setSelectedIndex(selectedIndex + 1);
                    taskList.updateUI();
                }
            }
        });
    }

    private void createUIComponents() {
        taskListModel = new DefaultListModel();
        taskList = new JList(taskListModel);
    }

    private void swapTask(int index1, int index2) {
        Task temp = (Task)taskListModel.get(index1);
        taskListModel.set(index1, taskListModel.get(index2));
        taskListModel.set(index2, temp);
    }

    private void updateProgress() {
        progressBar.setValue(taskRunner.getProgressPercentage());
        progressBar.updateUI();

        progressLog.setText(taskRunner.getActivity());
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
