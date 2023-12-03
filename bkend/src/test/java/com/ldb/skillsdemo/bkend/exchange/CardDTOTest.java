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
}
