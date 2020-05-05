package com.ps.tasks.boundary;

import com.ps.exceptions.NotFoundException;
import com.ps.tasks.entity.Task;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryTasksRepository implements TasksRepository {

    private final Set<Task> tasks = new HashSet<>();

    @Override
    public void add(Task task) {
        tasks.add(task);
    }

    @Override
    public List<Task> fetchAll() {
        return new ArrayList<>(tasks);
    }

    @Override
    public Task fetchById(Long id) {
        return findById(id)
                .orElseThrow(() -> new NotFoundException("Task with id not found " + id));
    }

    @Override
    public void delete(Long id) {
        Task task = findById(id)
                .orElseThrow(() -> new NotFoundException("Task with id not found " + id));

        tasks.remove(task);
    }

    @Override
    public void update(Long id, String title, String description) {
        Task task = findById(id)
                .orElseThrow(() -> new NotFoundException("Task with id not found " + id));

        task.setTitle(title);
        task.setDescription(description);
    };

    @Override
    public void save(Task task) {
        tasks.add(task);
    }

    @Override
    public List<Task> findByTitle(String title) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Task> findWithAttachments() {
        throw new UnsupportedOperationException();
    }

    private Optional<Task> findById(Long id) {
        return tasks.stream()
                .filter(task -> id.equals(task.getId()))
                .findFirst();
    }
}
