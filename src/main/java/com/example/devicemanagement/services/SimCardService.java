package com.example.devicemanagement.services;

import com.example.devicemanagement.entities.SimCard;
import com.example.devicemanagement.repositories.SimCardRepository;
import com.example.devicemanagement.services.idService.SimCardIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimCardService {

    @Autowired
    private SimCardRepository simCardRepository;

    @Autowired
    private SimCardIdService simCardIdService;

    // Method for save simcard with the simcard id logik we made from simcardIdService
    public void saveSimCard(SimCard simCard) {
        String nextId = simCardIdService.generateNextSimCardId(simCard.getSimCardType());
        simCard.setId(nextId);
        simCardRepository.save(simCard);
    }
}
