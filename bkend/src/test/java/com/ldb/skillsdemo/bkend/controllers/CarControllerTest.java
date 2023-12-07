package com.ldb.skillsdemo.bkend.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldb.skillsdemo.bkend.exchange.CarDTO;
import com.ldb.skillsdemo.bkend.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarService carService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void willFetchAllCars() throws Exception {
        var cars = Set.of(new CarDTO(
                1L,
                "a",
                "b",
                "c",
                "d",
                1998,
                23.333

        ));

        when(carService.allCars()).thenReturn(cars);

        this.mvc.perform(get("/cars/all"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(objectMapper.writeValueAsString(cars)));

        verify(carService, times(1)).allCars();
    }

    @Test
    public void willDelegateDeleteToTheService() throws Exception {
        doNothing().when(carService).deleteById(anyLong());

        this.mvc.perform(delete("/cars/1"))
                .andExpect(status().is2xxSuccessful());

        verify(carService, times(1)).deleteById(anyLong());
    }

    @Test
    public void willDelegateAddToTheService() throws Exception {
        var dto = new CarDTO(-1L, "x", "y", "z", "abc", 1990, 2000.1);
        when(carService.saveNewCar(any())).thenReturn(dto);

        this.mvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .characterEncoding("utf-8"))
                .andExpect(status().is2xxSuccessful());

        verify(carService, times(1)).saveNewCar(any());
    }
}
