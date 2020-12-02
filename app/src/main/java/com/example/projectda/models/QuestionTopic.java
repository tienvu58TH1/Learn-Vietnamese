package com.example.projectda.models;

import com.google.gson.annotations.SerializedName;

public class QuestionTopic {

    @SerializedName("idquestiontopic")
    private int idQuestion;

    @SerializedName("level")
    private int level;

    @SerializedName("content")
    private String content;

    @SerializedName("translate")
    private String translate;

    @SerializedName("idtopic")
    private int idtopic;

    public QuestionTopic(int idQuestion, int level, String content, int idtopic) {
        this.idQuestion = idQuestion;
        this.level = level;
        this.content = content;
        this.idtopic = idtopic;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public int getIdtopic() {
        return idtopic;
    }

    public void setIdtopic(int idtopic) {
        this.idtopic = idtopic;
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
}
