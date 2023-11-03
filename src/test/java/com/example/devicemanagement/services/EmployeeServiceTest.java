package com.example.devicemanagement.services;

import com.example.devicemanagement.entities.Employee;
import com.example.devicemanagement.enums.EmployeeType;
import com.example.devicemanagement.repositories.EmployeeRepository;
import com.example.devicemanagement.services.idService.EmployeeIdService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeIdService employeeIdService;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testSaveEmployee() {
        // Arrange
        Employee employee = new Employee();
        employee.setFirstname("John");
        employee.setLastname("Doe");
        employee.setEmployeeType(EmployeeType.TIMELONNET);

        // Angiv forventet resultat for generateNextEmployeeId-metoden
        when(employeeIdService.generateNextEmployeeId((EmployeeType.TIMELONNET.getAbbreviation())))
                .thenReturn("TL0001");

        when(employeeRepository.save(employee)).thenReturn(employee);

        // Act
        Optional<Employee> result = employeeService.saveEmployee(employee);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("TL0001", result.get().getId());
        assertEquals("John Doe", result.get().getFullname());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void testGenerateFullname() {
        // Arrange
        Employee employee = new Employee();
        employee.setFirstname("John");
        employee.setLastname("Doe");
        employee.setMiddleName("Smith");

        // Act
        String fullname = employeeService.generateFullname(employee);

        // Assert
        assertEquals("John Smith Doe", fullname);
    }

    @Test
    public void testFindAllEmployees() {
        // Arrange
        List<Employee> expectedEmployees = new ArrayList<>();
        when(employeeRepository.findAll()).thenReturn(expectedEmployees);

        // Act
        List<Employee> result = employeeService.findAll();

        // Assert
        assertEquals(expectedEmployees, result);
    }

    @Test
    public void testGetEmployeeById() {
        // Arrange
        String employeeId = "123";
        Employee expectedEmployee = new Employee();
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(expectedEmployee));

        // Act
        Optional<Employee> result = employeeService.getById(employeeId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedEmployee, result.get());
    }

    @Test
    public void testGetEmployeeByIdNotFound() {
        // Arrange
        String employeeId = "123";
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        // Act
        Optional<Employee> result = employeeService.getById(employeeId);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    public void testUpdateEmployee() {
        // Arrange
        Employee employeeToUpdate = new Employee();
        when(employeeRepository.save(employeeToUpdate)).thenReturn(employeeToUpdate);

        // Act
        Employee result = employeeService.updateEmployee(employeeToUpdate);

        // Assert
        assertEquals(employeeToUpdate, result);
    }

    @Test
    public void testDeleteEmployee() {
        // Arrange
        Employee employeeToDelete = new Employee();

        // Act
        employeeService.deleteEmployee(employeeToDelete);

        // Assert (use verify to ensure that the delete method is called)
        verify(employeeRepository, times(1)).delete(employeeToDelete);
    }


}