package com.attendance.strategy;

import com.attendance.model.AttendanceRecord;
import com.attendance.model.Student;
import java.util.List;

public class SubjectReportStrategy implements ReportStrategy {

    @Override
    public String generate(List<Student> students, List<AttendanceRecord> records) {
        StringBuilder report = new StringBuilder();
        report.append("=== Attendance Report by Subject ===\n\n");

        List<String> subjects = records.stream()
                .map(AttendanceRecord::getSubjectCode)
                .distinct()
                .toList();

        for (String subject : subjects) {
            long total = records.stream()
                    .filter(r -> r.getSubjectCode().equals(subject))
                    .count();
            long present = records.stream()
                    .filter(r -> r.getSubjectCode().equals(subject) && r.isPresent())
                    .count();

            double percentage = total == 0 ? 0 : (present * 100.0 / total);
            report.append("Subject: ").append(subject).append("\n");
            report.append("  Classes held: ").append(total);
            report.append("  Present: ").append(present);
            report.append("  Average attendance: ").append(String.format("%.1f", percentage)).append("%\n\n");
        }
        return report.toString();
    }
}
