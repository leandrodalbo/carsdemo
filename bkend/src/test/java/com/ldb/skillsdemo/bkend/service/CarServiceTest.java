package com.ldb.skillsdemo.bkend.service;

import com.ldb.skillsdemo.bkend.domain.Car;
import com.ldb.skillsdemo.bkend.exchange.CarDTO;
import com.ldb.skillsdemo.bkend.repositories.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
        var dto = new CarDTO(-1L, "x", "y", "z", "abc", 1990, 2000.1);
        when(carRepository.save(any())).thenReturn(dto.toModel());

        var result = service.saveNewCar(dto);

        assertThat(result).isEqualTo(dto);
        verify(carRepository, times(1)).save(any());
    }
}
