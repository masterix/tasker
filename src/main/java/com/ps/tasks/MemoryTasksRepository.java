package com.ps.tasks;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MemoryTasksRepository implements TasksRepository {

    private Set<Task> tasks = new HashSet<>();

    @Override
    public void add(Task task) {
        tasks.add(task);
    }

    @Override
    public List<Task> fetchAll() {
        return new ArrayList<>(tasks);
    }
}
