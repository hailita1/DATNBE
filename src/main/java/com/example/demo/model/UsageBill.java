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
    private String email;

    @Column
    private Double totalPrice;

    @Column
    private String status;

    @Column
    private Double voucher;

    @OneToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;
}
