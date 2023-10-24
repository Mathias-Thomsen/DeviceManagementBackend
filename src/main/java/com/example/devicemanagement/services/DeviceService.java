package com.example.devicemanagement.services;


import com.example.devicemanagement.entities.Device;
import com.example.devicemanagement.repositories.DeviceRepository;
import com.example.devicemanagement.services.idService.DeviceIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceIdService deviceIdService;

    // Metode til at oprette og gemme en enhed med genereret ID
    public void saveDevice(Device device) {
        String nextId = deviceIdService.generateNextDeviceId(device.getDeviceType());
        device.setId(nextId);
        deviceRepository.save(device);
    }


    public List<Device> findAll() {
        return deviceRepository.findAll();
    }
}
