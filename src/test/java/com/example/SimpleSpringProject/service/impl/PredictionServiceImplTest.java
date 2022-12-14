package com.example.SimpleSpringProject.service.impl;

import com.example.SimpleSpringProject.entity.Prediction;
import com.example.SimpleSpringProject.mapper.PredictionMapper;
import com.example.SimpleSpringProject.model.PredictionModel;
import com.example.SimpleSpringProject.repository.PredictionRepository;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


class PredictionServiceImplTest {
    PredictionRepository predictionRepository = Mockito.mock(PredictionRepository.class);
    PredictionMapper predictionMapper = Mockito.mock(PredictionMapper.class);
    PredictionServiceImpl predictionService = new PredictionServiceImpl(predictionRepository, predictionMapper);

    @Test
    void getAll() {
        Prediction predictionOne = new Prediction();
        Prediction predictionTwo = new Prediction();

        Mockito.when(predictionRepository.findAll())
                .thenReturn(Arrays.asList(predictionOne, predictionTwo));

        Mockito.when(predictionMapper.predictionToPredictionModel(Mockito.any(Prediction.class)))
                .thenReturn(new PredictionModel());

        List<PredictionModel> result = predictionService.getAll();

        Assertions.assertEquals(2, result.size());
        Assertions.assertNotNull(result.get(0));
        Assertions.assertNotNull(result.get(1));

    }

