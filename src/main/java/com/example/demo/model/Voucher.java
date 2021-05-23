package com.example.demo.model;

import com.example.demo.model.auth.User;
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
    private Double discount;

    @Column
    private Boolean status;

    @Column
    private String create_at;

    @Column
    private int typeVoucher;

    @Column
    private java.util.Date startDate;

    @Column
    private java.util.Date expiredDate;

    @Column
    private java.util.Date update_at;
}
