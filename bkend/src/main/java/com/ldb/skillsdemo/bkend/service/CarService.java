package com.ldb.skillsdemo.bkend.service;

import com.ldb.skillsdemo.bkend.domain.Car;
import com.ldb.skillsdemo.bkend.exchange.CarDTO;
import com.ldb.skillsdemo.bkend.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public Set<CarDTO> allCars() {
        var result = new ArrayList<Car>();

        carRepository.findAll()
                .forEach(result::add);

        return result.stream()
                .map(CarDTO::fromModel)
                .collect(Collectors.toSet());
    }

    public void deleteById(Long carId)
    {
        carRepository.deleteById(carId);
    }
}
