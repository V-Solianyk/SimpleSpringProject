package com.example.SimpleSpringProject.mapper;

import com.example.SimpleSpringProject.entity.Prediction;
import com.example.SimpleSpringProject.model.PredictionModel;

public interface PredictionMapper {
    PredictionModel predictionToPredictionModel(Prediction prediction);

    Prediction predictionModelToPrediction(PredictionModel predictionModel);

}
