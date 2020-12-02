package com.example.projectda.models;

import com.google.gson.annotations.SerializedName;

public class Track {
    @SerializedName("title")
    private String title;
    @SerializedName("image")
    private String image;
    @SerializedName("author")
    private String author;
    @SerializedName("namecategory")
    private String namecategory;
    @SerializedName("link")
    private String link;

    public Track(String title, String image, String author, String category, String link) {
        this.title = title;
        this.image = image;
        this.author = author;
        this.namecategory = category;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNamecategory() {
        return namecategory;
    }

    public void setNamecategory(String namecategory) {
        this.namecategory = namecategory;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
