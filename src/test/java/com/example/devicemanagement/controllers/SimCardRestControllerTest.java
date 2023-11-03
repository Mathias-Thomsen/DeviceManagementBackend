package com.example.devicemanagement.controllers;

import com.example.devicemanagement.entities.SimCard;
import com.example.devicemanagement.exceptions.CustomException;
import com.example.devicemanagement.services.SimCardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class SimCardRestControllerTest {

    @InjectMocks
    SimCardRestController simCardRestController;

    @Mock
    SimCardService simCardService;



    @Test
    public void testGetAllSimCards() {
        // Arrange
        List<SimCard> expectedSimCard = new ArrayList<>();

        // Mock the service to return the expected employees
        when(simCardService.findAll()).thenReturn(expectedSimCard);

        // Act
        ResponseEntity<List<SimCard>> response = simCardRestController.getAllSimCards();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedSimCard, response.getBody());
    }

    @Test
    public void testGetAllSimCardsException() {
        // Arrange
        List<SimCard> expectedSimCard = new ArrayList<>();
        when(simCardService.findAll()).thenReturn(expectedSimCard);

        // Simulate an exception in the service call
        when(simCardService.findAll()).thenThrow(new CustomException("Service exception"));

        // Act and Assert
        assertThrows(CustomException.class, () -> simCardRestController.getAllSimCards());
    }



    @Test
    public void testGetSimCardById() {
        // Arrange
        String simCardId = "POC00000001";
        SimCard expectedSimCard = new SimCard();

        // Mock the service to return the expected sim card
        when(simCardService.getById(simCardId)).thenReturn(Optional.of(expectedSimCard));

        // Act
        ResponseEntity<SimCard> response = simCardRestController.getSimCardById(simCardId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedSimCard, response.getBody());
    }

    @Test
    public void testGetSimCardByIdWithInvalidId() {
        // Arrange
        String invalidSimCardId = "InvalidID";

        // Mock the service to return an empty Optional to simulate a missing sim card
        when(simCardService.getById(invalidSimCardId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(CustomException.class, () -> simCardRestController.getSimCardById(invalidSimCardId));
    }



    @Test
    public void testCreateSimCard() {
        // Arrange
        SimCard newSimCard = new SimCard();

        // Mock the service to return the saved sim card
        when(simCardService.saveSimCard(newSimCard)).thenReturn(Optional.of(newSimCard));

        // Act
        ResponseEntity<SimCard> response = simCardRestController.createSimCard(newSimCard);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newSimCard, response.getBody());
    }

    @Test
    public void testCreateSimCardException() {
        // Arrange
        SimCard newSimCard = new SimCard();
        when(simCardService.saveSimCard(newSimCard)).thenReturn(Optional.of(newSimCard));

        // Simulate an exception in the service call
        when(simCardService.saveSimCard(newSimCard)).thenThrow(new CustomException("Service exception"));

        // Act and Assert
        assertThrows(CustomException.class, () -> simCardRestController.createSimCard(newSimCard));
    }


    // For converting the test object to json. Used in delete and update test.
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUpdateSimCard() {
        // Arrange
        String simCardId = "SIM001";
        SimCard updatedSimCard = new SimCard();

        // Mock the service to return the existing sim card and the updated sim card
        when(simCardService.getById(simCardId)).thenReturn(Optional.of(new SimCard()));
        when(simCardService.updateSimCard(updatedSimCard)).thenReturn(updatedSimCard);

        // Act
        ResponseEntity<SimCard> response = simCardRestController.updateSimCard(simCardId, updatedSimCard);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedSimCard, response.getBody());
    }

    @Test
    public void testUpdateSimCardException() {
        // Arrange
        String simCardId = "SIM001";
        SimCard updatedSimCard = new SimCard();

        // Mock the service to return the existing sim card and the updated sim card
        when(simCardService.getById(simCardId)).thenReturn(Optional.of(new SimCard()));
        when(simCardService.updateSimCard(updatedSimCard)).thenReturn(updatedSimCard);

        // Simulate an exception in the service call
        when(simCardService.updateSimCard(updatedSimCard)).thenThrow(new CustomException("Service exception"));

        // Act and Assert
        assertThrows(CustomException.class, () -> simCardRestController.updateSimCard(simCardId, updatedSimCard));
    }




    @Test
    public void testDeleteSimCard() {
        // Arrange
        String simCardId = "SIM001";

        // Mock the service to return the existing sim card
        when(simCardService.getById(simCardId)).thenReturn(Optional.of(new SimCard()));

        // Act
        ResponseEntity<Void> response = simCardRestController.deleteSimCard(simCardId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testDeleteSimCardException() {
        // Arrange
        String simCardId = "SIM001";

        // Mock the service to return the existing sim card
        when(simCardService.getById(simCardId)).thenReturn(Optional.of(new SimCard()));

        // Simulate an exception in the service call when the sim card is not found
        when(simCardService.getById(simCardId)).thenThrow(new CustomException("SimCard not found"));

        // Act and Assert
        assertThrows(CustomException.class, () -> simCardRestController.deleteSimCard(simCardId));
    }




}