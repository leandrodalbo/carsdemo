package com.ldb.skillsdemo.bkend.exchange;

import com.ldb.skillsdemo.bkend.domain.Car;

import java.util.Optional;

public record CarDTO(Optional<Long> carId,
                     Optional<String> brand,
                     Optional<String> model,
                     Optional<String> color,
                     Optional<String> registrationNumber,
                     Optional<Integer> modelYear,
                     Optional<Double> price) {

    public static CarDTO fromModel(Car car) {
        return new CarDTO(
                Optional.ofNullable(car.getCarId()),
                Optional.ofNullable(car.getBrand()),
                Optional.ofNullable(car.getModel()),
                Optional.ofNullable(car.getColor()),
                Optional.ofNullable(car.getRegistrationNumber()),
                Optional.ofNullable(car.getModelYear()),
                Optional.ofNullable(car.getPrice())
        );
    }

    public Car toModel() {
        return new Car(this.brand.orElse(null), this.model.orElse(null), this.color.orElse(null), this.registrationNumber.orElse(null), this.modelYear.orElse(null), this.price.orElse(null), null);
    }

}
