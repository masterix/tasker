package com.ps.tasks.control;

import com.ps.Clock;
import com.ps.tasks.boundary.TasksRepository;
import com.ps.tasks.entity.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

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

    public List<Task> fetchAll() {
        return tasksRepository.fetchAll();
    }

    public List<Task> fetchAllByQuery(String query) {
        return tasksRepository.fetchAll()
                .stream()
                .filter(task -> {
                    return task.getTitle().contains(query) ||
                            task.getDescription().contains(query);
                })
                .collect(Collectors.toList());
    }

    public Task fetchById(Long id) {
        return tasksRepository.fetchById(id);
    }

    public void delete(Long id) {
        tasksRepository.delete(id);
    }
}
