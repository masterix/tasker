package com.ps.tasks.boundary;

import com.ps.tasks.entity.Task;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
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
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
    }

    @Override
    public void delete(Long id) {
        findById(id)
                .ifPresent(task -> tasks.remove(task));
    }

    @Override
    public void update(Long id, String title, String description) {
        findById(id).ifPresent(task -> {
            task.setTitle(title);
            task.setDescription(description);
        });
    }

    private Optional<Task> findById(Long id) {
        return tasks.stream()
                .filter(task -> id.equals(task.getId()))
                .findFirst();
    }
}
