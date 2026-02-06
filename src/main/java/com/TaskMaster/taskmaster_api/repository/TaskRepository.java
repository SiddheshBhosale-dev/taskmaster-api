package com.TaskMaster.taskmaster_api.repository;

import com.TaskMaster.taskmaster_api.model.Task;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Sort;


import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId);
    
 // Search tasks by title or description (case-insensitive)
    @Query("SELECT t FROM Task t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(t.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Task> searchTasks(String searchTerm, PageRequest pageRequest);

    // Sort tasks by dueDate or priority
    List<Task> findAll(Sort sort);
}
