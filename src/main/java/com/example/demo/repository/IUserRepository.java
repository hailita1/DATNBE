package com.example.demo.repository;

import com.example.demo.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
