package com.example.SalarySurvey.Service;

import com.example.SalarySurvey.Model.SalarySurvey;
import com.example.SalarySurvey.Repository.SalarySurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElasticSearchService {

    @Autowired
    private SalarySurveyRepository salarySurveyRepository;

    public List<SalarySurvey> getSalarySurveys(Float compensation, String location, Sort sort) {

        if (compensation != null && location != null) {
            return salarySurveyRepository.findByCompensationAndLocation(compensation, location, sort);
        } else if (compensation != null) {
            return salarySurveyRepository.findByCompensation(compensation, sort);
        } else if (location != null) {
            return salarySurveyRepository.findByLocation(location, sort);
        } else {
            return salarySurveyRepository.findAll(sort);
        }
    }

    public SalarySurvey getSurveyById(Long id) {
        return salarySurveyRepository.findById(String.valueOf(id)).orElse(null);
    }

}