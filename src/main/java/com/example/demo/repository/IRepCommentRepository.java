package com.example.demo.repository;

import com.example.demo.model.RepComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepCommentRepository extends JpaRepository<RepComment, Long> {
}
