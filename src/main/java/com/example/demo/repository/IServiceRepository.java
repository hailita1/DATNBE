package com.example.demo.repository;

import com.example.demo.model.Category;
import com.example.demo.model.Service;
import com.example.demo.model.Utilitie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServiceRepository extends JpaRepository<Service, Long> {
    Service findByName(String name);

    Iterable<Service> findAllByStatusTrue();
}
