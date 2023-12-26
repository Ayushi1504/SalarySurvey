package com.example.SalarySurvey.Repository;


import com.example.SalarySurvey.Model.SalarySurvey;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SalarySurveyRespository extends ElasticsearchRepository<SalarySurvey, String> {
}