package com.example.devicemanagement.config;

import com.example.devicemanagement.entities.Device;
import com.example.devicemanagement.entities.Employee;
import com.example.devicemanagement.enums.DeviceStatus;
import com.example.devicemanagement.enums.DeviceType;
import com.example.devicemanagement.enums.EmployeeType;
import com.example.devicemanagement.services.DeviceService;
import com.example.devicemanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    DeviceService deviceService;

    @Autowired
    EmployeeService employeeService;

    @Override
    public void run(String... args) throws Exception {

        //test data device.
        Device device = new Device();
        device.setIMEINumber(123456789012345L);
        device.setSerialNumber("ABC123");
        device.setDeviceType(DeviceType.SMARTPHONE);
        device.setDeviceModel("iPhone X");
        device.setDeviceStatus(DeviceStatus.IN_USE);
        device.setComments("Dette er en testenhed");
        deviceService.saveDevice(device);

        Device device1 = new Device();
        device1.setIMEINumber(543210123456789L);
        device1.setSerialNumber("ABC1234");
        device1.setDeviceType(DeviceType.SMARTPHONE);
        device1.setDeviceModel("iPhone X");
        device1.setDeviceStatus(DeviceStatus.IN_STORAGE);
        device1.setComments("Dette er en testenhed 2");
        deviceService.saveDevice(device1);

        Device device3 = new Device();
        device3.setIMEINumber(543210123456786L);
        device3.setSerialNumber("ABC1235");
        device3.setDeviceType(DeviceType.TABLET);
        device3.setDeviceModel("TAB A8");
        device3.setDeviceStatus(DeviceStatus.IN_STORAGE);
        device3.setComments("Dette er en testenhed 3");
        deviceService.saveDevice(device3);

        Employee employee1 = new Employee();
        employee1.setFullname("John Smith");
        employee1.setFirstname("John");
        employee1.setMiddleName("David");
        employee1.setLastname("Smith");
        employee1.setEmployeeType(EmployeeType.FUNKTIONAER_LONNET);
        employeeService.saveEmployee(employee1);

        Employee employee2 = new Employee();
        employee2.setFullname("Alice Johnson");
        employee2.setFirstname("Alice");
        employee2.setLastname("Johnson");
        employee2.setEmployeeType(EmployeeType.TIMELONNET);
        employeeService.saveEmployee(employee2);

        Employee employee3 = new Employee();
        employee3.setFullname("Bob Anderson");
        employee3.setFirstname("Bob");
        employee3.setLastname("Anderson");
        employee3.setEmployeeType(EmployeeType.FUNKTIONAER_LONNET);
        employeeService.saveEmployee(employee3);








    }
}
