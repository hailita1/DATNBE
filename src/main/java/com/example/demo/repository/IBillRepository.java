package com.example.demo.repository;

import com.example.demo.model.Bill;
import com.example.demo.model.House;
import com.example.demo.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBillRepository extends JpaRepository<Bill, Long> {
    Iterable<Bill> findByOrderByIdDesc();

    Iterable<Bill> findAllByUserAndStatusOrStatus(User user, String status1, String status2);

    Iterable<Bill> findAllByHouseBillOrderByIdDesc(House house);
}
