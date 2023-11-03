package com.example.devicemanagement.services;

import com.example.devicemanagement.entities.SimCard;
import com.example.devicemanagement.repositories.SimCardRepository;
import com.example.devicemanagement.services.idService.SimCardIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;

@Service
public class SimCardService {

    @Autowired
    private SimCardRepository simCardRepository;

    @Autowired
    private SimCardIdService simCardIdService;

    // Method for save simcard with the simcard id logik we made from simcardIdService
    public Optional<SimCard> saveSimCard(SimCard simCard) {
        String nextId = simCardIdService.generateNextSimCardId(simCard.getSimCardType());
        simCard.setId(nextId);
        simCardRepository.save(simCard);
        return Optional.of(simCard);
    }

    public List<SimCard> findAll() {
        return simCardRepository.findAll();
    }

    public Optional<SimCard> getById(String id) {
        return simCardRepository.findById(id);
    }

    public SimCard updateSimCard(SimCard updatedSimCard) {
        return simCardRepository.save(updatedSimCard);
    }

    public void deleteSimCard(SimCard simCard) {
        simCardRepository.delete(simCard);
    }
}
