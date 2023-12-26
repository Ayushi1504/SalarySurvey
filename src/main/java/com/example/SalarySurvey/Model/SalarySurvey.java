package com.example.SalarySurvey.Model;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "salary_survey")
public class SalarySurvey {

    private String timestamp;
    private String age_start;
    private String age_end;
    private String industry;
    private String role;
    private String location;
    private String compensation;
    private String experience;
    private String health_insurance;
    private String employment_type;

}