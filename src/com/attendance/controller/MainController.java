package com.attendance.controller;

import com.attendance.exception.DuplicateEntryException;
import com.attendance.exception.StudentNotFoundException;
import com.attendance.model.Student;
import com.attendance.model.Subject;
import com.attendance.observer.AlertObserver;
import com.attendance.service.AttendanceService;
import com.attendance.strategy.StudentReportStrategy;
import com.attendance.strategy.SubjectReportStrategy;

public class MainController {
    private AttendanceService service;

    public MainController() {
        this.service = new AttendanceService();
        this.service.addObserver(new AlertObserver(75.0));
    }

    public String addStudent(String id, String name, String roll, String dept) {
        try {
            Student student = new Student(id, name, roll, dept);
            service.addStudent(student);
            return "Student added successfully: " + name;
        } catch (DuplicateEntryException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String addSubject(String code, String name) {
        Subject subject = new Subject(code, name);
        service.addSubject(subject);
        return "Subject added: " + name;
    }

    public String markAttendance(String studentId, String subjectCode, boolean present) {
        try {
            service.markAttendance(studentId, subjectCode, present);
            return "Attendance marked for student: " + studentId;
        } catch (StudentNotFoundException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String generateStudentReport() {
        service.setReportStrategy(new StudentReportStrategy());
        return service.generateReport();
    }

    public String generateSubjectReport() {
        service.setReportStrategy(new SubjectReportStrategy());
        return service.generateReport();
    }

    public AttendanceService getService() {
        return service;
    }
}
