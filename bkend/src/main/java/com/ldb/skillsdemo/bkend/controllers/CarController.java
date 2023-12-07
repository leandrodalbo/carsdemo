package com.ldb.skillsdemo.bkend.controllers;

import com.ldb.skillsdemo.bkend.exchange.CarDTO;
import com.ldb.skillsdemo.bkend.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{carId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCarById(@PathVariable Long carId) {
        carService.deleteById(carId);
    }
}
