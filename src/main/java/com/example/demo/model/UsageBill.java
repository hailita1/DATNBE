package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UsageBill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private java.util.Date endDateFinal;

    @Column
    private String email;

    @Column
    private Double totalPrice;

    @Column
    private String status;

    @Column
    private Double voucher;

    @Column
    private String service;

    @Column
    private int idHost;

    @OneToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "id_house")
    private House houseBill;
}
