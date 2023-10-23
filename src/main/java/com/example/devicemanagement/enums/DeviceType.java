package com.example.devicemanagement.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum DeviceType {
    ACTION_CAMERA("AC"),
    DOCKING_STATION("DS"),
    HEADSET("HS"),
    INTERNET_SUBSCRIPTION("IS"),
    MOBILE_PHONE("MP"),
    PC("PC"),
    ROUTER("RT"),
    SCREEN("SC"),
    SMARTPHONE("SP"),
    TABLET("TB"),
    MOBILE_PHONE_ACCESSORIES("MPA"),
    TABLET_ACCESSORIES("TA"),
    GPS("GPS"),
    WEBCAM("WC");

    private String abbreviation;

}
