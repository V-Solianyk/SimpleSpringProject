package com.example.SimpleSpringProject.controller;

import com.example.SimpleSpringProject.model.PredictionModel;
import com.example.SimpleSpringProject.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/predictions")
public class PredictionController {
    private final PredictionService predictionService;

    @Autowired
    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @GetMapping
    ResponseEntity<List<PredictionModel>> getAll() {
        return ResponseEntity.ok(predictionService.getAll());
    }

    @GetMapping("/positive")
    ResponseEntity<List<PredictionModel>> getAllByPositive(@RequestParam("positive") boolean isPositive, Pageable pageable) {
        return ResponseEntity.ok(predictionService.getAllByPositive(isPositive, pageable));
    }

    @GetMapping("/keyword")
    ResponseEntity<List<PredictionModel>> getAllByTextLike(@RequestParam("keyword") String keyword, Pageable pageable) {
        return ResponseEntity.ok(predictionService.getAllByTextContainsIgnoreCase(keyword, pageable));
    }

    @GetMapping("/keywordAndPositive")
    ResponseEntity<List<PredictionModel>> getAllByPositiveAndTextLike(@RequestParam("positive") boolean isPositive,
                                                                      @RequestParam("keyword") String keyword, Pageable pageable) {
        return ResponseEntity.ok(predictionService.getAllByPositiveAndTextContainsIgnoreCase(isPositive, keyword,
                pageable));
    }

    @GetMapping("/{id}")
    ResponseEntity<PredictionModel> get(@PathVariable Long id) {
        return ResponseEntity.ok(predictionService.get(id));
    }

    @PostMapping
    ResponseEntity<PredictionModel> create(@RequestBody PredictionModel predictionModel) {
        return new ResponseEntity<PredictionModel>(predictionService.create(predictionModel), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<PredictionModel> update(@PathVariable Long id, @RequestBody PredictionModel predictionModel) {
        return ResponseEntity.ok(predictionService.update(id, predictionModel));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<PredictionModel> deletePredictionById(@PathVariable Long id) {
        predictionService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
