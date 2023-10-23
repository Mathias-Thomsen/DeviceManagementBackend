package com.example.devicemanagement.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum SimCardType {
    PHONE_CARD("POC"),
    DATA_SHARING_CARD("DSC");

    private String abbreviation;
}
