package com.example.SimpleSpringProject.repository;

import com.example.SimpleSpringProject.entity.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PredictionRepository extends JpaRepository<Prediction,Long> {
    List<Prediction> findAllByPositive(boolean isPositive);
    List<Prediction> findAllByTextLike(String keyword);
    List<Prediction> findAllByPositiveAndTextLike(boolean isPositive,String keyword);

}
