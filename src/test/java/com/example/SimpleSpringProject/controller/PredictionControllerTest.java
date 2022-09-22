package com.example.SimpleSpringProject.controller;

import com.example.SimpleSpringProject.model.PredictionModel;
import com.example.SimpleSpringProject.service.PredictionService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

class PredictionControllerTest {
    PredictionService predictionService = Mockito.mock(PredictionService.class);
    PredictionController predictionController = new PredictionController(predictionService);

    @Test
    void getAll() {
        Mockito.when(predictionService.getAll())
                .thenReturn(List.of(new PredictionModel(), new PredictionModel()));

        ResponseEntity<List<PredictionModel>> result = predictionController.getAll();

        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(2, result.getBody().size());
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void getAllByPositive() {
        boolean positive = true;
        PageRequest pageRequest = PageRequest.of(0, 1000);

        PredictionModel predictionModel1 = new PredictionModel();
        predictionModel1.setPositive(positive);

        PredictionModel predictionModel2 = new PredictionModel();
        predictionModel2.setPositive(positive);

        Mockito.when(predictionService.getAllByPositive(positive, pageRequest))
                .thenReturn(List.of(predictionModel1, predictionModel2));

        ResponseEntity<List<PredictionModel>> allByPositive = predictionController.getAllByPositive(positive, pageRequest);

        Assertions.assertNotNull(allByPositive.getBody());
        Assertions.assertEquals(2, allByPositive.getBody().size());
        Assertions.assertEquals(positive, allByPositive.getBody().get(0).isPositive());
        Assertions.assertEquals(positive, allByPositive.getBody().get(1).isPositive());
        Assertions.assertEquals(HttpStatus.OK, allByPositive.getStatusCode());
    }

    @Test
    void getAllByTextLike() {
        String keyword = "Hello";
        PageRequest pageRequest = PageRequest.of(0, 999);

        PredictionModel predictionModel1 = new PredictionModel();
        predictionModel1.setText("Hello world");

        PredictionModel predictionModel2 = new PredictionModel();
        predictionModel2.setText("Hello nice to meet you");

        Mockito.when(predictionService.getAllByTextContainsIgnoreCase(keyword, pageRequest))
                .thenReturn(List.of(predictionModel1, predictionModel2));

        ResponseEntity<List<PredictionModel>> result = predictionController.getAllByTextLike(keyword,
                pageRequest);

        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(2, result.getBody().size());
        Assertions.assertTrue(StringUtils.containsIgnoreCase(result.getBody().get(0).getText(), "hello"));
        Assertions.assertTrue(StringUtils.containsIgnoreCase(result.getBody().get(1).getText(), "hello"));
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void getAllByPositiveAndTextLike() {
        String keyword = "Hello";
        boolean positive = true;
        PageRequest pageRequest = PageRequest.of(0, 100);

        PredictionModel predictionModel1 = new PredictionModel();
        predictionModel1.setText("Hello my name is Vladyslav");
        predictionModel1.setPositive(true);

        PredictionModel predictionModel2 = new PredictionModel();
        predictionModel2.setText("hello nice to meet you");
        predictionModel2.setPositive(true);

        Mockito.when(predictionService.getAllByPositiveAndTextContainsIgnoreCase(positive, keyword, pageRequest))
                .thenReturn(List.of(predictionModel1, predictionModel2));

        ResponseEntity<List<PredictionModel>> response = predictionController
                .getAllByPositiveAndTextLike(positive, keyword, pageRequest);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());
        Assertions.assertTrue(StringUtils.containsIgnoreCase(response.getBody().get(0).getText(), keyword));
        Assertions.assertEquals(positive, response.getBody().get(0).isPositive());
        Assertions.assertTrue(StringUtils.containsIgnoreCase(response.getBody().get(1).getText(), keyword));
        Assertions.assertEquals(positive, response.getBody().get(1).isPositive());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void get() {
        long id = 10L;
        PredictionModel predictionModel = new PredictionModel();

        Mockito.when(predictionService.get(id))
                .thenReturn(predictionModel);

        ResponseEntity<PredictionModel> result = predictionController.get(id);

        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void create() {
        PredictionModel predictionModel = new PredictionModel();

        Mockito.when(predictionService.create(predictionModel)).thenReturn(predictionModel);

        ResponseEntity<PredictionModel> response = predictionController.create(predictionModel);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    void update() {
        long id = 1000000L;
        PredictionModel predictionModel = new PredictionModel();

        Mockito.when(predictionService.update(id, predictionModel)).thenReturn(predictionModel);

        ResponseEntity<PredictionModel> resultUpdate = predictionController.update(id, predictionModel);

        Assertions.assertNotNull(resultUpdate.getBody());
        Assertions.assertEquals(HttpStatus.OK, resultUpdate.getStatusCode());
    }

    @Test
    void deletePredictionById() {
        long id = 10L;

        ResponseEntity<PredictionModel> result = predictionController.deletePredictionById(id);

        Mockito.verify(predictionService).delete(id);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
}