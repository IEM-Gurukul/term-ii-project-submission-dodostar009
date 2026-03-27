package com.attendance.model;

public class Teacher extends Person {
    private String employeeId;
    private String department;

    public Teacher(String id, String name, String employeeId, String department) {
        super(id, name);
        this.employeeId = employeeId;
        this.department = department;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String getInfo() {
        return "Teacher[" + employeeId + "] " + getName() + " - " + department;
    }
}