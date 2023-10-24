package com.example.devicemanagement.services.idService;

import com.example.devicemanagement.enums.DeviceType;
import com.example.devicemanagement.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class DeviceIdService {


    @Autowired
    private DeviceRepository deviceRepository;

    public String generateNextDeviceId(DeviceType deviceType) {
        String deviceTypeAbbreviation = deviceType.getAbbreviation();

        // Find det højeste eksisterende ID for den pågældende enhedstype
        String maxDeviceId = deviceRepository.findMaxDeviceIdForType(deviceTypeAbbreviation);

        int abbreviationLength = deviceTypeAbbreviation.length();

        // Hent den numeriske sekvens fra eksisterende ID, hvis det findes
        int nextSequence = 1;
        if (maxDeviceId != null && maxDeviceId.length() > abbreviationLength) {
            String sequencePart = maxDeviceId.substring(abbreviationLength);
            nextSequence = Integer.parseInt(sequencePart) + 1;
        }

        // Formater sekvensen med førende nuller og sammensæt ID
        String formattedSequence = String.format("%0" + (6 - abbreviationLength) + "d", nextSequence);
        return deviceTypeAbbreviation + formattedSequence;
    }




}
