package com.example.SalarySurvey.Controller;

import com.example.SalarySurvey.Model.SalarySurvey;
import com.example.SalarySurvey.Repository.SalarySurveyRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/SalarySurveys")
public class SalarySurveyController {
    @Autowired
    private SalarySurveyRespository repository;

    @PostMapping
    public SalarySurvey create(@RequestBody SalarySurvey salarySurvey) {
        return repository.save(salarySurvey);
    }

    @GetMapping
    public Iterable<SalarySurvey> findAll() {
        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }
}
