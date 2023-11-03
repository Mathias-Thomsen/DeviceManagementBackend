package com.example.devicemanagement.controllers;


import com.example.devicemanagement.entities.Employee;
import com.example.devicemanagement.exceptions.CustomException;
import com.example.devicemanagement.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Hent alle medarbejdere
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.findAll();
        return ResponseEntity.ok(employees);
    }

    // Hent en specifik medarbejder efter ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
        return employeeService.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomException("Employee not found with id: " + id));
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomException("Problem with post employee"));
    }

    // Opdater en eksisterende medarbejder
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee updatedEmployee) {
        return employeeService.getById(id)
                .map(employee -> ResponseEntity.ok(employeeService.updateEmployee(updatedEmployee)))
                .orElseThrow(() -> new CustomException("Employee not found with ID: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        return employeeService.getById(id)
                .map(employee -> {
                    employeeService.deleteEmployee(employee);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseThrow(() -> new CustomException("Employee not found with ID: " + id));
    }
}

