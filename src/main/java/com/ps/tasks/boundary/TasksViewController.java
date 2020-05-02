package com.ps.tasks.boundary;

import com.ps.tasks.control.TasksService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

        return "home";
    }
}
