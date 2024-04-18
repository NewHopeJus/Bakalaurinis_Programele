package com.mastercoding.bakalaurinis.dtos;

import java.util.List;

public class StatisticsResponse {
    private Integer totalCorrectlyAnswered;
    private Integer totalAnswered;
    private List<LevelStatisticsDto> levelsStatistics;

    public StatisticsResponse(Integer totalCorrectlyAnswered, Integer totalAnswered, List<LevelStatisticsDto> levelsStatistics) {
        this.totalCorrectlyAnswered = totalCorrectlyAnswered;
        this.totalAnswered = totalAnswered;
        this.levelsStatistics = levelsStatistics;
    }

    public Integer getTotalCorrectlyAnswered() {
        return totalCorrectlyAnswered;
    }

    public void setTotalCorrectlyAnswered(Integer totalCorrectlyAnswered) {
        this.totalCorrectlyAnswered = totalCorrectlyAnswered;
    }

    public Integer getTotalAnswered() {
        return totalAnswered;
    }

    public void setTotalAnswered(Integer totalAnswered) {
        this.totalAnswered = totalAnswered;
    }

    public List<LevelStatisticsDto> getLevelsStatistics() {
        return levelsStatistics;
    }

    public void setLevelsStatistics(List<LevelStatisticsDto> levelsStatistics) {
        this.levelsStatistics = levelsStatistics;
    }
}
