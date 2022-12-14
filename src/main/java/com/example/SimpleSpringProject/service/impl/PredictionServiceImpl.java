package com.example.SimpleSpringProject.service.impl;

import com.example.SimpleSpringProject.entity.Prediction;
import com.example.SimpleSpringProject.mapper.PredictionMapper;
import com.example.SimpleSpringProject.model.PredictionModel;
import com.example.SimpleSpringProject.repository.PredictionRepository;
import com.example.SimpleSpringProject.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PredictionServiceImpl implements PredictionService {
    private final PredictionRepository predictionRepository;
    private final PredictionMapper predictionMapper;

    @Autowired
    public PredictionServiceImpl(PredictionRepository predictionRepository, PredictionMapper mapper) {
        this.predictionRepository = predictionRepository;
        this.predictionMapper = mapper;
    }

    @Override
    public List<PredictionModel> getAll() {
        return StreamSupport.stream(predictionRepository.findAll().spliterator(), false)
                .map(predictionMapper::predictionToPredictionModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<PredictionModel> getAllByPositive(boolean isPositive, Pageable pageable) {
        return predictionRepository.findAllByPositive(isPositive, pageable).stream()
                .map(predictionMapper::predictionToPredictionModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<PredictionModel> getAllByTextContainsIgnoreCase(String keyword, Pageable pageable) {
        return predictionRepository.findAllByTextContainsIgnoreCase(keyword, pageable).stream()
                .map(predictionMapper::predictionToPredictionModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<PredictionModel> getAllByPositiveAndTextContainsIgnoreCase(boolean isPositive, String keyword,
                                                                           Pageable pageable) {
        return predictionRepository.findAllByPositiveAndTextContainsIgnoreCase(isPositive, keyword, pageable).stream()
                .map(predictionMapper::predictionToPredictionModel)
                .collect(Collectors.toList());
    }

    @Override
    public PredictionModel get(Long id) {
        Prediction prediction = predictionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prediction not found for ID : " + id));

        return predictionMapper.predictionToPredictionModel(prediction);
    }

    @Override
    public PredictionModel create(PredictionModel predictionModel) {
        Prediction prediction = predictionMapper.predictionModelToPrediction(predictionModel);

        predictionRepository.save(prediction);

        return predictionModel;
    }


    @Override
    public PredictionModel update(Long id, PredictionModel predictionModel) {
        predictionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prediction doesn't exist at this address."));

        Prediction prediction = predictionMapper.predictionModelToPrediction(predictionModel);
        prediction.setId(id);
        predictionRepository.save(prediction);
        return predictionModel;
    }

    @Override
    public void delete(Long id) {
        predictionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prediction doesn't exist at this address."));

        predictionRepository.deleteById(id);
    }
}
