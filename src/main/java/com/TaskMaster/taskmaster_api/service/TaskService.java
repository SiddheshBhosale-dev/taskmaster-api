package com.TaskMaster.taskmaster_api.service;

import com.TaskMaster.taskmaster_api.model.Task;
import com.TaskMaster.taskmaster_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;


import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Add a new task
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }
    
 // Search tasks by title or description with pagination and sorting
    public List<Task> searchTasks(String searchTerm, int page, int size, String sortBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sortBy));
        return taskRepository.searchTasks(searchTerm, pageRequest);
    }
    
 // Sort tasks by dueDate or priority
    public List<Task> sortTasks(String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        return taskRepository.findAll(sort);
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByProject(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task) {
        task.setId(id);
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
