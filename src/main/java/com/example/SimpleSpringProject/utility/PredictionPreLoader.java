package com.example.SimpleSpringProject.utility;

import com.example.SimpleSpringProject.entity.Prediction;
import com.example.SimpleSpringProject.repository.PredictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PredictionPreLoader implements ApplicationRunner {
    private final PredictionRepository predictionRepository;

    @Autowired
    public PredictionPreLoader(PredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Prediction predictionOne = new Prediction();
        predictionOne.setText("Ab Hello good day.");
        predictionOne.setPositive(true);

        Prediction predictionTwo = new Prediction();
        predictionTwo.setText("Good day.");
        predictionTwo.setPositive(true);

        Prediction predictionThree = new Prediction();
        predictionThree.setText("hello good day.");
        predictionThree.setPositive(false);

        Prediction predictionFour = new Prediction();
        predictionFour.setText("B good day.");
        predictionFour.setPositive(true);

        Prediction predictionFive = new Prediction();
        predictionFive.setText("Aa helLo Nice day");
        predictionFive.setPositive(true);

        Prediction predictionSix = new Prediction();
        predictionSix.setText("HElLo Nice day");
        predictionSix.setPositive(false);

        predictionRepository.saveAll(List.of(predictionOne, predictionTwo, predictionThree, predictionFour,
                predictionFive, predictionSix));

    }
}
