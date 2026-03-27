package com.attendance.strategy;

import com.attendance.model.AttendanceRecord;
import com.attendance.model.Student;
import java.util.List;

public class StudentReportStrategy implements ReportStrategy {

    @Override
    public String generate(List<Student> students, List<AttendanceRecord> records) {
        StringBuilder report = new StringBuilder();
        report.append("=== Attendance Report by Student ===\n\n");

        for (Student student : students) {
            long total = records.stream()
                    .filter(r -> r.getStudentId().equals(student.getId()))
                    .count();
            long present = records.stream()
                    .filter(r -> r.getStudentId().equals(student.getId()) && r.isPresent())
                    .count();

            double percentage = total == 0 ? 0 : (present * 100.0 / total);
            report.append(student.getInfo()).append("\n");
            report.append("  Classes: ").append(present).append("/").append(total);
            report.append("  Attendance: ").append(String.format("%.1f", percentage)).append("%\n\n");
        }
        return report.toString();
    }
}