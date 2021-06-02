package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServiceRepository extends JpaRepository<Service, Long> {
    Service findByName(String name);

    Iterable<Service> findAllByStatusTrue();

    Iterable<Service> findAllByHouseService(House house);
}
