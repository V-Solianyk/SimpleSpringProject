package com.example.SimpleSpringProject.repository;

import com.example.SimpleSpringProject.entity.Prediction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PredictionRepository extends PagingAndSortingRepository<Prediction, Long> {

    List<Prediction> findAllByPositive(boolean isPositive, Pageable pageable);

    List<Prediction> findAllByTextContainsIgnoreCase(String keyword, Pageable pageable);

    List<Prediction> findAllByPositiveAndTextContainsIgnoreCase(boolean isPositive, String keyword, Pageable pageable);

}
