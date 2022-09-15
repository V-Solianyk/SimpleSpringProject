package com.example.SimpleSpringProject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class PredictionModel {

    private String text;

    private boolean positive;
}
