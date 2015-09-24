package com.gyosh.ui;

import com.gyosh.worker.factory.CaseFoldingFactory;
import com.gyosh.worker.factory.OwnStopWordRemovalFactory;
import com.gyosh.worker.factory.TaskFactory;
import com.gyosh.worker.task.*;

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
    private List<Task> tasks;

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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Kemangi");

        Main main = new Main();
        frame.setContentPane(main.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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
}
