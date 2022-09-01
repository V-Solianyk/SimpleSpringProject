package com.example.SimpleSpringProject.service.impl;

import com.example.SimpleSpringProject.entity.Prediction;
import com.example.SimpleSpringProject.repository.PredictionRepository;
import com.example.SimpleSpringProject.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PredictionServiceImpl implements PredictionService {
    private final PredictionRepository predictionRepository;

    @Autowired
    public PredictionServiceImpl(PredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }

    @Override
    public List<Prediction> getAll() {
        predictionRepository.findAll();
        // todo
        return null;
    }

    @Override
    public Prediction get(Long id) {
        var predictionOptional = predictionRepository.findById(id);
        if (predictionOptional.isPresent()) {
            var prediction = predictionOptional.get();
            prediction.setText("Hello " + prediction.getText());
            return prediction;
        } else {
            throw new RuntimeException("Prediction not found!");
        }
    }

    @Override
    public Prediction create(Prediction prediction) {
        return predictionRepository.save(prediction);
    }

    @Override
    public Prediction update(Prediction prediction) {
        var id = prediction.getId();
        Optional<Prediction> optionalPrediction = predictionRepository.findById(id);
        if (optionalPrediction.isPresent()){
            Prediction prediction1 = optionalPrediction.get();
            prediction1.setText(prediction.getText());
            return prediction1;

        } else {
            throw new RuntimeException("Prediction doesn't exist at this address.");
        }


    }

    @Override
    public void delete() {

    }
}
