package com.ldb.skillsdemo.bkend.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldb.skillsdemo.bkend.exchange.CarDTO;
import com.ldb.skillsdemo.bkend.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    }
}
