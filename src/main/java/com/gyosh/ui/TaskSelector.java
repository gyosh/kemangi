package com.gyosh.ui;

import com.gyosh.worker.utility.Util;
import com.gyosh.worker.factory.*;
import com.gyosh.worker.task.Task;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

public class TaskSelector extends JDialog {
    private JPanel contentPane;
    private JButton selectButton;
    private JButton cancelButton;
    private JList taskFactoryList;
    private JTextPane taskDescription;
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
        List<String> inputErrors = selectedTaskFactory.validateInput();

        if (inputErrors.size() > 0) {
            JOptionPane.showMessageDialog(null, Util.join(inputErrors, "\n"));
            return;
        }

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
        JPanel panel = selectedTaskFactory.getParameterPanel();
        taskDescription.setText(selectedTaskFactory.getDescription());

        parameterPanel.removeAll();
        parameterPanel.add(panel);

        parameterPanel.revalidate();
        parameterPanel.repaint();
    }
}
