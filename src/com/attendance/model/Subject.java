package com.attendance.model;

public class Subject implements java.io.Serializable {
    private String code;
    private String name;
    private int totalClasses;

    public Subject(String code, String name) {
        this.code = code;
        this.name = name;
        this.totalClasses = 0;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getTotalClasses() {
        return totalClasses;
    }

    public void incrementTotalClasses() {
        this.totalClasses++;
    }

    @Override
    public String toString() {
        return code + " - " + name;
    }
}
