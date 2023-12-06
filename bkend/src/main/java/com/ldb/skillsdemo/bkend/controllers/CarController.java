package com.ldb.skillsdemo.bkend.controllers;

import com.ldb.skillsdemo.bkend.exchange.CarDTO;
import com.ldb.skillsdemo.bkend.service.CarService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/cars")
@CrossOrigin
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/all")
    public Set<CarDTO> all() {
        return carService.allCars();
    }
}
