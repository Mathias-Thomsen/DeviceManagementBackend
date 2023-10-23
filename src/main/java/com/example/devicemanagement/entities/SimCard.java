package com.example.devicemanagement.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

    @Pattern(regexp = "^[0-9\\s]{8}$", message = "Phone number must be 8 digits and can contain spaces.")
    private String phoneNumber;
    private Long IMSINumber;
    private Long ICCIDNumber;
    private int pinCode;
    private int pukCode;

}
