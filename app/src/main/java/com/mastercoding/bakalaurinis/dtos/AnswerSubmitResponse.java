package com.mastercoding.bakalaurinis.dtos;

public class AnswerSubmitResponse {
    private boolean answerCorrect;
    private Integer updatedExperience;
    private Integer updatedCoins;

    private String correctAnswerText;

    public AnswerSubmitResponse(boolean answerCorrect, Integer updatedExperience, Integer updatedCoins, String correctAnswerText) {
        this.answerCorrect = answerCorrect;
        this.updatedExperience = updatedExperience;
        this.updatedCoins = updatedCoins;
        this.correctAnswerText = correctAnswerText;
    }

    public boolean isAnswerCorrect() {
        return answerCorrect;
    }

    public void setAnswerCorrect(boolean answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    public Integer getUpdatedExperience() {
        return updatedExperience;
    }

    public void setUpdatedExperience(Integer updatedExperience) {
        this.updatedExperience = updatedExperience;
    }

    public Integer getUpdatedCoins() {
        return updatedCoins;
    }

    public void setUpdatedCoins(Integer updatedCoins) {
        this.updatedCoins = updatedCoins;
    }

    public String getCorrectAnswerText() {
        return correctAnswerText;
    }

    public void setCorrectAnswerText(String correctAnswerText) {
        this.correctAnswerText = correctAnswerText;
    }
}
