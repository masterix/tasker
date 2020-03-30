package com.ps.tasks;

import java.util.List;

public interface TasksRepository {
    void add(Task task);

    List<Task> fetchAll();

    Task fetchById(Long id);

    void delete(Long id);
}
