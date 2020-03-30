package com.ps.tasks.control;

import com.ps.tasks.boundary.TasksRepository;
import com.ps.tasks.entity.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TasksService {
    private final TasksRepository tasksRepository;
    private final AtomicLong taskIdGenerator = new AtomicLong(0);

    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public void addTask(String title, String description) {
        tasksRepository.add(
                new Task(
                        taskIdGenerator.getAndIncrement(),
                        title,
                        description,
                        LocalDateTime.now()
                )
        );
    }
}
