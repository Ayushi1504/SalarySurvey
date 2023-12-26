package com.example.SalarySurvey.Model;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "salary_survey")
public class SalarySurvey {

    @Id
    private String id;
    @Field(type = FieldType.Keyword, name = "timestamp")
    private String timestamp;

    @Field(type = FieldType.Integer, name = "age_start")
    private Integer age_start;
    @Field(type = FieldType.Integer, name = "age_end")
    private Integer age_end;

    @Field(type = FieldType.Text, name = "industry")
    private String industry;

    @Field(type = FieldType.Keyword, name = "role")
    private String role;

    @Field(type = FieldType.Keyword, name = "location")
    private String location;
    @Field(type = FieldType.Float, name = "compensation")
    private Float compensation;


    @Field(type = FieldType.Integer, name = "experience")
    private Integer experience;

    @Field(type = FieldType.Boolean, name = "health_insurance")
    private boolean health_insurance;


    @Field(type = FieldType.Keyword, name = "employment_type")
    private String employment_type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getAge_start() {
        return age_start;
    }

    public void setAge_start(Integer age_start) {
        this.age_start = age_start;
    }

    public Integer getAge_end() {
        return age_end;
    }

    public void setAge_end(Integer age_end) {
        this.age_end = age_end;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Float getCompensation() {
        return compensation;
    }

    public void setCompensation(Float compensation) {
        this.compensation = compensation;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public boolean isHealth_insurance() {
        return health_insurance;
    }

    public void setHealth_insurance(boolean health_insurance) {
        this.health_insurance = health_insurance;
    }

    public String getEmployment_type() {
        return employment_type;
    }

    public void setEmployment_type(String employment_type) {
        this.employment_type = employment_type;
    }
}