package com.example.sqlite.repository;

import com.example.sqlite.models.Task;
import com.example.sqlite.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
    Optional<Task> findByIdAndUser(Long id, User user); // Ensure this method exists
}
