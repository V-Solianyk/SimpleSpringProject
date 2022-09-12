package com.example.SimpleSpringProject.mapper;

import com.example.SimpleSpringProject.entity.Prediction;
import com.example.SimpleSpringProject.model.PredictionModel;
import org.springframework.stereotype.Component;

@Component
public class PredictionMapperImpl implements PredictionMapper {
    @Override
    public PredictionModel predictionToPredictionModel(Prediction prediction) {
        PredictionModel predictionModel = new PredictionModel();
        predictionModel.setText(prediction.getText());
        predictionModel.setPositive(prediction.getPositive());
        return predictionModel;
    }

    @Override
    public Prediction predictionModelToPrediction(PredictionModel predictionModel) {
        Prediction prediction = new Prediction();
        prediction.setText(predictionModel.getText());
        prediction.setPositive(predictionModel.isPositive());
        return prediction;
    }
}
