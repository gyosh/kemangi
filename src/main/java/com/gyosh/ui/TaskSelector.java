package com.gyosh.ui;

import com.gyosh.worker.factory.CaseFoldingFactory;
import com.gyosh.worker.factory.OwnStopWordRemovalFactory;
import com.gyosh.worker.factory.TaskFactory;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TaskSelector extends JDialog {
    private JDialog jDialog;
    private JPanel contentPane;
    private JButton selectButton;
    private JButton cancelButton;
    private JList taskFactoryList;
    private JLabel taskDescription;
    private JPanel mainPanel;
    private JPanel parameterPanel;

    public TaskSelector() {
        initUI();
    }

    private void initUI() {
        jDialog = this;

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

    private void createUIComponents() {
        parameterPanel = new JPanel();
    }
}
