package com.attendance.strategy;

import java.util.List;
import com.attendance.model.AttendanceRecord;
import com.attendance.model.Student;

public interface ReportStrategy {
    String generate(List<Student> students, List<AttendanceRecord> records);
}