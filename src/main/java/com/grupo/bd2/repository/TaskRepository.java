package com.grupo.bd2.repository;
import com.grupo.bd2.model.Employee;
import com.grupo.bd2.model.Task;
import com.grupo.bd2.model.TaskState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByFatherTask(Task fatherTask);
    List<Task> findByAssignedEmployeesAndTaskStateIn(Employee employee, List<TaskState> states);
}
