package com.example.SimpleSpringProject.service;

import com.example.SimpleSpringProject.entity.Prediction;
import com.example.SimpleSpringProject.model.PredictionModel;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PredictionService {
    List<PredictionModel> getAll();

    List<PredictionModel> getAllByPositive(boolean isPositive, Pageable pageable);

    List<PredictionModel> getAllByTextContainsIgnoreCase(String keyword, Pageable pageable);

    List<PredictionModel> getAllByPositiveAndTextContainsIgnoreCase(boolean isPositive, String keyword,
                                                                    Pageable pageable);

    PredictionModel get(Long id);

    Prediction create(PredictionModel predictionModel);

    Prediction update(Long id, PredictionModel predictionModel);

    void delete(Long id);


}
