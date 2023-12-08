package com.ldb.skillsdemo.bkend.repositories;

import com.ldb.skillsdemo.bkend.domain.Car;
import com.ldb.skillsdemo.bkend.domain.Owner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

// load spring context
@DataJpaTest
@ExtendWith(SpringExtension.class)
class CarRepositoryTest {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private OwnerRepository ownerRepository;


    @Test
    public void willFindCarsByModel() {

        Car c1 = new Car();
        Car c2 = new Car();

        c1.setModel("m1");
        c2.setModel("m2");

        carRepository.save(c1);
        carRepository.save(c2);

        var result = carRepository.findByModel("m1");

        assertThat(result.get(0).getModel()).isEqualTo("m1");
    }

    @Test
    public void willFindCarsByOwner() {
        Optional<Owner> owner = ownerRepository.findById(1005l);
        Car c1 = new Car();
        c1.setOwner(owner.get());

        carRepository.save(c1);

        var result = carRepository.findByOwner(owner.get());

        assertThat(result.get(0).getOwner().getOwnerId()).isEqualTo(1005l);
    }

}
