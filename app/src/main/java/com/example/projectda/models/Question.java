package com.example.projectda.models;

import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("idquestion")
    private int idQuestion;

    @SerializedName("level")
    private int level;

    @SerializedName("content")
    private String content;

    @SerializedName("scorepass")
    private int scorepass;

    @SerializedName("category")
    private int category;

    public Question(int idQuestion, int level, String content, int scorepass, int category) {
        this.idQuestion = idQuestion;
        this.level = level;
        this.content = content;
        this.scorepass = scorepass;
        this.category = category;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScorepass() {
        return scorepass;
    }

    public void setScorepass(int scorepass) {
        this.scorepass = scorepass;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
