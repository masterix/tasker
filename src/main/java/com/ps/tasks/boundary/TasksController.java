package com.ps.tasks.boundary;

import com.ps.Clock;
import com.ps.tasks.control.TasksService;
import com.ps.tasks.entity.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RestController
@RequestMapping(path = "/api/tasks")
public class TasksController {

    private final TasksRepository tasksRepository;

    private final TasksService tasksService;

    @Autowired
    public TasksController(TasksRepository tasksRepository, TasksService tasksService) {
        this.tasksRepository = tasksRepository;
        this.tasksService = tasksService;
    }

    @PostConstruct
    private void init() {
        tasksService.addTask("Title 1", "desc 1");
        tasksService.addTask("Title 2", "desc 2");
        tasksService.addTask("Title 3", "desc 3");
    }

    @GetMapping
    public List<TaskResponse> index() {
        log.info("Fetching all tasks...");
        return tasksRepository.fetchAll()
                .stream()
                .map(this::transformToTaskResponse)
                .collect(toList());
    }

    @GetMapping(path = "/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        log.info("Fetching task by id: {}", id);

        return transformToTaskResponse(tasksRepository.fetchById(id));
    }

    @PostMapping
    public void addTask(@RequestBody CreateTaskRequest task) {
        log.info("Storing new tasks {}", task);
        tasksService.addTask(task.getTitle(), task.getDescription());
    }

    @DeleteMapping(path = "/{id}")
    public void deleteTask(@PathVariable Long id) {
        log.info("Deleting by id: {}", id);
        tasksRepository.delete(id);
    }

    @PutMapping(path = "/{id}")
    public void updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest updateTaskRequest) {
        log.info("Updating a task {}", id);
        tasksService.updateTask(id, updateTaskRequest.title, updateTaskRequest.description);
    }

    private TaskResponse transformToTaskResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCreatedAt()
        );
    }
}
