package com.example.SimpleSpringProject.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Data
@Entity
public class Prediction {
    @Id
    private Long id;
    private String text;
    private boolean isPositive;


}
