package com.example.devicemanagement.services;

import com.example.devicemanagement.entities.SimCard;
import com.example.devicemanagement.repositories.SimCardRepository;
import com.example.devicemanagement.services.idService.SimCardIdService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimCardServiceTest {

    @InjectMocks
    private SimCardService simCardService;

    @Mock
    private SimCardRepository simCardRepository;

    @Mock
    private SimCardIdService simCardIdService;

    @Test
    public void testSaveSimCard() {
        // Arrange
        SimCard simCard = new SimCard();
        String generatedId = "123";
        when(simCardIdService.generateNextSimCardId(simCard.getSimCardType())).thenReturn(generatedId);
        when(simCardRepository.save(simCard)).thenReturn(simCard);

        // Act
        Optional<SimCard> result = simCardService.saveSimCard(simCard);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(generatedId, result.get().getId());
    }

    @Test
    public void testFindAllSimCards() {
        // Arrange
        List<SimCard> expectedSimCards = new ArrayList<>();
        when(simCardRepository.findAll()).thenReturn(expectedSimCards);

        // Act
        List<SimCard> result = simCardService.findAll();

        // Assert
        assertEquals(expectedSimCards, result);
    }

    @Test
    public void testGetSimCardById() {
        // Arrange
        String simCardId = "123";
        SimCard expectedSimCard = new SimCard();
        when(simCardRepository.findById(simCardId)).thenReturn(Optional.of(expectedSimCard));

        // Act
        Optional<SimCard> result = simCardService.getById(simCardId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedSimCard, result.get());
    }

    @Test
    public void testGetSimCardByIdNotFound() {
        // Arrange
        String simCardId = "123";
        when(simCardRepository.findById(simCardId)).thenReturn(Optional.empty());

        // Act
        Optional<SimCard> result = simCardService.getById(simCardId);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    public void testUpdateSimCard() {
        // Arrange
        SimCard simCardToUpdate = new SimCard();
        when(simCardRepository.save(simCardToUpdate)).thenReturn(simCardToUpdate);

        // Act
        SimCard result = simCardService.updateSimCard(simCardToUpdate);

        // Assert
        assertEquals(simCardToUpdate, result);
    }

    @Test
    public void testDeleteSimCard() {
        // Arrange
        SimCard simCardToDelete = new SimCard();

        // Act
        simCardService.deleteSimCard(simCardToDelete);

        // Assert (use verify to ensure that the delete method is called)
        verify(simCardRepository, times(1)).delete(simCardToDelete);
    }
}