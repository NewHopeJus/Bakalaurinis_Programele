package com.mastercoding.bakalaurinis.dtos;

public class AnswerSubmitResponse {
    private boolean answerCorrect;
    private Integer updatedExperience;
    private Integer updatedCoins;

    public Boolean getAnswerCorrect() {
        return answerCorrect;
    }

    public Integer getUpdatedExperience() {
        return updatedExperience;
    }

    public Integer getUpdatedCoins() {
        return updatedCoins;
    }

    public AnswerSubmitResponse(boolean answerCorrect, Integer updatedExperience, Integer updatedCoins) {
        this.answerCorrect = answerCorrect;
        this.updatedExperience = updatedExperience;
        this.updatedCoins = updatedCoins;
    }
}
