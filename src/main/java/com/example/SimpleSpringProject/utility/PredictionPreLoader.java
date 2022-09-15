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
        predictionOne.setText("You should wait a little, and the future will bring you great joy.");
        predictionOne.setPositive(true);

        Prediction predictionTwo = new Prediction();
        predictionTwo.setText("Share joy with others and enjoy it yourself.");
        predictionTwo.setPositive(true);

        Prediction predictionThree = new Prediction();
        predictionThree.setText("Do your best and get the most.");
        predictionThree.setPositive(true);

        Prediction predictionFour = new Prediction();
        predictionFour.setText("When bad weather rages around, your house protects warmth and comfort.");
        predictionFour.setPositive(true);

        Prediction predictionFive = new Prediction();
        predictionFive.setText("New knowledge will bring you success.");
        predictionFive.setPositive(true);

        Prediction predictionSix = new Prediction();
        predictionSix.setText("The weather is bad today");
        predictionSix.setPositive(false);

        Prediction predictionSeven = new Prediction();
        predictionSeven.setText("I haven't slept and I'm in a bad mood");
        predictionSeven.setPositive(false);

        Prediction predictionEight = new Prediction();
        predictionEight.setText("Going back is a bad sign");
        predictionEight.setPositive(false);

        Prediction predictionNight = new Prediction();
        predictionNight.setText("A black cat crossed the road, expect trouble");
        predictionNight.setPositive(false);

        Prediction predictionTen = new Prediction();
        predictionTen.setText("I'm in a bad mood this morning");
        predictionTen.setPositive(false);

        predictionRepository.saveAll(List.of(predictionOne, predictionTwo, predictionThree, predictionFour,
                predictionFive, predictionSix, predictionSeven, predictionEight, predictionNight, predictionTen));

    }
}
