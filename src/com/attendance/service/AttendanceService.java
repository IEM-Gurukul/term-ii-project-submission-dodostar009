package com.attendance.service;

import com.attendance.exception.DuplicateEntryException;
import com.attendance.exception.InvalidAttendanceException;
import com.attendance.exception.StudentNotFoundException;
import com.attendance.model.AttendanceRecord;
import com.attendance.model.Student;
import com.attendance.model.Subject;
import com.attendance.observer.AttendanceObserver;
import com.attendance.repository.AttendanceRepository;
import com.attendance.repository.StudentRepository;
import com.attendance.strategy.ReportStrategy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceService {
    private List<Student> students;
    private List<Subject> subjects;
    private List<AttendanceRecord> records;
    private List<AttendanceObserver> observers;
    private ReportStrategy reportStrategy;
    private StudentRepository studentRepo;
    private AttendanceRepository attendanceRepo;

    public AttendanceService() {
        this.studentRepo = new StudentRepository("students.dat");
        this.attendanceRepo = new AttendanceRepository("attendance.dat");
        this.students = studentRepo.load();
        this.records = attendanceRepo.load();
        this.subjects = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public void addObserver(AttendanceObserver observer) {
        observers.add(observer);
    }

    public void setReportStrategy(ReportStrategy strategy) {
        this.reportStrategy = strategy;
    }

    public void addStudent(Student student) {
        for (Student s : students) {
            if (s.getId().equals(student.getId())) {
                throw new DuplicateEntryException(
                        "Student with ID " + student.getId() + " already exists");
            }
        }
        students.add(student);
        studentRepo.save(students);
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public void markAttendance(String studentId, String subjectCode, boolean present) {
        Student found = null;
        for (Student s : students) {
            if (s.getId().equals(studentId)) {
                found = s;
                break;
            }
        }
        if (found == null) {
            throw new StudentNotFoundException(studentId);
        }
        if (subjectCode == null || subjectCode.isEmpty()) {
            throw new InvalidAttendanceException("Subject code cannot be empty");
        }

        AttendanceRecord record = new AttendanceRecord(
                studentId, subjectCode, LocalDate.now(), present);
        records.add(record);
        attendanceRepo.save(records);

        long total = records.stream()
                .filter(r -> r.getStudentId().equals(studentId)
                        && r.getSubjectCode().equals(subjectCode))
                .count();
        long presentCount = records.stream()
                .filter(r -> r.getStudentId().equals(studentId)
                        && r.getSubjectCode().equals(subjectCode) && r.isPresent())
                .count();

        double percentage = total == 0 ? 0 : (presentCount * 100.0 / total);
        for (AttendanceObserver observer : observers) {
            observer.update(found.getName(), subjectCode, percentage);
        }
    }

    public String generateReport() {
        if (reportStrategy == null) {
            return "No report strategy selected.";
        }
        return reportStrategy.generate(students, records);
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public List<AttendanceRecord> getRecords() {
        return records;
    }
}

