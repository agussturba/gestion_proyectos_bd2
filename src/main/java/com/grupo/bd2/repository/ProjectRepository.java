package com.grupo.bd2.repository;

import com.grupo.bd2.model.Employee;
import com.grupo.bd2.model.Project;
import com.grupo.bd2.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByTask(Task task);
    Optional<Project> findByEmployeesContainingAndIsActive(Employee employee, Boolean isActive);
}
