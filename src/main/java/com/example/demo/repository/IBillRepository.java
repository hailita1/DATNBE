package com.example.demo.repository;

import com.example.demo.model.Bill;
import com.example.demo.model.House;
import com.example.demo.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IBillRepository extends JpaRepository<Bill, Long> {
    Iterable<Bill> findByOrderByIdDesc();

    Iterable<Bill> findAllByUserAndStatusOrStatusOrStatus(User user, String status1, String status2, String status3);

    Iterable<Bill> findAllByHouseBillOrderByIdAsc(House house);

    Iterable<Bill> findByStartDateGreaterThanEqualAndEndDateLessThanEqualAndStatus(Date sd, Date ed, String status);
}
