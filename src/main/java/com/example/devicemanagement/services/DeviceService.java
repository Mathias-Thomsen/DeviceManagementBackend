package com.example.devicemanagement.services;


import com.example.devicemanagement.entities.Device;
import com.example.devicemanagement.repositories.DeviceRepository;
import com.example.devicemanagement.services.idService.DeviceIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceIdService deviceIdService;

    // Method for save device with the device id logik we made from deviceIdService
    public Device saveDevice(Device device) {
        String nextId = deviceIdService.generateNextDeviceId(device.getDeviceType()); //generate and get the next id
        device.setId(nextId);
        deviceRepository.save(device);
        return device;
    }


    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    public Optional<Device> getById(String id) {
        return deviceRepository.findById(id);
    }

    public Device updateDevice(Device updatedDevice) {
        return deviceRepository.save(updatedDevice);
    }

    public void delete(Device device) {
        deviceRepository.delete(device);
    }
}
