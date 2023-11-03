package com.example.devicemanagement.controllers;


import com.example.devicemanagement.entities.SimCard;
import com.example.devicemanagement.exceptions.CustomException;
import com.example.devicemanagement.services.SimCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simcards")
public class SimCardRestController {

    private final SimCardService simCardService;

    public SimCardRestController(SimCardService simCardService) {
        this.simCardService = simCardService;
    }

    // Hent alle SIM-kort
    @GetMapping
    public ResponseEntity<List<SimCard>> getAllSimCards() {
        List<SimCard> simCards = simCardService.findAll();
        return ResponseEntity.ok(simCards);
    }

    // Hent et specifikt SIM-kort efter ID
    @GetMapping("/{id}")
    public ResponseEntity<SimCard> getSimCardById(@PathVariable String id) {
        return simCardService.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomException("SimCard not found with id: " + id));
    }

    @PostMapping
    public ResponseEntity<SimCard> createSimCard(@RequestBody SimCard simCard) {
        return simCardService.saveSimCard(simCard)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomException("Problem with post SimCard"));
    }

    // Opdater et eksisterende SIM-kort
    @PutMapping("/{id}")
    public ResponseEntity<SimCard> updateSimCard(@PathVariable String id, @RequestBody SimCard updatedSimCard) {
        return simCardService.getById(id)
                .map(simCard -> ResponseEntity.ok(simCardService.updateSimCard(updatedSimCard)))
                .orElseThrow(() -> new CustomException("SimCard not found with ID: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSimCard(@PathVariable String id) {
        return simCardService.getById(id)
                .map(simCard -> {
                    simCardService.deleteSimCard(simCard);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseThrow(() -> new CustomException("SimCard not found with ID: " + id));
    }


}