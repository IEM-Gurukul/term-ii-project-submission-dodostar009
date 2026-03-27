package com.attendance.repository;

import com.attendance.model.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements DataRepository<Student> {
    private String filePath;

    public StudentRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(List<Student> students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filePath))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> load() {
        File file = new File(filePath);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filePath))) {
            return (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading students: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}