package com.example.devicemanagement.controllers;

import com.example.devicemanagement.entities.Device;
import com.example.devicemanagement.exceptions.CustomException;
import com.example.devicemanagement.services.DeviceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class DeviceRestControllerTest {

    @Autowired
    DeviceRestController deviceRestController;

    @MockBean
    DeviceService deviceService;

    @Test
    public void testGetAllDevices() {
        // Arrange
        List<Device> expectedDevices = new ArrayList<>();

        // Mock the service to return the expected devices
        when(deviceService.findAll()).thenReturn(expectedDevices);

        // Act
        ResponseEntity<List<Device>> response = deviceRestController.getAllDevices();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDevices, response.getBody());
    }
    @Test
    public void testGetAllDevicesException() {
        // Arrange
        List<Device> expectedDevices = new ArrayList<>();
        when(deviceService.findAll()).thenReturn(expectedDevices);

        // Simulér en fejl i servicekaldet
        when(deviceService.findAll()).thenThrow(new CustomException("Service exception"));

        // Act and Assert
        assertThrows(CustomException.class, () -> deviceRestController.getAllDevices());
    }


    @Test
    public void testGetDeviceById() {
        // Arrange
        String deviceId = "TB0001";
        Device expectedDevice = new Device();

        // Mock the service to return the expected device
        when(deviceService.getById(deviceId)).thenReturn(Optional.of(expectedDevice));

        // Act
        ResponseEntity<Device> response = deviceRestController.getDeviceById(deviceId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDevice, response.getBody());
    }
    @Test
    public void testGetDeviceByIdWithInvalidId() {
        // Arrange
        String invalidDeviceId = "InvalidID";

        // Mock the service to return an empty Optional to simulate a missing device
        when(deviceService.getById(invalidDeviceId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(CustomException.class, () -> deviceRestController.getDeviceById(invalidDeviceId));
    }

    @Test
    public void testCreateDevice() {
        // Arrange
        Device newDevice = new Device();

        // Mock the service to return the saved device
        when(deviceService.saveDevice(newDevice)).thenReturn(Optional.of(newDevice));

        // Act
        ResponseEntity<Device> response = deviceRestController.createDevice(newDevice);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newDevice, response.getBody());
    }

    @Test
    public void testCreateDeviceException() {
        // Arrange
        Device newDevice = new Device();
        when(deviceService.saveDevice(newDevice)).thenReturn(Optional.of(newDevice));

        // Simulér en fejl i servicekaldet
        when(deviceService.saveDevice(newDevice)).thenThrow(new CustomException("Service exception"));

        // Act and Assert
        assertThrows(CustomException.class, () -> deviceRestController.createDevice(newDevice));
    }


    @Test
    public void testUpdateDevice() {
        // Arrange
        String deviceId = "TB0001";
        Device updatedDevice = new Device();

        // Mock the service to return the existing device and the updated device
        when(deviceService.getById(deviceId)).thenReturn(Optional.of(new Device()));
        when(deviceService.updateDevice(updatedDevice)).thenReturn(updatedDevice);

        // Act
        ResponseEntity<Device> response = deviceRestController.updateDevice(deviceId, updatedDevice);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedDevice, response.getBody());
    }

    @Test
    public void testUpdateDeviceException() {
        // Arrange
        String deviceId = "TB0001";
        Device updatedDevice = new Device();

        // Mock the service to return the existing device and the updated device
        when(deviceService.getById(deviceId)).thenReturn(Optional.of(new Device()));
        when(deviceService.updateDevice(updatedDevice)).thenReturn(updatedDevice);

        // Simulér en fejl i servicekaldet
        when(deviceService.updateDevice(updatedDevice)).thenThrow(new CustomException("Service exception"));

        // Act and Assert
        assertThrows(CustomException.class, () -> deviceRestController.updateDevice(deviceId, updatedDevice));
    }


    @Test
    public void testDeleteDevice() {
        // Arrange
        String deviceId = "TB0001";

        // Mock the service to return the existing device
        when(deviceService.getById(deviceId)).thenReturn(Optional.of(new Device()));

        // Act
        ResponseEntity<Void> response = deviceRestController.deleteDevice(deviceId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testDeleteDeviceException() {
        // Arrange
        String deviceId = "TB0001";

        // Mock the service to return the existing device
        when(deviceService.getById(deviceId)).thenReturn(Optional.of(new Device()));

        // Simulér en fejl i servicekaldet, når enheden ikke findes
        when(deviceService.getById(deviceId)).thenThrow(new CustomException("Device not found"));

        // Act and Assert
        assertThrows(CustomException.class, () -> deviceRestController.deleteDevice(deviceId));
    }



}

