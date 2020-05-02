package com.ps.tasks.boundary;

import com.ps.tasks.control.TasksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping(path = "/")
public class TasksViewController {
    private final TasksService tasksService;

    public TasksViewController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping(path = "/")
    public String home(Model model) {
        model.addAttribute("tasks", tasksService.fetchAll());
        model.addAttribute("newTask", new CreateTaskRequest());

        return "home";
    }

    @PostMapping(path = "/tasks")
    public String addTask(@ModelAttribute("newTask") CreateTaskRequest request) {
        log.info("add task {}", request);
        tasksService.addTask(request.getTitle(), request.getDescription());

        return "redirect:/";
    }

    @PostMapping(path = "/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        tasksService.delete(id);

        return "redirect:/";
    }

}
