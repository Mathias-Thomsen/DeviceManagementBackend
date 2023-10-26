package com.example.devicemanagement.services;

import com.example.devicemanagement.entities.Device;
import com.example.devicemanagement.entities.SimCard;
import com.example.devicemanagement.repositories.DeviceRepository;
import com.example.devicemanagement.repositories.SimCardRepository;
import com.example.devicemanagement.services.idService.DeviceIdService;
import com.example.devicemanagement.services.idService.SimCardIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimCardService {

    @Autowired
    private SimCardRepository simCardRepository;

    @Autowired
    private SimCardIdService simCardIdService;

    // Metode til at oprette og gemme en enhed med genereret ID
    public void saveSimCard(SimCard simCard) {
        String nextId = simCardIdService.generateNextSimCardId(simCard.getSimCardType());
        simCard.setId(nextId);
        simCardRepository.save(simCard);
    }
}
