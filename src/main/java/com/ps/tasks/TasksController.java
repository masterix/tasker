package com.ps.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/tasks")
public class TasksController {

    private final TasksRepository tasksRepository;

    @Autowired
    public TasksController(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @GetMapping
    public List<Task> index() {
        log.info("Fetching all tasks...");
        return tasksRepository.fetchAll();
    }

    @GetMapping(path = "/{id}")
    public Task getTaskById(@PathVariable Long id) {
        log.info("Fetching task by id: {}", id);

        return tasksRepository.fetchById(id);
    }

    @PostMapping
    public void addTask(@RequestBody Task task) {
        log.info("Storing new tasks {}", task);
        tasksRepository.add(task);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteTask(@PathVariable Long id) {
        log.info("Deleting by id: {}", id);
        tasksRepository.delete(id);
    }
}
