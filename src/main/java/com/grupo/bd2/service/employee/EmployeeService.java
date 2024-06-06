package com.grupo.bd2.service.employee;

import com.grupo.bd2.dto.EmployeeResponseDto;
import com.grupo.bd2.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDto> getAllEmployees();
    EmployeeResponseDto getEmployeeById(Long id);
    EmployeeResponseDto createOrUpdateEmployee(Employee employee);
}
