package com.example.SimpleSpringProject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredictionModel {

    private String text;

    private boolean positive;
}
