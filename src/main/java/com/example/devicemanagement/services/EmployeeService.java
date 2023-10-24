package com.example.devicemanagement.services;


import com.example.devicemanagement.entities.Employee;
import com.example.devicemanagement.repositories.EmployeeRepository;
import com.example.devicemanagement.services.idService.EmployeeIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeIdService employeeIdService;

    public void saveEmployee(Employee employee) {
        employee.setFullname(generateFullname(employee));
        String nextId = employeeIdService.generateNextEmployeeId(employee.getEmployeeType());
        employee.setId(nextId);
        employeeRepository.save(employee);
    }

    private String generateFullname(Employee employee) {
        String firstname = employee.getFirstname();
        String middleName = employee.getMiddleName();
        String lastname = employee.getLastname();

        if (middleName != null && !middleName.isEmpty()) {
            return firstname + " " + middleName + " " + lastname;
        } else {
            return firstname + " " + lastname;
        }
    }
}

