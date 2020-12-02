package com.example.projectda.models;

public class Alphabet {
    private String text;
    private String audio;
    private String pronunciation;

    public Alphabet(String text, String audio, String pronunciation) {
        this.text = text;
        this.audio = audio;
        this.pronunciation = pronunciation;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
