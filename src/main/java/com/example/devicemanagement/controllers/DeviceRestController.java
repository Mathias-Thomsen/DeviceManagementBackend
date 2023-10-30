package com.example.devicemanagement.controllers;
import com.example.devicemanagement.entities.Device;
import com.example.devicemanagement.services.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/devices")
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
                .orElse(ResponseEntity.notFound().build());
    }

    // Opret en ny enhed
    @PostMapping
    public ResponseEntity<Device> createDevice(@RequestBody Device device) {
        return Optional.ofNullable(deviceService.saveDevice(device))
                .map(createdDevice -> ResponseEntity.status(HttpStatus.CREATED).body(createdDevice))
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }


    // Opdater en eksisterende enhed
    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable String id, @RequestBody Device updatedDevice) {
        return deviceService.getById(id)
                .map(device -> ResponseEntity.ok(deviceService.updateDevice(updatedDevice)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Slet en enhed efter ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable String id) {
        return deviceService.getById(id)
                .map(device -> {
                    deviceService.delete(device);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
