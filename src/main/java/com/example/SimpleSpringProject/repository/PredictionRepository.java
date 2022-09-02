package com.example.SimpleSpringProject.repository;

import com.example.SimpleSpringProject.entity.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction,Long> {

}
