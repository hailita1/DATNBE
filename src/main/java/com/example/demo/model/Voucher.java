package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String voucher_code;

    @Column
    private String title;

    @Column
    private Double price;

    @Column
    private Boolean status;

    @Column
    private java.util.Date create_at;

    @Column
    private java.util.Date update_at;

}
