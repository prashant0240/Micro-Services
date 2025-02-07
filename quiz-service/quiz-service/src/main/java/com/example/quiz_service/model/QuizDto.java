package com.example.quiz_service.model;


import lombok.Data;

@Data
public class QuizDto {
    String category;
    Integer numQuestion;
    String title;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getNumQuestion() {
        return numQuestion;
    }

    public void setNumQuestion(Integer numQuestion) {
        this.numQuestion = numQuestion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
