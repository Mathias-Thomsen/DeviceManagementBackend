package com.example.devicemanagement.config;

import com.example.devicemanagement.entities.Device;
import com.example.devicemanagement.entities.Employee;
import com.example.devicemanagement.entities.SimCard;
import com.example.devicemanagement.enums.DeviceStatus;
import com.example.devicemanagement.enums.DeviceType;
import com.example.devicemanagement.enums.EmployeeType;
import com.example.devicemanagement.enums.SimCardType;
import com.example.devicemanagement.services.DeviceService;
import com.example.devicemanagement.services.EmployeeService;
import com.example.devicemanagement.services.SimCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    DeviceService deviceService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    SimCardService simCardService;

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
        employee1.setFirstname("John");
        employee1.setMiddleName("David");
        employee1.setLastname("Smith");
        employee1.setEmployeeType(EmployeeType.FUNKTIONAER_LONNET);
        employeeService.saveEmployee(employee1);

        Employee employee2 = new Employee();
        employee2.setFirstname("Alice");
        employee2.setLastname("Johnson");
        employee2.setEmployeeType(EmployeeType.TIMELONNET);
        employeeService.saveEmployee(employee2);

        Employee employee3 = new Employee();
        employee3.setFirstname("Bob");
        employee3.setLastname("Anderson");
        employee3.setEmployeeType(EmployeeType.FUNKTIONAER_LONNET);
        employeeService.saveEmployee(employee3);

        Employee employee4 = new Employee();
        employee4.setFirstname("Mathias");
        employee4.setLastname("Thomsen");
        employee4.setEmployeeType(EmployeeType.FUNKTIONAER_LONNET);
        employeeService.saveEmployee(employee4);


        SimCard simCard1 = new SimCard();
        simCard1.setPhoneNumber("12345678");
        simCard1.setIMSINumber(9876543210L);
        simCard1.setICCIDNumber(1234567890L);
        simCard1.setPinCode(1234);
        simCard1.setPukCode(5678);
        simCard1.setSimCardType(SimCardType.PHONE_CARD);
        simCardService.saveSimCard(simCard1);

        SimCard simCard2 = new SimCard();
        simCard2.setPhoneNumber("87654321");
        simCard2.setIMSINumber(1111111111L);
        simCard2.setICCIDNumber(2222222222L);
        simCard2.setPinCode(4321);
        simCard2.setPukCode(8765);
        simCard2.setSimCardType(SimCardType.DATA_SHARING_CARD);
        simCardService.saveSimCard(simCard2);

        SimCard simCard3 = new SimCard();
        simCard3.setPhoneNumber("55555555");
        simCard3.setIMSINumber(3333333333L);
        simCard3.setICCIDNumber(4444444444L);
        simCard3.setPinCode(9999);
        simCard3.setPukCode(8888);
        simCard3.setSimCardType(SimCardType.PHONE_CARD);
        simCardService.saveSimCard(simCard3);










    }
}
