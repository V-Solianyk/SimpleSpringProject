package com.example.SimpleSpringProject.service.impl;

import com.example.SimpleSpringProject.entity.Prediction;
import com.example.SimpleSpringProject.model.PredictionModel;
import com.example.SimpleSpringProject.repository.PredictionRepository;
import com.example.SimpleSpringProject.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PredictionServiceImpl implements PredictionService {
    private final PredictionRepository predictionRepository;

    @Autowired
    public PredictionServiceImpl(PredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }

    @Override
    public List<PredictionModel> getAll() {
        return predictionRepository.findAll().stream()
//                .map(prediction -> PredictionModel.builder()
//                        .text(prediction.getText())
//                        .positive(prediction.isPositive())
//                        .build())
                .map(prediction -> {
                    var predictionModel = new PredictionModel();
                    predictionModel.setText(prediction.getText());
                    predictionModel.setPositive(predictionModel.isPositive());
                    return predictionModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public PredictionModel get(Long id) {
        var prediction = predictionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prediction not found for ID : " + id));

        return PredictionModel.builder()
                .text(prediction.getText())
                .positive(prediction.isPositive())
                .build();
    }

    @Override
    public Prediction create(PredictionModel predictionModel) {
        Prediction prediction = Prediction.builder()
                .text(predictionModel.getText())
                .positive(predictionModel.isPositive())
                .build();
        return predictionRepository.save(prediction);
    }

    @Override
    public Prediction update(Long id, PredictionModel predictionModel) {
        var predictionOld = predictionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prediction doesn't exist at this address."));
        predictionOld.setText(predictionModel.getText());

        if (predictionModel.getText() == null) {
            throw new RuntimeException("Error! Text cannot be null.");
        } else if (predictionModel.getText().isEmpty()) {
            throw new RuntimeException("Error! Text cannot be empty.");
        }
        predictionOld.setPositive(predictionModel.isPositive());

        return predictionRepository.save(predictionOld);
    }

    @Override
    public void delete(Long id) {
        predictionRepository.deleteById(id);
    }
}
