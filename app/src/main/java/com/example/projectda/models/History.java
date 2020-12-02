package com.example.projectda.models;

import com.google.gson.annotations.SerializedName;

public class History {

    @SerializedName("level")
    private int level;

    @SerializedName("score")
    private int score;

    @SerializedName("time")
    private long time;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
