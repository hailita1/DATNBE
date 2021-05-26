package com.example.demo.repository;

import com.example.demo.model.Bill;
import com.example.demo.model.House;
import com.example.demo.model.HouseDay;
import com.example.demo.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IBillRepository extends JpaRepository<Bill, Long> {
    Iterable<Bill> findByOrderByIdDesc();

    Iterable<Bill> findAllByHouseBillOrderByIdAsc(House house);

//    Iterable<Bill> findByHouseBillAndStartDateGreaterThanEqualAndEndDateLessThanEqualAndStatus(House house, Date sd, Date ed, String status);

    @Modifying
    @Query(value = "SELECT * FROM Bill b WHERE b.id_user = :id and (b.status = :status1 or b.status = :status2 or b.status = :status3)", nativeQuery = true)
    Iterable<Bill> findBillByUser(Long id, String status1, String status2, String status3);
}
