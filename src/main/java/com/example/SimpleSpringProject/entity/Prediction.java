package com.example.SimpleSpringProject.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Prediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private boolean positive;
}
