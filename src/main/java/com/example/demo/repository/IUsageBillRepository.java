package com.example.demo.repository;


import com.example.demo.model.UsageBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsageBillRepository extends JpaRepository<UsageBill, Long> {
    @Query(value = "select sum(total_price) from usage_bill u " +
            "where month(end_date_final) = ?1 and year(end_date_final) = ?2 and u.id_host = ?3", nativeQuery = true)
    Long sumTotalPrice(Integer month, Integer year, Long id);

    @Query(value = "select sum(total_price) from usage_bill u " +
            "where month(end_date_final) = ?1 and year(end_date_final) = ?2 and u.id_host = ?3 and u.id_house = ?4", nativeQuery = true)
    Long sumTotalPriceHomeStay(Integer month, Integer year, Long idHost, Long idHouse);
}