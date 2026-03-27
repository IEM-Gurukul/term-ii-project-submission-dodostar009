package com.attendance.repository;

import com.attendance.model.AttendanceRecord;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceRepository implements DataRepository<AttendanceRecord> {
    private String filePath;

    public AttendanceRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(List<AttendanceRecord> records) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filePath))) {
            oos.writeObject(records);
        } catch (IOException e) {
            System.out.println("Error saving records: " + e.getMessage());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<AttendanceRecord> load() {
        File file = new File(filePath);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filePath))) {
            return (List<AttendanceRecord>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading records: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
