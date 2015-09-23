package com.gyosh.ui;

import javax.swing.*;
import java.awt.event.*;

public class TaskSelector extends JDialog {
    private JPanel contentPane;
    private JButton selectButton;
    private JButton cancelButton;
    private JList tasks;
    private JLabel taskDescription;
    private JPanel mainPanel;
    private JPanel requirementPanel;

    public TaskSelector() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(selectButton);

        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        TaskSelector dialog = new TaskSelector();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
