package com.ldb.skillsdemo.bkend.repositories;

import com.ldb.skillsdemo.bkend.domain.Car;
import com.ldb.skillsdemo.bkend.domain.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {

    @Query(value = """
            SELECT c
            FROM Car c
            WHERE c.model = :model
            """)
    List<Car> findByModel(@Param("model") String model);

    List<Car> findByOwner(Owner owner);
}
