package com.mastercoding.bakalaurinis.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question {
    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("questionType")
    @Expose
    private String questionType;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("options")
    @Expose
    private List<Option> options;

    @SerializedName("questionLevel")
    @Expose
    private String questionLevel;

    @SerializedName("questionTopic")
    @Expose
    private String questionTopic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getQuestionLevel() {
        return questionLevel;
    }

    public void setQuestionLevel(String questionLevel) {
        this.questionLevel = questionLevel;
    }

    public String getQuestionTopic() {
        return questionTopic;
    }

    public void setQuestionTopic(String questionTopic) {
        this.questionTopic = questionTopic;
    }
}