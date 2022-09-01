package com.example.SimpleSpringProject.repository;

import com.example.SimpleSpringProject.entity.Prediction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionRepository extends CrudRepository<Prediction,Long> {

}
