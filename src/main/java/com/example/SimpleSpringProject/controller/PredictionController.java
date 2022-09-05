package com.example.SimpleSpringProject.controller;

import com.example.SimpleSpringProject.entity.Prediction;
import com.example.SimpleSpringProject.model.PredictionModel;
import com.example.SimpleSpringProject.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
    List<PredictionModel> getAll() {
        return predictionService.getAll();
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
    void deletePredictionById(@PathVariable Long id) {
        predictionService.delete(id);
    }


}
