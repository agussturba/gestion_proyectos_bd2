package com.grupo.bd2.controller;

import com.grupo.bd2.dto.EmployeeResponseDto;
import com.grupo.bd2.dto.ProjectResponseDto;
import com.grupo.bd2.model.Employee;
import com.grupo.bd2.model.Project;
import com.grupo.bd2.service.employee.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployee(){
        return ResponseEntity.ok().body(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok().body(employeeService.getEmployeeById(id));
    }
    @PostMapping
    public ResponseEntity<EmployeeResponseDto> saveEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok().body(employeeService.createOrUpdateEmployee(employee));
    }
    @PutMapping
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok().body(employeeService.createOrUpdateEmployee(employee));
    }
}
