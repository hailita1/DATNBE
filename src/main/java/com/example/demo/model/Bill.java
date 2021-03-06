package com.example.demo.model;

import com.example.demo.model.auth.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @Column
    private Double voucher;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_house")
    private House houseBill;

    @ManyToMany(targetEntity = Service.class, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "bills_services",
            joinColumns = {@JoinColumn(name = "id_bill")},
            inverseJoinColumns = {@JoinColumn(name = "id_service")})
    private List<Service> service;

    @Column
    private java.util.Date create_at;

    @Column
    private java.util.Date update_at;

}
