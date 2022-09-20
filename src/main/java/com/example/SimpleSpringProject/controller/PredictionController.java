package com.example.SimpleSpringProject.controller;

import com.example.SimpleSpringProject.entity.Prediction;
import com.example.SimpleSpringProject.model.PredictionModel;
import com.example.SimpleSpringProject.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/predictions")
public class PredictionController {
    private final PredictionService predictionService;

    @Autowired
    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @GetMapping
    List<PredictionModel> getAll() {
        return predictionService.getAll();
    }

    @GetMapping("/positive")
    List<PredictionModel> getAllByPositive(@RequestParam("positive") boolean isPositive, Pageable pageable) {
        return predictionService.getAllByPositive(isPositive, pageable);
        // List<PredictionModel> allByPositive = predictionService.getAllByPositive(isPositive, pageable);
        //        return allByPositive.stream()
        //                .peek(predictionModel -> predictionModel.setPositive(true))
        //                .collect(Collectors.toList());
        //todo
    }

    @GetMapping("/keyword")
    List<PredictionModel> getAllByTextLike(@RequestParam("keyword") String keyword, Pageable pageable) {
        return predictionService.getAllByTextContainsIgnoreCase(keyword, pageable);
    }

    @GetMapping("/keywordAndPositive")
    List<PredictionModel> getAllByPositiveAndTextLike(@RequestParam("positive") boolean isPositive,
                                                      @RequestParam("keyword") String keyword, Pageable pageable) {
        return predictionService.getAllByPositiveAndTextContainsIgnoreCase(isPositive, keyword, pageable);
    }

    @GetMapping("/{id}")
    PredictionModel get(@PathVariable Long id) {
        return predictionService.get(id);
    }

    @PostMapping
    Prediction create(@RequestBody PredictionModel predictionModel) {
        return predictionService.create(predictionModel);
    }

    @PutMapping("/{id}")
    Prediction update(@PathVariable Long id, @RequestBody PredictionModel predictionModel) {
        return predictionService.update(id, predictionModel);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<PredictionModel> deletePredictionById(@PathVariable Long id) {
        predictionService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
