package com.gyosh.ui;

import com.gyosh.worker.factory.*;
import com.gyosh.worker.task.Task;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;

public class TaskSelector extends JDialog {
    private JPanel contentPane;
    private JButton selectButton;
    private JButton cancelButton;
    private JList taskFactoryList;
    private JLabel taskDescription;
    private JPanel mainPanel;
    private JPanel parameterPanel;

    private TaskFactory selectedTaskFactory;
    private TaskFactory[] taskFactories = {
        new CaseFoldingFactory(),
        new NonAlphaNumericRemovalFactory(),
        new OwnStopWordRemovalFactory(),
        new StopWordRemovalFactory(),
        new StemFactory()
    };

    private Task taskCreated;

    public TaskSelector() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(selectButton);

        taskFactoryList.setListData(taskFactories);
        addListeners();
    }

    private void addListeners() {
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOk();
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

        taskFactoryList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                if (!listSelectionEvent.getValueIsAdjusting()) {
                    int index = taskFactoryList.getSelectedIndex();
                    selectedTaskFactory = taskFactories[index];
                    updateFieldOnSelection();
                }
            }
        });
    }

    public Task getCreatedTask() {
        return taskCreated;
    }

    private void onOk() {
        taskCreated = selectedTaskFactory.createTask();
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private void createUIComponents() {
        parameterPanel = new JPanel();
    }

    private void updateFieldOnSelection() {
        taskDescription.setText(selectedTaskFactory.getDescription());

        parameterPanel.removeAll();
        parameterPanel.add(selectedTaskFactory.getParameterPanel());

        parameterPanel.revalidate();
        parameterPanel.repaint();
    }
}
