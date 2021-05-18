package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String times;

    @Column
    private Double price;

    @Column
    private Boolean status;
}
