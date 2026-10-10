package com.example.todomanager.service;

import com.example.todomanager.model.Task;
import com.example.todomanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task not found with ID: " + id));
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task task = getTaskById(id);

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setCompleted(updatedTask.isCompleted());
        task.setPriority(updatedTask.getPriority());

        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

    public Task toggleStatus(Long id) {
        Task task = getTaskById(id);
        task.setCompleted(!task.isCompleted());
        return taskRepository.save(task);
    }

    public List<Task> getTasksByPriority(String priority) {
        return taskRepository.findByPriorityIgnoreCase(priority);
    }
}
