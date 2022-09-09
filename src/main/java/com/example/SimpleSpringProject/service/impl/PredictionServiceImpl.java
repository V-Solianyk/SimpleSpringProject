package com.example.SimpleSpringProject.service.impl;

import com.example.SimpleSpringProject.entity.Prediction;
import com.example.SimpleSpringProject.mapper.PredictionMapper;
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
    private final PredictionMapper predictionMapper;

    // todo- додати пошук всіх за певними критеріями (+ сортування та пагінація, тобто розбиття на сторінки)
    //-todo юніт тести! про це я зовсім забув:)- змінити тип помилок
    //- повертати в контролері не сутність, а ResponseEntity (клас-обгортка для сутності від спрінга,
    // що дозволяє повертати разом із сутністю необхідний нам http код)


    @Autowired
    public PredictionServiceImpl(PredictionRepository predictionRepository, PredictionMapper mapper) {
        this.predictionRepository = predictionRepository;
        this.predictionMapper = mapper;
    }

    @Override
    public List<PredictionModel> getAll() {
        return predictionRepository.findAll().stream()
                .map(predictionMapper::predictionToPredictionModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<PredictionModel> getAllByPositive(boolean isPositive) {
        return predictionRepository.findAllByPositive(isPositive).stream()
                .map(predictionMapper::predictionToPredictionModel)
                .collect(Collectors.toList());
    }

    @Override
    public PredictionModel get(Long id) {
        Prediction prediction = predictionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prediction not found for ID : " + id));

        return predictionMapper.predictionToPredictionModel(prediction);
    }

    @Override
    public Prediction create(PredictionModel predictionModel) {
        checkPredictionModelTextIsValid(predictionModel);
        Prediction prediction = predictionMapper.predictionModelToPrediction(predictionModel);

        return predictionRepository.save(prediction);
    }


    @Override
    public Prediction update(Long id, PredictionModel predictionModel) {
        predictionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prediction doesn't exist at this address."));

        checkPredictionModelTextIsValid(predictionModel);

        Prediction prediction = predictionMapper.predictionModelToPrediction(predictionModel);
        prediction.setId(id);

        return predictionRepository.save(prediction);
    }

    @Override
    public void delete(Long id) {
        predictionRepository.deleteById(id);
    }

    private void checkPredictionModelTextIsValid(PredictionModel predictionModel) {
        String predictionModelText = predictionModel.getText();
        if (predictionModelText == null) {
            throw new RuntimeException("Error! Text cannot be null.");
        } else if (predictionModelText.isEmpty()) {
            throw new RuntimeException("Error! Text cannot be empty.");
        }
    }
}
