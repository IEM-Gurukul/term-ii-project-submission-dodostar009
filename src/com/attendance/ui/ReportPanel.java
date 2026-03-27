package com.attendance.ui;

import com.attendance.controller.MainController;
import javax.swing.*;
import java.awt.*;

public class ReportPanel extends JPanel {
    private MainController controller;
    private JTextArea reportArea;

    public ReportPanel(MainController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        initUI();
    }

    private void initUI() {
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton studentReportBtn = new JButton("Report by Student");
        JButton subjectReportBtn = new JButton("Report by Subject");

        studentReportBtn.addActionListener(e -> {
            reportArea.setText(controller.generateStudentReport());
        });

        subjectReportBtn.addActionListener(e -> {
            reportArea.setText(controller.generateSubjectReport());
        });

        buttonPanel.add(studentReportBtn);
        buttonPanel.add(subjectReportBtn);

        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        reportArea.setBorder(BorderFactory.createTitledBorder("Report Output"));

        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(reportArea), BorderLayout.CENTER);
    }
}
