package com.example.devicemanagement.controllers;

import com.example.devicemanagement.entities.Employee;
import com.example.devicemanagement.exceptions.CustomException;
import com.example.devicemanagement.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeRestController.class)
class EmployeeRestControllerTest {

    @Autowired
    EmployeeRestController employeeRestController;

    @MockBean
    EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllEmployees() {
        // Arrange
        List<Employee> expectedEmployees = new ArrayList<>();

        // Mock the service to return the expected employees
        when(employeeService.findAll()).thenReturn(expectedEmployees);

        // Act
        ResponseEntity<List<Employee>> response = employeeRestController.getAllEmployees();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedEmployees, response.getBody());
    }

    @Test
    public void testGetAllEmployeesException() {
        // Arrange
        List<Employee> expectedEmployees = new ArrayList<>();
        when(employeeService.findAll()).thenReturn(expectedEmployees);

        // Simulate an exception in the service call
        when(employeeService.findAll()).thenThrow(new CustomException("Service exception"));

        // Act and Assert
        assertThrows(CustomException.class, () -> employeeRestController.getAllEmployees());
    }

    @Test
    public void testGetAllEmployeesEndpoint() throws Exception {
        // Arrange
        List<Employee> expectedEmployees = new ArrayList<>();
        when(employeeService.findAll()).thenReturn(expectedEmployees);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/employee")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetEmployeeById() {
        // Arrange
        String employeeId = "TL0001";
        Employee expectedEmployee = new Employee();

        // Mock the service to return the expected employee
        when(employeeService.getById(employeeId)).thenReturn(Optional.of(expectedEmployee));

        // Act
        ResponseEntity<Employee> response = employeeRestController.getEmployeeById(employeeId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedEmployee, response.getBody());
    }

    @Test
    public void testGetEmployeeByIdWithInvalidId() {
        // Arrange
        String invalidEmployeeId = "InvalidID";

        // Mock the service to return an empty Optional to simulate a missing employee
        when(employeeService.getById(invalidEmployeeId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(CustomException.class, () -> employeeRestController.getEmployeeById(invalidEmployeeId));
    }

    @Test
    public void testGetEmployeeByIdEndpoint() throws Exception {
        // Arrange
        String employeeId = "TL0001";
        Employee employee = new Employee();
        when(employeeService.getById(employeeId)).thenReturn(Optional.of(employee));

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreateEmployee() {
        // Arrange
        Employee newEmployee = new Employee();

        // Mock the service to return the saved employee
        when(employeeService.saveEmployee(newEmployee)).thenReturn(Optional.of(newEmployee));

        // Act
        ResponseEntity<Employee> response = employeeRestController.createEmployee(newEmployee);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newEmployee, response.getBody());
    }

    @Test
    public void testCreateEmployeeException() {
        // Arrange
        Employee newEmployee = new Employee();
        when(employeeService.saveEmployee(newEmployee)).thenReturn(Optional.of(newEmployee));

        // Simulate an exception in the service call
        when(employeeService.saveEmployee(newEmployee)).thenThrow(new CustomException("Service exception"));

        // Act and Assert
        assertThrows(CustomException.class, () -> employeeRestController.createEmployee(newEmployee));
    }

    @Test
    public void testCreateEmployeeEndpoint() throws Exception {
        // Arrange
        Employee employeeToCreate = new Employee();
        when(employeeService.saveEmployee(employeeToCreate)).thenReturn(Optional.of(employeeToCreate));

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(employeeToCreate)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }


    // For converting the test object to json. Used in delete and update test.
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUpdateEmployee() {
        // Arrange
        String employeeId = "TL0001";
        Employee updatedEmployee = new Employee();

        // Mock the service to return the existing employee and the updated employee
        when(employeeService.getById(employeeId)).thenReturn(Optional.of(new Employee()));
        when(employeeService.updateEmployee(updatedEmployee)).thenReturn(updatedEmployee);

        // Act
        ResponseEntity<Employee> response = employeeRestController.updateEmployee(employeeId, updatedEmployee);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedEmployee, response.getBody());
    }

    @Test
    public void testUpdateEmployeeException() {
        // Arrange
        String employeeId = "TL0001";
        Employee updatedEmployee = new Employee();

        // Mock the service to return the existing employee and the updated employee
        when(employeeService.getById(employeeId)).thenReturn(Optional.of(new Employee()));
        when(employeeService.updateEmployee(updatedEmployee)).thenReturn(updatedEmployee);

        // Simulate an exception in the service call
        when(employeeService.updateEmployee(updatedEmployee)).thenThrow(new CustomException("Service exception"));

        // Act and Assert
        assertThrows(CustomException.class, () -> employeeRestController.updateEmployee(employeeId, updatedEmployee));
    }

    @Test
    public void testUpdateEmployeeEndpoint() throws Exception {
        // Arrange
        String employeeId = "TL0001";
        Employee updatedEmployee = new Employee();

        when(employeeService.getById(employeeId)).thenReturn(Optional.of(new Employee()));
        when(employeeService.updateEmployee(updatedEmployee)).thenReturn(updatedEmployee);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/employee/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedEmployee))) // Use asJsonString from create employee endpoint test
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testDeleteEmployee() {
        // Arrange
        String employeeId = "TL0001";

        // Mock the service to return the existing employee
        when(employeeService.getById(employeeId)).thenReturn(Optional.of(new Employee()));

        // Act
        ResponseEntity<Void> response = employeeRestController.deleteEmployee(employeeId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testDeleteEmployeeException() {
        // Arrange
        String employeeId = "TL0001";

        // Mock the service to return the existing employee
        when(employeeService.getById(employeeId)).thenReturn(Optional.of(new Employee()));

        // Simulate an exception in the service call when the employee is not found
        when(employeeService.getById(employeeId)).thenThrow(new CustomException("Employee not found"));

        // Act and Assert
        assertThrows(CustomException.class, () -> employeeRestController.deleteEmployee(employeeId));
    }

    @Test
    public void testDeleteEmployeeEndpoint() throws Exception {
        // Arrange
        String employeeId = "TL0001";

        when(employeeService.getById(employeeId)).thenReturn(Optional.of(new Employee()));

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }





}