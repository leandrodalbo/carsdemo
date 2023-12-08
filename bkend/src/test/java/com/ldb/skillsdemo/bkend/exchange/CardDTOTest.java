package com.ldb.skillsdemo.bkend.exchange;

import com.ldb.skillsdemo.bkend.domain.Car;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDTOTest {


    @Test
    public void shouldCreateACarDTOFromACar() {
        var car = new Car(-1L, "x", "y", "z", "abc", 1990, 2000.1, null);

        var dto = CarDTO.fromModel(car);

        assertThat(dto.carId().get()).isEqualTo(car.getCarId());
        assertThat(dto.model().get()).isEqualTo(car.getModel());
        assertThat(dto.brand().get()).isEqualTo(car.getBrand());
        assertThat(dto.price().get()).isEqualTo(car.getPrice());
        assertThat(dto.modelYear().get()).isEqualTo(car.getModelYear());
        assertThat(dto.color().get()).isEqualTo(car.getColor());
        assertThat(dto.registrationNumber().get()).isEqualTo(car.getRegistrationNumber());
    }

    @Test
    public void shouldCreateACarADTOInstance() {
        var dto = new CarDTO(Optional.ofNullable(null), Optional.of("x"), Optional.of("y"), Optional.of("z"), Optional.of("abc"), Optional.of(1990), Optional.of(2000.1));


        var car = dto.toModel();

        assertThat(car.getCarId()).isNull();
        assertThat(car.getModel()).isEqualTo(dto.model().get());
        assertThat(car.getBrand()).isEqualTo(dto.brand().get());
        assertThat(car.getPrice()).isEqualTo(dto.price().get());
        assertThat(car.getModelYear()).isEqualTo(dto.modelYear().get());
        assertThat(car.getColor()).isEqualTo(dto.color().get());
        assertThat(car.getRegistrationNumber()).isEqualTo(dto.registrationNumber().get());
    }
}
