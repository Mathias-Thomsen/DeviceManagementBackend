package com.example.devicemanagement.services;
import com.example.devicemanagement.entities.Device;
import com.example.devicemanagement.repositories.DeviceRepository;
import com.example.devicemanagement.services.idService.DeviceIdService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private DeviceIdService deviceIdService;

    @InjectMocks
    private DeviceService deviceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveDevice() {

        // Arrange
        Device device = new Device();
        String nextId = "DEVICE001";

        when(deviceIdService.generateNextDeviceId(device.getDeviceType())).thenReturn(nextId);
        when(deviceRepository.save(device)).thenReturn(device);

        // Act
        Optional<Device> result = deviceService.saveDevice(device);

        // Assert
        assertEquals(Optional.of(device), result);
        assertEquals(nextId, device.getId());
    }

    @Test
    public void testFindAllDevices() {
        // Arrange
        List<Device> expectedDevices = new ArrayList<>();
        when(deviceRepository.findAll()).thenReturn(expectedDevices);

        // Act
        List<Device> result = deviceService.findAll();

        // Assert
        assertEquals(expectedDevices, result);
    }

    @Test
    public void testGetDeviceById() {
        // Arrange
        String deviceId = "DEVICE001";
        Device expectedDevice = new Device();

        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(expectedDevice));

        // Act
        Optional<Device> result = deviceService.getById(deviceId);

        // Assert
        assertEquals(Optional.of(expectedDevice), result);
    }

    @Test
    public void testUpdateDevice() {
        // Arrange
        Device updatedDevice = new Device();

        when(deviceRepository.save(updatedDevice)).thenReturn(updatedDevice);

        // Act
        Device result = deviceService.updateDevice(updatedDevice);

        // Assert
        assertEquals(updatedDevice, result);
    }

    @Test
    public void testDeleteDevice() {
        // Arrange
        Device device = new Device();

        // Act
        deviceService.delete(device);

        // Assert
        verify(deviceRepository, times(1)).delete(device);
    }
}