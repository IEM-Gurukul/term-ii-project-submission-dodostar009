# Student Attendance Management System

## Problem Statement
A Java-based desktop application to manage student attendance efficiently. Teachers can add students, mark attendance subject-wise, and generate attendance reports. The system alerts when attendance drops below 75%.

## Target User
Teachers and administrators at educational institutions.

## Core Features
- Add and manage students
- Mark attendance per subject
- Generate reports by student or subject
- Automatic alert when attendance falls below 75%
- Data persistence using file serialization

## OOP Concepts Used
- Abstraction: Abstract class Person defines common structure
- Inheritance: Student and Teacher extend Person
- Polymorphism: getInfo() overridden in each subclass
- Encapsulation: All fields private with getters/setters
- Observer Pattern: AlertObserver notifies on low attendance
- Strategy Pattern: Switchable report generation strategies
- Exception Handling: Custom exceptions for all error cases
- File Handling: Serialization-based data persistence

## How to Run
1. Open project in IntelliJ IDEA
2. Mark src as Sources Root
3. Run MainFrame.java
