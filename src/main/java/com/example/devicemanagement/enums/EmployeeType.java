package com.example.devicemanagement.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum EmployeeType {
    TIMELONNET("TL"), // Forkortelse for timelønnet
    FUNKTIONAER_LONNET("FL"); // Forkortelse for funktionær lønnet

    private String abbreviation;
}
