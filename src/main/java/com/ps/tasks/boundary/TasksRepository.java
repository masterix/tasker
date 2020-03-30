package com.ps.tasks.boundary;

import com.ps.tasks.entity.Task;

import java.util.List;

public interface TasksRepository {
    void add(Task task);

    List<Task> fetchAll();

    Task fetchById(Long id);

    void delete(Long id);
}