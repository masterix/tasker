package com.ps.tasks.boundary;

import com.ps.exceptions.NotFoundException;
import com.ps.tasks.control.TasksService;
import com.ps.tasks.entity.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
@RestController
@RequestMapping(path = "/api/tasks")
public class TasksController {

    private final StorageService storageService;
    private final TasksService tasksService;

    @Autowired
    public TasksController(StorageService storageService, TasksService tasksService) {
        this.storageService = storageService;
        this.tasksService = tasksService;
    }

    @PostConstruct
    private void init() {
        tasksService.addTask("Title 1", "desc 1");
        tasksService.addTask("Title 2", "desc 2");
        tasksService.addTask("Title 3", "desc 3");
    }

    @GetMapping
    public List<TaskResponse> index(@RequestParam Optional<String> query) {
        log.info("Fetching all tasks with filter {}", query);

        return query.map(tasksService::fetchAllByQuery)
                .orElseGet(tasksService::fetchAll)
                .stream()
                .map(this::transformToTaskResponse)
                .collect(toList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable Long id) {
        log.info("Fetching task by id: {}", id);
        try {
            return ResponseEntity
                    .ok()
                    .body(transformToTaskResponse(tasksService.fetchById(id)));
        } catch (NotFoundException exception) {
            log.error("Failed to get task", exception);

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(exception.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> addTask(@RequestBody CreateTaskRequest task) {
        log.info("Storing new tasks {}", task);
        tasksService.addTask(task.getTitle(), task.getDescription());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable Long id) {
        log.info("Deleting by id: {}", id);
        try {
            tasksService.delete(id);

            return ResponseEntity
                    .noContent()
                    .build();
        } catch (NotFoundException exception) {
            log.error("Failed to delete task", exception);

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(exception.getMessage());
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest updateTaskRequest) {
        log.info("Updating a task {}", id);
        try {
            tasksService.updateTask(id, updateTaskRequest.title, updateTaskRequest.description);

            return ResponseEntity.noContent().build();
        } catch (NotFoundException exception) {
            log.error("Failed to update", exception);

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(exception.getMessage());
        }
    }

    @GetMapping(path = "/{id}/attachments/{filename}")
    public ResponseEntity<Object> getAttachment(
            @PathVariable Long id,
            @PathVariable String filename,
            HttpServletRequest request
    ) throws IOException {
        Resource resource = storageService.loadFile(filename);
        String mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        if (null == mimeType) {
            mimeType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .body(resource);
    }

    @PostMapping(path = "/{id}/attachments")
    public ResponseEntity<Object> addAttachment(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        String filename = storageService.saveFile(id, file);
        tasksService.addAttachmentToTask(id, filename);

        return ResponseEntity.noContent().build();
    }

    private TaskResponse transformToTaskResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCreatedAt(),
                task.getAttachments()
        );
    }
}
