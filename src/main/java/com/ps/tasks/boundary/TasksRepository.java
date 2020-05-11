package com.ps.tasks.boundary;

import com.ps.tasks.entity.Task;

import java.util.List;

public interface TasksRepository {
    void add(Task task);

    List<Task> fetchAll();

    Task fetchById(Long id);

    void delete(Long id);

    void update(Long id, String title, String description);

    void save(Task task);

    List<Task> findByTitle(String title);

    List<Task> findWithAttachments();

    void addAll(Iterable<Task> tasks);
}
