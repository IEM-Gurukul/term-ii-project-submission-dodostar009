package com.attendance.model;

public class Student extends Person implements java.io.Serializable {
    private String rollNumber;
    private String department;

    public Student(String id, String name, String rollNumber, String department) {
        super(id, name);
        this.rollNumber = rollNumber;
        this.department = department;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String getInfo() {
        return "Student[" + getRollNumber() + "] " + getName() + " - " + department;
    }
}