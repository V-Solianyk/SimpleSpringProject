package com.example.SimpleSpringProject.service;

import com.example.SimpleSpringProject.entity.Prediction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PredictionService {
    List<Prediction> getAll();

    Prediction get(Long id);

    Prediction create(Prediction prediction);

    Prediction update(Prediction prediction);

    void delete(Long id);


}
