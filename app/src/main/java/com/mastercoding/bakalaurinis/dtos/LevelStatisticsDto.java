package com.mastercoding.bakalaurinis.dtos;

public class LevelStatisticsDto {
    private String levelName;
    private Integer levelCorrectAnswered;
    private Integer totalAnswered;

    public LevelStatisticsDto(String levelName, Integer levelCorrectAnswered, Integer totalAnswered) {
        this.levelName = levelName;
        this.levelCorrectAnswered = levelCorrectAnswered;
        this.totalAnswered = totalAnswered;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getLevelCorrectAnswered() {
        return levelCorrectAnswered;
    }

    public void setLevelCorrectAnswered(Integer levelCorrectAnswered) {
        this.levelCorrectAnswered = levelCorrectAnswered;
    }

    public Integer getTotalAnswered() {
        return totalAnswered;
    }

    public void setTotalAnswered(Integer totalAnswered) {
        this.totalAnswered = totalAnswered;
    }
}
