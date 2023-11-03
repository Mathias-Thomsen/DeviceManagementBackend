package com.example.devicemanagement.repositories;

import com.example.devicemanagement.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    @Query("SELECT MAX(e.id) FROM Employee e")
    String findMaxEmployeeId();
}
