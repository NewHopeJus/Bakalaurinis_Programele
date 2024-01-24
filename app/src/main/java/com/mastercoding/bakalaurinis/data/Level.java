package com.mastercoding.bakalaurinis.data;

import java.util.List;

public class Level {
    private String levelName;
    private List<String> topics;

    public Level(String levelName, List<String> topics) {
        this.levelName = levelName;
        this.topics = topics;
    }

    public String getLevelName() {
        return levelName;
    }

    public List<String> getTopics() {
        return topics;
    }
}
