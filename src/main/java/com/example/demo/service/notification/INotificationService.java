package com.example.demo.service.notification;

import com.example.demo.model.Notification;
import com.example.demo.model.auth.User;
import com.example.demo.service.IGeneralService;

import java.util.List;

public interface INotificationService extends IGeneralService<Notification> {
    Iterable<Notification> findAllByUser(User user);

    void deleteListService(List<Long> model);

    Iterable<Notification> findAllByUserAndStatusIsTrueOrderByIdDesc(User user);
}
