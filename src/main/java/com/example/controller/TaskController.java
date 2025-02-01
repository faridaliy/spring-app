package com.example.controller;

import com.example.sqlite.models.Task;
import com.example.sqlite.models.User;
import com.example.sqlite.service.TaskService;
import com.example.sqlite.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping
    public String getTasks(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        model.addAttribute("tasks", taskService.getTasksByUser(user));
        model.addAttribute("task", new Task());
        return "tasks";
    }

    @PostMapping
    public String createTask(@Valid @ModelAttribute("task") Task task,
                             BindingResult result,
                             @AuthenticationPrincipal UserDetails userDetails) {
        if (result.hasErrors()) {
            return "tasks";
        }

        User user = userService.getUserByUsername(userDetails.getUsername());
        taskService.createTask(task, user);
        return "redirect:/tasks";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id,
                             @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        taskService.deleteTask(id, user);
        return "redirect:/tasks";
    }
}