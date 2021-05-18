package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String link;

    @Column
    private Boolean status;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_nha")
    private House houseImage;
}
