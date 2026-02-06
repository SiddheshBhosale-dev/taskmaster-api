package com.TaskMaster.taskmaster_api.repository;

import com.TaskMaster.taskmaster_api.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
