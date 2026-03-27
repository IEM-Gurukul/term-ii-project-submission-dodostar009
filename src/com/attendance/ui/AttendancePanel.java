package com.attendance.ui;

import com.attendance.controller.MainController;
import javax.swing.*;
import java.awt.*;

public class AttendancePanel extends JPanel {
    private MainController controller;
    private JTextField studentIdField, subjectField;
    private JCheckBox presentCheckBox;
    private JTextArea outputArea;

    public AttendancePanel(MainController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        initUI();
    }

    private void initUI() {
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        studentIdField = new JTextField();
        subjectField = new JTextField();
        presentCheckBox = new JCheckBox("Present");
        presentCheckBox.setSelected(true);

        formPanel.add(new JLabel("Student ID:"));
        formPanel.add(studentIdField);
        formPanel.add(new JLabel("Subject Code:"));
        formPanel.add(subjectField);
        formPanel.add(new JLabel("Attendance:"));
        formPanel.add(presentCheckBox);

        JButton markButton = new JButton("Mark Attendance");
        markButton.addActionListener(e -> markAttendance());
        formPanel.add(new JLabel());
        formPanel.add(markButton);

        outputArea = new JTextArea(5, 40);
        outputArea.setEditable(false);
        outputArea.setBorder(BorderFactory.createTitledBorder("Output"));

        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
    }

    private void markAttendance() {
        String studentId = studentIdField.getText().trim();
        String subject = subjectField.getText().trim();
        boolean present = presentCheckBox.isSelected();

        if (studentId.isEmpty() || subject.isEmpty()) {
            outputArea.setText("Error: All fields are required.");
            return;
        }

        String result = controller.markAttendance(studentId, subject, present);
        outputArea.setText(result);
        studentIdField.setText("");
        subjectField.setText("");
        presentCheckBox.setSelected(true);
    }
}