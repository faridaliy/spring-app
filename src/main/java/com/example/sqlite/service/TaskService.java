package com.example.sqlite.service;

import com.example.sqlite.models.Task;
import com.example.sqlite.models.User;
import com.example.sqlite.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasksByUser(User user) {
        return taskRepository.findByUser(user);
    }

    public Task createTask(Task task, User user) {
        task.setUser(user);
        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId, User user) {
        Optional<Task> taskOptional = taskRepository.findByIdAndUser(taskId, user);
        taskOptional.ifPresent(taskRepository::delete);
    }
}
