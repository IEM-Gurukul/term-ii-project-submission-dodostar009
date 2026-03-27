package com.attendance.model;

import java.time.LocalDate;

public class AttendanceRecord {
    private String studentId;
    private String subjectCode;
    private LocalDate date;
    private boolean present;

    public AttendanceRecord(String studentId, String subjectCode,
                            LocalDate date, boolean present) {
        this.studentId = studentId;
        this.subjectCode = subjectCode;
        this.date = date;
        this.present = present;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isPresent() {
        return present;
    }

    @Override
    public String toString() {
        return studentId + " | " + subjectCode + " | " +
                date + " | " + (present ? "Present" : "Absent");
    }
}
