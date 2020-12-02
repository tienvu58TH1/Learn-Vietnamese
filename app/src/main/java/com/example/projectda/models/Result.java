package com.example.projectda.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("hypotheses")
    private List<Hypotheses> hypotheses;

    public List<Hypotheses> getHypotheses() {
        return hypotheses;
    }
}
