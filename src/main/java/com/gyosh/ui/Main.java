package com.gyosh.ui;

import javax.swing.*;

public class Main {
    private JList list1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton vButton;
    private JButton button7;
    private JProgressBar progressBar1;
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton button5;
    private JTextField textField2;
    private JButton button6;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
