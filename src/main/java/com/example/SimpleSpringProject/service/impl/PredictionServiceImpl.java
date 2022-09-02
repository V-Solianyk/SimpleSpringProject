package com.example.SimpleSpringProject.service.impl;

import com.example.SimpleSpringProject.entity.Prediction;
import com.example.SimpleSpringProject.repository.PredictionRepository;
import com.example.SimpleSpringProject.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PredictionServiceImpl implements PredictionService {
    private final PredictionRepository predictionRepository;

    @Autowired
    public PredictionServiceImpl(PredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }

    @Override
    public List<Prediction> getAll() {

        return predictionRepository.findAll().stream()
                .peek(prediction -> prediction.setText("Hello " + prediction.getText()))
                .collect(Collectors.toList());

    }

    @Override
    public Prediction get(Long id) {
        var prediction = predictionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prediction not found for ID : " + id));
        prediction.setText("Hello " + prediction.getText());

        return prediction;
    }

    @Override
    public Prediction create(Prediction prediction) {
        return predictionRepository.save(prediction);
    }

    @Override
    public Prediction update(Prediction prediction) {
        var id = prediction.getId();
        var predictionOld = predictionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prediction doesn't exist at this address."));
        predictionOld.setText(prediction.getText());
        predictionOld.setPositive(prediction.isPositive());

        return predictionOld;
    }

    @Override
    public void delete(Long id) {
        predictionRepository.deleteById(id);
    }
}
