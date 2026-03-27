package com.attendance.observer;

public interface AttendanceObserver {
    void update(String studentName, String subject, double attendancePercentage);
}