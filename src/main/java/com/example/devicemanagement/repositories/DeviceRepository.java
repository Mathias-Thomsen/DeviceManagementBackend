package com.example.devicemanagement.repositories;


import com.example.devicemanagement.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeviceRepository extends JpaRepository<Device, String>{

    //Get the max id number for the device type to generate the next id.
    @Query("SELECT MAX(d.id) FROM Device d WHERE d.id LIKE :deviceTypeAbbreviation%")
    String findMaxDeviceIdForType(@Param("deviceTypeAbbreviation") String deviceTypeAbbreviation);
}
