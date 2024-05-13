package com.mastercoding.bakalaurinis.dtos;

public class RankingDto {
    private String username;
    private Integer correctlyAnsweredCount;

    public RankingDto(String username, Integer correctlyAnsweredCount) {
        this.username = username;
        this.correctlyAnsweredCount = correctlyAnsweredCount;
    }

    public String getUsername() {
        return username;
    }

    public Integer getCorrectlyAnsweredCount() {
        return correctlyAnsweredCount;
    }
}
