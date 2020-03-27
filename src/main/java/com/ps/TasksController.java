package com.ps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return tasksRepository.fetchAll();
    }

    @PostMapping
    public void addTask(@RequestBody Task task) {
        tasksRepository.add(task);
    }
}
