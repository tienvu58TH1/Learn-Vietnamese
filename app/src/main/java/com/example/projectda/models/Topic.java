package com.example.projectda.models;

import com.google.gson.annotations.SerializedName;

public class Topic {

    @SerializedName("idtopic")
    private int idTopic;

    @SerializedName("numbertopic")
    private int numbertopic;

    @SerializedName("titletopic")
    private String titleTopic;

    @SerializedName("category")
    private int category;

    public Topic(int idTopic, int numbertopic, String titleTopic, int category) {
        this.idTopic = idTopic;
        this.numbertopic = numbertopic;
        this.titleTopic = titleTopic;
        this.category = category;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getNumbertopic() {
        return numbertopic;
    }

    public void setNumbertopic(int numbertopic) {
        this.numbertopic = numbertopic;
    }

    public int getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }

    public String getTitleTopic() {
        return titleTopic;
    }

    public void setTitleTopic(String titleTopic) {
        this.titleTopic = titleTopic;
    }
}
