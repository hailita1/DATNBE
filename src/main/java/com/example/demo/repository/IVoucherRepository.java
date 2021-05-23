package com.example.demo.repository;

import com.example.demo.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IVoucherRepository extends JpaRepository<Voucher, Long> {
    Iterable<Voucher> findByExpiredDateGreaterThanEqual(Date date);
}
