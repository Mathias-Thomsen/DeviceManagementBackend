package com.example.devicemanagement.entities;


import com.example.devicemanagement.enums.SimCardType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimCard {

    @Id
    @Column(
            name = "sim_card_id",
            nullable = false
    )
    private String id;

    @Pattern(
            regexp = "^[0-9\\s]{8}$",
            message = "Phone number must be 8 digits and can contain spaces."
    )
    @Column(
            nullable = false,
            unique = true
    )
    private String phoneNumber;

    @Column(
            nullable = false,
            unique = true
    )
    private Long IMSINumber;

    @Column(
            nullable = false,
            unique = true
    )
    private Long ICCIDNumber;

    @Column(nullable = false)
    private int pinCode;

    @Column(nullable = false)
    private int pukCode;

    @Enumerated(EnumType.STRING)
    private SimCardType simCardType;
}