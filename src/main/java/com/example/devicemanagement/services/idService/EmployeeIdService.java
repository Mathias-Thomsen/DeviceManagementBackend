package com.example.devicemanagement.services.idService;

import com.example.devicemanagement.enums.DeviceType;
import com.example.devicemanagement.enums.EmployeeType;
import com.example.devicemanagement.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeIdService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public String generateNextEmployeeId(String employeeTypeAbbreviation) {
        String maxEmployeeId = employeeRepository.findMaxEmployeeId();

        // Tjek om maxEmployeeId er null, og sæt det til en tom streng, hvis det er tilfældet
        maxEmployeeId = (maxEmployeeId != null) ? maxEmployeeId : "";

        // Pars numerisk sekvens og fortsæt generering af næste ID
        int nextSequence = 1;
        if (!maxEmployeeId.isEmpty()) {
            int maxSequence = Integer.parseInt(maxEmployeeId.substring(employeeTypeAbbreviation.length()));
            nextSequence = maxSequence + 1;
        }

        String formattedSequence = String.format("%04d", nextSequence);

        return employeeTypeAbbreviation + formattedSequence;
    }



}
