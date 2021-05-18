package com.example.demo.repository;


import com.example.demo.model.House;
import com.example.demo.model.HouseDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IHouseDayRepository extends JpaRepository<HouseDay, Date> {
    Iterable<HouseDay> findAllByHouseDate(House house);
}
