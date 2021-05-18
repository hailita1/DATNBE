package com.example.demo.service.notification;

import com.example.demo.model.Notification;
import com.example.demo.model.auth.User;
import com.example.demo.repository.INotificationRepository;
import com.example.demo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class NotificationService implements INotificationService {
    @Autowired
    private INotificationRepository notificationRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Iterable<Notification> findAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void remove(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public Iterable<Notification> findAllByUser(User user) {
        return notificationRepository.findAllByUser(user);
    }

    @Override
    public void deleteListService(List<Long> model) {
        if (model != null) {
            for (int i = 0; i < model.size(); i++) {
                notificationRepository.deleteById(model.get(i));
            }
        }
    }

    @Override
    public Iterable<Notification> findAllByUserAndStatusIsTrueOrderByIdDesc(User user) {
        return notificationRepository.findAllByUserAndStatusIsTrueOrderByIdDesc(user);
    }

}
