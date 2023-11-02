package com.example.devicemanagement.controllers;


import com.example.devicemanagement.entities.Device;
import com.example.devicemanagement.services.DeviceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


@WebMvcTest(DeviceRestController.class)
public class TestTes {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeviceService deviceService;

    @Test
    public void testGetAllDevicesEndpoint() throws Exception {
        // Arrange
        List<Device> expectedDevices = new ArrayList<>();
        when(deviceService.findAll()).thenReturn(expectedDevices);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/device")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}