    @Test
    void getAllByPositive() {
        Prediction predictionOne = new Prediction();

        Prediction predictionTwo = new Prediction();

        Pageable pageable = PageRequest.of(0, 100);

        PredictionModel predictionModel = new PredictionModel();
        predictionModel.setPositive(true);

        Mockito.when(predictionRepository.findAllByPositive(true, pageable))
                .thenReturn(Arrays.asList(predictionOne, predictionTwo));

        Mockito.when(predictionMapper.predictionToPredictionModel(Mockito.any(Prediction.class)))
                .thenReturn(predictionModel);

        List<PredictionModel> result = predictionService.getAllByPositive(true, pageable);

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.get(0).isPositive());
        Assertions.assertTrue(result.get(1).isPositive());

    }

    @Test
    void getAllByTextContainsIgnoreCase() {
        Prediction predictionOne = new Prediction();

        Prediction predictionTwo = new Prediction();

        Pageable pageable = PageRequest.of(0, 100);

        PredictionModel predictionModel = new PredictionModel();
        predictionModel.setText("hello");

        Mockito.when(predictionRepository.findAllByTextContainsIgnoreCase("hello", pageable))
                .thenReturn(Arrays.asList(predictionOne, predictionTwo));

        Mockito.when(predictionMapper.predictionToPredictionModel(Mockito.any(Prediction.class)))
                .thenReturn(predictionModel);

        List<PredictionModel> result = predictionService.getAllByTextContainsIgnoreCase("hello", pageable);

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(StringUtils.containsIgnoreCase(result.get(0).getText(), "hello"));
        Assertions.assertTrue(StringUtils.containsIgnoreCase(result.get(1).getText(), "hello"));
    }

    @Test
    void getAllByPositiveAndTextContainsIgnoreCase() {
        Prediction predictionOne = new Prediction();

        Prediction predictionTwo = new Prediction();

        Pageable pageable = PageRequest.of(0, 100);

        PredictionModel predictionModel = new PredictionModel();
        predictionModel.setPositive(true);
        predictionModel.setText("Have a good day");

        Mockito.when(predictionRepository.findAllByPositiveAndTextContainsIgnoreCase(true, "good",
                        pageable))
                .thenReturn(Arrays.asList(predictionOne, predictionTwo));

        Mockito.when(predictionMapper.predictionToPredictionModel(Mockito.any(Prediction.class)))
                .thenReturn(predictionModel);

        List<PredictionModel> result = predictionService.getAllByPositiveAndTextContainsIgnoreCase(true,
                "good", pageable);

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(StringUtils.containsIgnoreCase(result.get(0).getText(), "good"));
        Assertions.assertTrue(result.get(0).isPositive());
        Assertions.assertTrue(StringUtils.containsIgnoreCase(result.get(1).getText(), "good"));
        Assertions.assertTrue(result.get(1).isPositive());

    }

    @Test
    void get_NotExists() {
        Long notExistingId = 10L;
        String expected = "Prediction not found for ID : " + notExistingId;

        Mockito.when(predictionRepository.findById(notExistingId))
                .thenReturn(Optional.empty());

        EntityNotFoundException entityNotFoundException = Assertions.assertThrows(EntityNotFoundException.class,
                () -> predictionService.get(notExistingId));

        Assertions.assertEquals(expected, entityNotFoundException.getMessage());
    }

    @Test
    void get_Exists() {
        Long existingId = 5L;
        Prediction prediction = new Prediction();
        prediction.setText("Good day");
        prediction.setPositive(true);

        PredictionModel predictionModel = new PredictionModel();
        predictionModel.setText(prediction.getText());
        predictionModel.setPositive(prediction.getPositive());

        Mockito.when(predictionRepository.findById(existingId))
                .thenReturn(Optional.of(prediction));

        Mockito.when(predictionMapper.predictionToPredictionModel(prediction))
                .thenReturn(predictionModel);

        PredictionModel result = predictionService.get(existingId);

        Assertions.assertEquals(prediction.getPositive(), result.isPositive());
        Assertions.assertEquals(prediction.getText(), result.getText());

    }

    @Test
    void create() {
        PredictionModel predictionModel = new PredictionModel();
        predictionModel.setText("Ok nice to meet you");
        predictionModel.setPositive(true);

        Prediction prediction = new Prediction();
        prediction.setText(predictionModel.getText());
        prediction.setPositive(predictionModel.isPositive());

        Mockito.when(predictionMapper.predictionModelToPrediction(predictionModel))
                .thenReturn(prediction);

        Mockito.when(predictionRepository.save(Mockito.any(Prediction.class))).thenReturn(prediction);

        PredictionModel result = predictionService.create(predictionModel);

        Assertions.assertEquals(predictionModel.getText(), result.getText());
        Assertions.assertEquals(predictionModel.isPositive(), result.isPositive());

    }

    @Test
    void update_NotExists() {
        Long notExistingId = 20L;
        PredictionModel predictionModel = new PredictionModel();
        String expectedException = "Prediction doesn't exist at this address.";

        Mockito.when(predictionRepository.findById(notExistingId))
                .thenReturn(Optional.empty());

        EntityNotFoundException entityNotFoundException =
                Assertions.assertThrows(EntityNotFoundException.class,
                        () -> predictionService.update(notExistingId, predictionModel));

        Assertions.assertEquals(expectedException, entityNotFoundException.getMessage());
    }

    @Test
    void update_Exists() {
        Long existingId = 33L;

        PredictionModel predictionModel = new PredictionModel();
        predictionModel.setPositive(true);
        predictionModel.setText("Hello, I'll try to learn English.");

        Prediction prediction = new Prediction();
        prediction.setPositive(predictionModel.isPositive());
        prediction.setText(predictionModel.getText());

        Mockito.when(predictionRepository.findById(existingId))
                .thenReturn(Optional.of(prediction));

        Mockito.when(predictionMapper.predictionModelToPrediction(predictionModel))
                .thenReturn(prediction);

        Mockito.when(predictionRepository.save(Mockito.any(Prediction.class)))
                .thenReturn(prediction);

        PredictionModel result = predictionService.update(existingId, predictionModel);

        Assertions.assertEquals(predictionModel.isPositive(), result.isPositive());
        Assertions.assertEquals(predictionModel.getText(), result.getText());
    }

    @Test
    void delete_NotExists() {
        Long notExistingId = 10000L;
        String expectedException = "Prediction doesn't exist at this address.";

        Mockito.when(predictionRepository.findById(notExistingId))
                .thenReturn(Optional.empty());

        EntityNotFoundException entityNotFoundException = Assertions.assertThrows(EntityNotFoundException.class,
                () -> predictionService.delete(notExistingId));

        Assertions.assertEquals(expectedException, entityNotFoundException.getMessage());
    }

    @Test
    void delete_Exists() {
        Long existingId = 555L;
        Prediction prediction = new Prediction();

        Mockito.when(predictionRepository.findById(existingId))
                .thenReturn(Optional.of(prediction));

        predictionService.delete(existingId);
        Mockito.verify(predictionRepository).deleteById(existingId);
    }
}