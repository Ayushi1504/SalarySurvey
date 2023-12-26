package com.example.SalarySurvey.Controller;

import com.example.SalarySurvey.Model.SalarySurvey;
import com.example.SalarySurvey.Repository.SalarySurveyRepository;
import com.example.SalarySurvey.Service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SalarySurveyController {


    @Autowired
    private ElasticSearchService elasticSearchService;
    @GetMapping("/salary_survey")
    public List<SalarySurvey> getSalarySurvey(
            @RequestParam(required = false) Float compensation,
            @RequestParam(required = false) String location,
            @RequestParam(required = false, defaultValue = "asc") String timestamp) {


        // Add validation here later
        Sort sort = Sort.by(Sort.Direction.fromString(timestamp), "timestamp");
        return elasticSearchService.getSalarySurveys(compensation,location,sort);
    }

    @GetMapping("/salary_survey/{id}")
    public SalarySurvey getSurveyById(@PathVariable Long id) {
        return elasticSearchService.getSurveyById(id);
    }
}

