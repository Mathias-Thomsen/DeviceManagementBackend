package com.example.devicemanagement.entities;


import com.example.devicemanagement.enums.DeviceStatus;
import com.example.devicemanagement.enums.DeviceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    @Id
    @Column(name="device_id")
    private String id;

    @Column(nullable = false)
    @Size(min = 15, max = 15)
    private Long IMEINumber;

    @Column(nullable = false)
    private String SerialNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceType deviceType;

    @Column(nullable = false)
    private String deviceModel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceStatus deviceStatus;

    private String comments;

    @CreationTimestamp //Hibernate automatic create a timestamp in the database
    private LocalDateTime dateCreated;

    @UpdateTimestamp //Hibernate automatic update this timestamp if there has been implemented a change in the database.
    private LocalDateTime lastUpdated;




}
