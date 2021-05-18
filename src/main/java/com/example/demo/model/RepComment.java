package com.example.demo.model;

import com.example.demo.model.auth.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class RepComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String content;

    @Column
    private Boolean status;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_house")
    private House houseRepcomment;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
