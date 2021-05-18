package com.example.demo.repository;

import com.example.demo.model.Notification;
import com.example.demo.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotificationRepository extends JpaRepository<Notification, Long> {
    Iterable<Notification> findAllByUser(User user);

    Iterable<Notification> findAllByUserAndStatusIsTrueOrderByIdDesc(User user);
}
