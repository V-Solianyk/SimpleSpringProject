package com.example.SimpleSpringProject.service;

import com.example.SimpleSpringProject.entity.Prediction;
import com.example.SimpleSpringProject.model.PredictionModel;

import java.util.List;

public interface PredictionService {
    List< PredictionModel> getAll();

    PredictionModel get(Long id);

    Prediction create(PredictionModel predictionModel);

    Prediction update(Long id, PredictionModel predictionModel);

    void delete(Long id);


}
