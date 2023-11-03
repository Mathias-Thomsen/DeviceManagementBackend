package com.example.devicemanagement.services;


import com.example.devicemanagement.entities.Employee;
import com.example.devicemanagement.repositories.EmployeeRepository;
import com.example.devicemanagement.services.idService.EmployeeIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeIdService employeeIdService;

    // Save employee with the id logik we made in employeeIdService.
    public Optional<Employee> saveEmployee(Employee employee) {
        employee.setFullname(generateFullname(employee)); //Set fullname from the firstname, middlename(if present) and lastname from user input.
        String nextId = employeeIdService.generateNextEmployeeId(employee.getEmployeeType().getAbbreviation());
        employee.setId(nextId);
        employeeRepository.save(employee);
        return Optional.of(employee);
    }

    // Collect fullname
    public String generateFullname(Employee employee) {
        String firstname = employee.getFirstname();
        String middleName = employee.getMiddleName();
        String lastname = employee.getLastname();

        if (middleName != null && !middleName.isEmpty()) {
            return firstname + " " + middleName + " " + lastname;
        } else {
            return firstname + " " + lastname;
        }
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getById(String id) {
        return employeeRepository.findById(id);

    }

    public Employee updateEmployee(Employee updatedEmployee) {
        return employeeRepository.save(updatedEmployee);
    }

    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }
}

