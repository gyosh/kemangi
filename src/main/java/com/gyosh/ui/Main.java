package com.gyosh.ui;

import javax.swing.*;

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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Kemangi");
        frame.setContentPane(new Main().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
