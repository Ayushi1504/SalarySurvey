package com.example.SalarySurvey.Repository;

import org.springframework.data.domain.Sort;
import com.example.SalarySurvey.Model.SalarySurvey;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface SalarySurveyRepository extends ElasticsearchRepository<SalarySurvey, String> {
    List<SalarySurvey> findByCompensationAndLocation(Float compensation, String location, Sort sort);

    List<SalarySurvey> findByCompensation(Float compensation, Sort sort);

    List<SalarySurvey> findByLocation(String location, Sort sort);
    List<SalarySurvey> findAll(Sort sort);
}