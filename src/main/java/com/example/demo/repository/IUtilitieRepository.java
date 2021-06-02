package com.example.demo.repository;

import com.example.demo.model.House;
import com.example.demo.model.Image;
import com.example.demo.model.Utilitie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUtilitieRepository extends JpaRepository<Utilitie, Long> {
    Utilitie findByName(String name);

    Iterable<Utilitie> findAllByStatusTrue();
}
