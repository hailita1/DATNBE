package com.example.demo.model.auth;

import com.example.demo.model.Bill;
import com.example.demo.model.House;
import com.example.demo.model.Notification;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Column
    private String telephoneNumber;

    @Column
    private java.util.Date create_at;

    @Column
    private java.util.Date update_at;

    @Column
    private String avt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

    private String voucher;
}
