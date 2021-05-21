package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class HouseDay {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Date date;

    @Column
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "id_house")
    private House houseDate;

    @Column
    private java.util.Date create_at;

    @Column
    private java.util.Date update_at;

}
