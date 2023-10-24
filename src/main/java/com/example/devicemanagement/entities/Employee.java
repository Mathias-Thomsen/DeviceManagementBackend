package com.example.devicemanagement.entities;


import com.example.devicemanagement.enums.EmployeeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @Column(name = "employee_id")
    private String id;

    private String fullname;

    @Column(nullable = false)
    private String firstname;

    private String middleName;

    @Column(nullable = false)
    private String lastname;

    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

}
