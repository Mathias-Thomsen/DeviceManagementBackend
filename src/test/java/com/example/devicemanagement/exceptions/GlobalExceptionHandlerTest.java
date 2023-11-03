package com.example.devicemanagement.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    public void testHandleCustomException() {
        // Arrange
        CustomException customException = new CustomException("Custom exception message");

        // Act
        ResponseEntity<Object> response = globalExceptionHandler.handleRuntimeException(customException);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Custom exception message", response.getBody());
    }

    @Test
    public void testHandleOtherException() {
        // Arrange
        RuntimeException otherException = new RuntimeException("Other exception message");

        // Act
        ResponseEntity<Object> response = globalExceptionHandler.handleRuntimeException(otherException);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("En uventet fejl opstod.", response.getBody());
    }
}