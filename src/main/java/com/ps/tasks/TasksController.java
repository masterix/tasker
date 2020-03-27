package com.ps.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/")
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

    @PostMapping
    public void addTask(@RequestBody Task task) {
        log.info("Storing new tasks {}", task);
        tasksRepository.add(task);
    }
}
