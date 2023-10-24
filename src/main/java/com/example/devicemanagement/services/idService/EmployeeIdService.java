package com.example.devicemanagement.services.idService;

import com.example.devicemanagement.enums.DeviceType;
import com.example.devicemanagement.enums.EmployeeType;
import com.example.devicemanagement.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeIdService {

    @Autowired
    EmployeeRepository employeeRepository;

    public String generateNextEmployeeId(EmployeeType employeeType) {
        String employeeTypeAbbreviation = employeeType.getAbbreviation();

        // Find det højeste eksisterende ID for den pågældende enhedstype
        String maxEmployeeId = employeeRepository.findMaxEmployeeIdForType(employeeTypeAbbreviation);

        int abbreviationLength = employeeTypeAbbreviation.length();

        // Hent den numeriske sekvens fra eksisterende ID, hvis det findes
        int nextSequence = 1;
        if (maxEmployeeId != null && maxEmployeeId.length() > abbreviationLength) {
            String sequencePart = maxEmployeeId.substring(abbreviationLength);
            nextSequence = Integer.parseInt(sequencePart) + 1;
        }

        // Formater sekvensen med førende nuller og sammensæt ID
        String formattedSequence = String.format("%0" + (4 - abbreviationLength) + "d", nextSequence);
        return employeeTypeAbbreviation + formattedSequence;
    }
}
