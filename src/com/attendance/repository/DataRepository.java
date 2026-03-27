package com.attendance.repository;

import java.util.List;

public interface DataRepository<T> {
    void save(List<T> items);
    List<T> load();
}