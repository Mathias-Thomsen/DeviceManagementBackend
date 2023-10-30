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

    // Save employee with the id logik we made in employeeIdService.
    public void saveEmployee(Employee employee) {
        employee.setFullname(generateFullname(employee)); //Set fullname from the firstname, middlename(if present) and lastname from user input.
        String nextId = employeeIdService.generateNextEmployeeId(employee.getEmployeeType().getAbbreviation());
        employee.setId(nextId);
        employeeRepository.save(employee);
    }

    // Collect fullname
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

