package com.example.projectda.models;

import com.google.gson.annotations.SerializedName;

public class Statistic {

    @SerializedName("level")
    private int level;

    @SerializedName("category")
    private int category;

    @SerializedName("mediumscore")
    private double mediumscore;

    @SerializedName("count")
    private int count;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getMediumscore() {
        return mediumscore;
    }

    public void setMediumscore(double mediumscore) {
        this.mediumscore = mediumscore;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
