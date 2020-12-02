package com.example.projectda.models;

import com.google.gson.annotations.SerializedName;

public class Hypotheses {
    @SerializedName("transcript")
    private String transcript;

    @SerializedName("transcript_normed")
    private String transcript_normed;

    public String getTranscript() {
        return transcript;
    }

    public String getTranscript_normed() {
        return transcript_normed;
    }
}
