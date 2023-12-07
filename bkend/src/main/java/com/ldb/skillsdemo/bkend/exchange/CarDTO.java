package com.ldb.skillsdemo.bkend.exchange;

import com.ldb.skillsdemo.bkend.domain.Car;

public record CarDTO(Long carId,
                     String brand,
                     String model,
                     String color,
                     String registrationNumber,
                     Integer modelYear,
                     Double price) {

    public static CarDTO fromModel(Car car) {
        return new CarDTO(
                car.getCarId(),
                car.getBrand(),
                car.getModel(),
                car.getColor(),
                car.getRegistrationNumber(),
                car.getModelYear(),
                car.getPrice()
        );
    }

    public Car toModel(){
        return new Car(this.carId, this.brand, this.model, this.color, this.registrationNumber, this.modelYear, this.price, null);
    }

}
