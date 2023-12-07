package com.ldb.skillsdemo.bkend.exchange;

import com.ldb.skillsdemo.bkend.domain.Car;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDTOTest {


    @Test
    public void shouldCreateACarDTOFromACar() {
        var car = new Car(-1L, "x", "y", "z", "abc", 1990, 2000.1, null);

        var dto = CarDTO.fromModel(car);

        assertThat(dto.carId()).isEqualTo(car.getCarId());
        assertThat(dto.model()).isEqualTo(car.getModel());
        assertThat(dto.brand()).isEqualTo(car.getBrand());
        assertThat(dto.price()).isEqualTo(car.getPrice());
        assertThat(dto.modelYear()).isEqualTo(car.getModelYear());
        assertThat(dto.color()).isEqualTo(car.getColor());
        assertThat(dto.registrationNumber()).isEqualTo(car.getRegistrationNumber());
    }

    @Test
    public void shouldCreateACarADTOInstance() {
        var dto = new CarDTO(-1L, "x", "y", "z", "abc", 1990, 2000.1);


        var car = dto.toModel();

        assertThat(car.getCarId()).isEqualTo(dto.carId());
        assertThat(car.getModel()).isEqualTo(dto.model());
        assertThat(car.getBrand()).isEqualTo(dto.brand());
        assertThat(car.getPrice()).isEqualTo(dto.price());
        assertThat(car.getModelYear()).isEqualTo(dto.modelYear());
        assertThat(car.getColor()).isEqualTo(dto.color());
        assertThat(car.getRegistrationNumber()).isEqualTo(dto.registrationNumber());
    }
}
