package com.TaskMaster.taskmaster_api.controller;

import com.TaskMaster.taskmaster_api.model.Task;
import com.TaskMaster.taskmaster_api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Add a new task
    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    // Get all tasks for a project
    @GetMapping("/project/{projectId}")
    public List<Task> getTasksByProject(@PathVariable Long projectId) {
        return taskService.getTasksByProject(projectId);
    }

 // Search tasks by title or description with pagination and sorting
    @GetMapping("/search")
    public List<Task> searchTasks(
            @RequestParam String searchTerm,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "dueDate") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction
    ) {
        return taskService.searchTasks(searchTerm, page, size, sortBy, direction);
    }
    
 // Sort tasks by dueDate or priority
    @GetMapping("/sort")
    public List<Task> sortTasks(
            @RequestParam(defaultValue = "dueDate") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction
    ) {
        return taskService.sortTasks(sortBy, direction);
    }
    
    // Update a task
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    // Delete a task
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "Task deleted successfully";
    }

}
