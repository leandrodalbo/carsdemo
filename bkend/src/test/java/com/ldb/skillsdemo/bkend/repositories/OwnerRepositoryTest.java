package com.ldb.skillsdemo.bkend.repositories;

import com.ldb.skillsdemo.bkend.domain.Owner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

// load spring context
@DataJpaTest
@ExtendWith(SpringExtension.class)
class OwnerRepositoryTest {

    @Autowired
    private OwnerRepository repository;

    @Test
    public void willFindThemByLastName() {

        Owner o1 = new Owner();
        Owner o2 = new Owner();

        o1.setLastName("Perez");
        o2.setLastName("Roberts");

        repository.save(o1);
        repository.save(o2);

        var result = repository.findByLastName("Perez");

        assertThat(result.get(0).getLastName()).isEqualTo("Perez");
    }

}
