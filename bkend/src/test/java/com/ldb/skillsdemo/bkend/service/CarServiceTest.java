package com.ldb.skillsdemo.bkend.service;

import com.ldb.skillsdemo.bkend.domain.Car;
import com.ldb.skillsdemo.bkend.exchange.CarDTO;
import com.ldb.skillsdemo.bkend.repositories.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService service;


    @Test
    public void shouldGetASetOfCars() {
        Car car = new Car();
        when(carRepository.findAll())
                .thenReturn(List.of(car));


        var result = service.allCars();

        assertThat(result).isNotEmpty();
        verify(carRepository, times(1)).findAll();
    }


    @Test
    public void shouldDeleteACarUsingTheRepository() {
        doNothing().when(carRepository).deleteById(anyLong());

        service.deleteById(1L);

        verify(carRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void willSaveANewCar() {
        var dto = new CarDTO(Optional.empty(), Optional.of("x"), Optional.of("y"), Optional.of("z"), Optional.of("abc"), Optional.of(1990), Optional.of(2000.1));
        when(carRepository.save(any())).thenReturn(dto.toModel());

        var result = service.saveNewCar(dto);

        assertThat(result).isEqualTo(dto);
        verify(carRepository, times(1)).save(any());
    }


    @Test
    public void shouldNotUpdateACarWithoutTheId() {
        var updateDTO = new CarDTO(Optional.empty(),
                Optional.of("AAA"),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        );

        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> service.updateACar(updateDTO));


        verify(carRepository, times(0)).save(any());
        verify(carRepository, times(0)).findById(anyLong());
    }

    @Test
    public void willUpdateACar() {
        var existing = new CarDTO(Optional.of(1L), Optional.of("x"), Optional.of("y"), Optional.of("z"), Optional.of("abc"), Optional.of(1990), Optional.of(2000.1));
        var updateDTO = new CarDTO(Optional.of(1L),
                Optional.of("AAA"),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        );

        var updated = existing.toModel();
        updated.setCarId(1L);
        updated.setBrand("AAA");

        when(carRepository.findById(anyLong())).thenReturn(Optional.of(existing.toModel()));
        when(carRepository.save(any())).thenReturn(updated);

        var result = service.updateACar(updateDTO);

        assertThat(result).isEqualTo(CarDTO.fromModel(updated));
        verify(carRepository, times(1)).save(any());
        verify(carRepository, times(1)).findById(anyLong());
    }
}
