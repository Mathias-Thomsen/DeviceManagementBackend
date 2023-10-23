package com.example.devicemanagement.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private String id;

    private String fullname;

    @Column(nullable = false)
    private String firstname;

    private String middleName;

    @Column(nullable = false)
    private String lastname;

    public String getFullname() {
        if (middleName != null && !middleName.isEmpty()) {
            return firstname + " " + middleName + " " + lastname;
        } else {
            return firstname + " " + lastname;
        }
    }
}
