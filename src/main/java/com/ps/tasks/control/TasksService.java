package com.ps.tasks.control;

import com.ps.Clock;
import com.ps.tasks.boundary.TasksRepository;
import com.ps.tasks.entity.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TasksService {
    private final TasksRepository tasksRepository;
    private final Clock clock;
    private final AtomicLong taskIdGenerator = new AtomicLong(0);

    public TasksService(TasksRepository tasksRepository, Clock clock) {
        this.tasksRepository = tasksRepository;
        this.clock = clock;
    }

    public void addTask(String title, String description) {
        tasksRepository.add(
                new Task(
                        taskIdGenerator.getAndIncrement(),
                        title,
                        description,
                        this.clock.time()
                )
        );
    }

    public void updateTask(Long id, String title, String description) {
        tasksRepository.update(id, title, description);
    }
}
