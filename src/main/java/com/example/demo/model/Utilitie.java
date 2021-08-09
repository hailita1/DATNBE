package com.example.demo.model;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Utilitie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Boolean status;

    @Column
    private java.util.Date create_at;

    @Column
    private java.util.Date update_at;
}


