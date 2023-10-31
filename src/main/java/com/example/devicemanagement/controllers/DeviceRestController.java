package com.example.devicemanagement.controllers;
import com.example.devicemanagement.entities.Device;
import com.example.devicemanagement.exceptions.CustomException;
import com.example.devicemanagement.services.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/device")
public class DeviceRestController {

    private final DeviceService deviceService;

    public DeviceRestController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    // Hent alle enheder
    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.findAll();
        return ResponseEntity.ok(devices);
    }

    // Hent en specifik enhed efter ID
    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable String id) {
        return deviceService.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomException("Device not found with id: " + id));
    }

    @PostMapping
    public ResponseEntity<Device> createDevice(@RequestBody Device device) {
        return deviceService.saveDevice(device)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomException("Problem with post device"));
    }



    // Opdater en eksisterende enhed
    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable String id, @RequestBody Device updatedDevice) {
        return deviceService.getById(id)
                .map(device -> ResponseEntity.ok(deviceService.updateDevice(updatedDevice)))
                .orElseThrow(() -> new CustomException("Device not found with ID: " + id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable String id) {
        return deviceService.getById(id)
                .map(device -> {
                    deviceService.delete(device);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseThrow(() -> new CustomException("Device not found with ID: " + id));
    }

}
