package com.example.SimpleSpringProject.mapper.impl;

import com.example.SimpleSpringProject.entity.Prediction;
import com.example.SimpleSpringProject.model.PredictionModel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PredictionMapperImplTest {
    PredictionMapperImpl predictionMapper = new PredictionMapperImpl();

    @Test
    void predictionToPredictionModel() {
        Prediction prediction = new Prediction();
        prediction.setText("Hello have a good day.");
        prediction.setPositive(true);

        PredictionModel predictionModelResult = predictionMapper.predictionToPredictionModel(prediction);

        Assertions.assertEquals(prediction.getText(), predictionModelResult.getText());
        Assertions.assertEquals(prediction.getPositive(), predictionModelResult.isPositive());

    }

    @Test
    void predictionModelToPrediction() {
        PredictionModel predictionModel = new PredictionModel();
        predictionModel.setText("Hello have a good day.");
        predictionModel.setPositive(true);

        Prediction predictionResult = predictionMapper.predictionModelToPrediction(predictionModel);

        Assertions.assertEquals(predictionModel.getText(), predictionResult.getText());
        Assertions.assertEquals(predictionModel.isPositive(), predictionResult.getPositive());

    }
}