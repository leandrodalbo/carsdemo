package com.ldb.skillsdemo.bkend.service;

import com.ldb.skillsdemo.bkend.domain.Car;
import com.ldb.skillsdemo.bkend.exchange.CarDTO;
import com.ldb.skillsdemo.bkend.repositories.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public void deleteById(Long carId) {
        carRepository.deleteById(carId);
    }

    public CarDTO saveNewCar(CarDTO dto) {
        var saved = carRepository.save(dto.toModel());

        return CarDTO.fromModel(saved);
    }

    public CarDTO updateACar(CarDTO dto) throws ResponseStatusException {

        if (dto.carId().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "provide_carId");
        }

        var existing = carRepository.findById(dto.carId().get()).get();

        var toSave = new Car(
                existing.getCarId(),
                dto.brand().orElse(existing.getBrand()),
                dto.model().orElse(existing.getModel()),
                dto.color().orElse(existing.getColor()),
                dto.registrationNumber().orElse(existing.getRegistrationNumber()),
                dto.modelYear().orElse(existing.getModelYear()),
                dto.price().orElse(existing.getPrice()),
                null);

        var saved = carRepository.save(toSave);

        return CarDTO.fromModel(saved);
    }
}
