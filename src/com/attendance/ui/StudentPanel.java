package com.attendance.ui;

import com.attendance.controller.MainController;
import javax.swing.*;
import java.awt.*;

public class StudentPanel extends JPanel {
    private MainController controller;
    private JTextField idField, nameField, rollField, deptField;
    private JTextArea outputArea;

    public StudentPanel(MainController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        initUI();
    }

    private void initUI() {
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        idField = new JTextField();
        nameField = new JTextField();
        rollField = new JTextField();
        deptField = new JTextField();

        formPanel.add(new JLabel("Student ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Roll Number:"));
        formPanel.add(rollField);
        formPanel.add(new JLabel("Department:"));
        formPanel.add(deptField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> addStudent());
        formPanel.add(new JLabel());
        formPanel.add(addButton);

        outputArea = new JTextArea(5, 40);
        outputArea.setEditable(false);
        outputArea.setBorder(BorderFactory.createTitledBorder("Output"));

        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
    }

    private void addStudent() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String roll = rollField.getText().trim();
        String dept = deptField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || roll.isEmpty() || dept.isEmpty()) {
            outputArea.setText("Error: All fields are required.");
            return;
        }

        String result = controller.addStudent(id, name, roll, dept);
        outputArea.setText(result);
        idField.setText("");
        nameField.setText("");
        rollField.setText("");
        deptField.setText("");
    }
}