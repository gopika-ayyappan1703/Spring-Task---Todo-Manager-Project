package com.example.todomanager.repository;

import com.example.todomanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

List<Task> findByPriorityIgnoreCase(String priority);

}
