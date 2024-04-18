package com.mastercoding.bakalaurinis.dtos;

public class AnswerSubmitRequest {
    private Long questionId;
    private String userAnswer;
    private Long selectedOptionId;
    private String levelName;

    public AnswerSubmitRequest(Long questionId, String userAnswer, Long selectedOptionId, String levelName) {
        this.questionId = questionId;
        this.userAnswer = userAnswer;
        this.selectedOptionId = selectedOptionId;
        this.levelName = levelName;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public Long getSelectedOptionId() {
        return selectedOptionId;
    }

    public String getLevelName() {
        return levelName;
    }
}
