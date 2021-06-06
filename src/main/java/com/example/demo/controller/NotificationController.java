package com.example.demo.controller;

import com.example.demo.model.House;
import com.example.demo.model.Image;
import com.example.demo.model.Notification;
import com.example.demo.model.Utilitie;
import com.example.demo.model.auth.User;
import com.example.demo.service.BaseService;
import com.example.demo.service.notification.INotificationService;
import com.example.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private INotificationService notificationService;

    @Autowired
    private IUserService userService;

    @Autowired
    private BaseService baseService;

    @GetMapping

    public ResponseEntity<Iterable<Notification>> getAllNotification() {
        return new ResponseEntity<>(notificationService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/byUser")
    public ResponseEntity<Iterable<Notification>> getAllByUser(@RequestBody User user) {
        baseService.negotiate();
        baseService.sendMessage("Okeee");
        return new ResponseEntity<>(notificationService.findAllByUserAndStatusIsTrueOrderByIdDesc(user), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Void> createNewNotification(@RequestBody Notification notification, UriComponentsBuilder ucBuilder) {
        notificationService.save(notification);
        if (notification.getUser() != null) {
            User user = userService.findByEmail(notification.getUser().toString());
            Set<User> users = new HashSet<>();
            users.add(user);
            notification.setUser(users);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/notification/{id}").buildAndExpand(notification.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotification(@PathVariable Long id) {
        Optional<Notification> notification = notificationService.findById(id);
        return notification.map(notification1 -> new ResponseEntity<>(notification1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<Notification> updateNotification(@RequestBody Notification notification) {
        Optional<Notification> notificationOptional = notificationService.findById(notification.getId());
        return notificationOptional.map(notification1 -> {
            notification1.setId(notification1.getId());
            notification1.setStatus(false);
            return new ResponseEntity<>(notificationService.save(notification1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Notification> deleteNotification(@PathVariable Long id) {
        Optional<Notification> notificationOptional = notificationService.findById(id);
        return notificationOptional.map(notification1 -> {
            notificationService.remove(id);
            return new ResponseEntity<>(notification1, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/deleteList")
    public ResponseEntity deleteListNotification(@RequestBody List<Long> id) {
        notificationService.deleteListService(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/listNotifications")
    public ResponseEntity<Iterable<Notification>> findAllByUser(@RequestBody User user) {
        Iterable<Notification> notifications = notificationService.findAllByUser(user);
        if (notifications == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }
}
