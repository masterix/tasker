package com.ps.tasks.boundary;

import com.ps.tasks.control.TasksService;
import com.ps.tasks.entity.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping(path = "/")
public class TasksViewController {
    private final TasksService tasksService;
    private final StorageService storageService;

    public TasksViewController(TasksService tasksService, StorageService storageService) {
        this.tasksService = tasksService;
        this.storageService = storageService;
    }

    @GetMapping(path = "/")
    public String home(Model model) {
        model.addAttribute("tasks", tasksService.fetchAll());
        model.addAttribute("newTask", new CreateTaskRequest());

        return "home";
    }

    @PostMapping(path = "/tasks")
    public String addTask(@ModelAttribute("newTask") CreateTaskRequest request, @RequestParam("file") MultipartFile attachment) throws IOException {
        log.info("add task {}", request);
        Task task = tasksService.addTask(request.getTitle(), request.getDescription());
        if (!attachment.isEmpty()) {
            String filename = storageService.saveFile(task.getId(), attachment);
            tasksService.addAttachmentToTask(task.getId(), filename, request.getAttachmentComment());
        }

        return "redirect:/";
    }

    @PostMapping(path = "/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        tasksService.delete(id);

        return "redirect:/";
    }

}
