package com.example.demo.model;

import com.example.demo.model.auth.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Bill {
    @Id
    private Long id;

    @Column
    private String nameUser;

    @Column
    private String telephoneNumber;

    @Column
    private String bookingDate;

    @Column
    private java.util.Date startDate;

    @Column
    private java.util.Date endDate;

    @Column
    private String comment;

    @Column
    private String email;

    @Column
    private String evaluate;

    @Column
    private Double totalPrice;

    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_house")
    private House houseBill;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "bills_services",
            joinColumns = {@JoinColumn(name = "id_bill")},
            inverseJoinColumns = {@JoinColumn(name = "id_service")})
    private Set<Service> service;

    @Column
    private java.util.Date create_at;

    @Column
    private java.util.Date update_at;

}
