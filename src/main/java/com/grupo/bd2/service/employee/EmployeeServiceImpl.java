package com.grupo.bd2.service.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo.bd2.dto.EmployeeResponseDto;
import com.grupo.bd2.exceptions.NotFoundException;
import com.grupo.bd2.model.Employee;
import com.grupo.bd2.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(project -> objectMapper.convertValue(project, EmployeeResponseDto.class))
                .toList();
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(project -> objectMapper.convertValue(project,EmployeeResponseDto.class))
                .orElseThrow(() -> new NotFoundException("Employee not found"));    }

    @Override
    public EmployeeResponseDto createOrUpdateEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return objectMapper.convertValue(savedEmployee, EmployeeResponseDto.class);    }
}
