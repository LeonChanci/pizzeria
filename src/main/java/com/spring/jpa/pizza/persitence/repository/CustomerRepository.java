package com.spring.jpa.pizza.persitence.repository;

import com.spring.jpa.pizza.persitence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends ListCrudRepository<CustomerEntity, String> {

    //@Query -> usando JPQL Obtener un cliente por medio de su número telefónico
    @Query(value = "SELECT c FROM CustomerEntity c WHERE c.phoneNumber = :phone")
    CustomerEntity finBnByPhone(@Param("phone") String phone);
}