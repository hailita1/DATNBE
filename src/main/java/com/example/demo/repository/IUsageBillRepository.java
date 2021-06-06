package com.example.demo.repository;

import com.example.demo.model.UsageBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsageBillRepository extends JpaRepository<UsageBill, Long> {
}
