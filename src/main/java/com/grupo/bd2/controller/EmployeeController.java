package com.grupo.bd2.controller;

import com.grupo.bd2.dto.EmployeeResponseDto;
import com.grupo.bd2.model.Employee;
import com.grupo.bd2.service.employee.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    @Operation(summary = "Get all employees")
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployee(){
        return ResponseEntity.ok().body(employeeService.getAllEmployees());
    }
    @Operation(summary = "Get a employee by its id")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok().body(employeeService.getEmployeeById(id));
    }
    @Operation(summary = "Create a employee")
    @PostMapping
    public ResponseEntity<EmployeeResponseDto> saveEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok().body(employeeService.createOrUpdateEmployee(employee));
    }
    @Operation(summary = "Update a employee")
    @PutMapping
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok().body(employeeService.createOrUpdateEmployee(employee));
    }
}
