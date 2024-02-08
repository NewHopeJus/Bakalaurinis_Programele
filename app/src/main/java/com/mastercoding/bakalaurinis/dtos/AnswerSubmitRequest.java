package com.mastercoding.bakalaurinis.dtos;

public class AnswerSubmitRequest {
    private Long questionId;
    private String userAnswer;
    private Long selectedOptionId;

    public AnswerSubmitRequest(Long questionId, String userAnswer, Long selectedOptionId) {
        this.questionId = questionId;
        this.userAnswer = userAnswer;
        this.selectedOptionId = selectedOptionId;
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


}
