package com.example.demo.model;

import com.example.demo.model.auth.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column(unique = true, nullable = false)
    private String address;

    @Column
    private String description;

    @Column
    private String acreage;

    @Column
    private Double price;

    @Column
    private Long numberRoom;

    @Column
    private Long discount;

    @Column
    private Long numberHires;

    private Boolean status;

    @OneToMany(mappedBy = "houseImage", cascade = CascadeType.REMOVE)
    private List<Image> images;

    @JsonBackReference
    @OneToMany(mappedBy = "houseBill", cascade = CascadeType.REMOVE)
    private List<Bill> bill;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "houseService", cascade = CascadeType.REMOVE)
    private List<Service> services;

    @ManyToMany(targetEntity = Utilitie.class, fetch = FetchType.EAGER)
    @JoinTable(name = "houses_utilities",
            joinColumns = {@JoinColumn(name = "id_house")},
            inverseJoinColumns = {@JoinColumn(name = "id_utilitie")})
    private List<Utilitie> utilitie;

    @Column
    private java.util.Date create_at;

    @Column
    private java.util.Date update_at;
}

