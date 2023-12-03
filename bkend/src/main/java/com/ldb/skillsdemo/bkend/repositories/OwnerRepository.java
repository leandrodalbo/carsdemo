package com.ldb.skillsdemo.bkend.repositories;

import com.ldb.skillsdemo.bkend.domain.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    @Query(value = """
            SELECT o
            FROM Owner o
            WHERE o.lastName = :lastName
            """)
    List<Owner> findByLastName(@Param("lastName") String lastName);

}
