package com.example.projectda.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("response")
    private String Response;

    @SerializedName("name")
    private String Name;

    @SerializedName("imageuser")
    private String Path;

    @SerializedName("levelwrite")
    private int levelWrite;

    @SerializedName("iduser")
    private int idUser;

    @SerializedName("levelspeech")
    private int levelSpeech;

    @SerializedName("checkadmin")
    private int checkAdmin;

    @SerializedName("phone")
    private String phone;

    @SerializedName("email")
    private String email;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPath(String path) {
        Path = path;
    }

    public void setLevelWrite(int levelWrite) {
        this.levelWrite = levelWrite;
    }

    public void setLevelSpeech(int levelSpeech) {
        this.levelSpeech = levelSpeech;
    }

    public int getCheckAdmin() {
        return checkAdmin;
    }

    public void setCheckAdmin(int checkAdmin) {
        this.checkAdmin = checkAdmin;
    }

    public int getLevelWrite() {
        return levelWrite;
    }

    public int getLevelSpeech() {
        return levelSpeech;
    }

    public String getResponse() {
        return Response;
    }

    public String getName() {
        return Name;
    }

    public String getPath(){
        return Path;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
