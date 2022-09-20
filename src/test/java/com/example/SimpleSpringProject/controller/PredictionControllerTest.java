package com.example.SimpleSpringProject.controller;

import com.example.SimpleSpringProject.model.PredictionModel;
import com.example.SimpleSpringProject.service.PredictionService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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

        List<PredictionModel> result = predictionController.getAll();

        Assertions.assertEquals(2, result.size());
    }

    @Test
    void getAllByPositive() {
        boolean positive = false;
        PageRequest pageRequest = PageRequest.of(0, 1000);

        PredictionModel predictionModel1 = new PredictionModel();
        predictionModel1.setPositive(positive);

        PredictionModel predictionModel2 = new PredictionModel();
        predictionModel2.setPositive(positive);

        Mockito.when(predictionService.getAllByPositive(positive, pageRequest))
                .thenReturn(List.of(predictionModel1, predictionModel2));

        List<PredictionModel> allByPositive = predictionController.getAllByPositive(positive, pageRequest);
        // todo
        Assertions.assertEquals(2, allByPositive.size());
        Assertions.assertEquals(positive, allByPositive.get(0).isPositive());
        Assertions.assertEquals(positive, allByPositive.get(1).isPositive());
//        Assertions.assertTrue(allByPositive.get(0).isPositive());
//        Assertions.assertTrue(allByPositive.get(1).isPositive());
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

        List<PredictionModel> result = predictionController.getAllByTextLike(keyword,
                pageRequest);

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(StringUtils.containsIgnoreCase(result.get(0).getText(), "hello"));
        Assertions.assertTrue(StringUtils.containsIgnoreCase(result.get(1).getText(), "hello"));
    }

    @Test
    void getAllByPositiveAndTextLike() {
    }

    @Test
    void get() {
        long id = 10L;
        PredictionModel predictionModel = new PredictionModel();

        Mockito.when(predictionService.get(id))
                .thenReturn(predictionModel);

        PredictionModel result = predictionController.get(id);

        Assertions.assertNotNull(result);
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void deletePredictionById() {
        long id = 10L;

        ResponseEntity<PredictionModel> result = predictionController.deletePredictionById(id);

        Mockito.verify(predictionService).delete(id);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
}