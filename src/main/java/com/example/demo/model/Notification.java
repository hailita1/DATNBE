package com.example.demo.model;

import com.example.demo.model.auth.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Data
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String content;

    @Column
    private Boolean status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "notifications_users",
            joinColumns = {@JoinColumn(name = "id_notification")},
            inverseJoinColumns = {@JoinColumn(name = "id_user")})
    private Set<User> user;

    @Column
    private java.util.Date create_at;

    @Column
    private java.util.Date update_at;

}
