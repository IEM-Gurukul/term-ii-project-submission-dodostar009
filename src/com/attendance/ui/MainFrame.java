package com.attendance.ui;

import com.attendance.controller.MainController;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private MainController controller;
    private AttendancePanel attendancePanel;
    private ReportPanel reportPanel;
    private StudentPanel studentPanel;

    public MainFrame() {
        this.controller = new MainController();
        setTitle("Student Attendance Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JTabbedPane tabbedPane = new JTabbedPane();

        studentPanel = new StudentPanel(controller);
        attendancePanel = new AttendancePanel(controller);
        reportPanel = new ReportPanel(controller);

        tabbedPane.addTab("Students", studentPanel);
        tabbedPane.addTab("Mark Attendance", attendancePanel);
        tabbedPane.addTab("Reports", reportPanel);

        add(tabbedPane, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel(
                "Student Attendance Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}