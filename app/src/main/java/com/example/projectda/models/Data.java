package com.example.projectda.models;

import com.google.gson.annotations.SerializedName;

public class Data  {

    @SerializedName("status")
    private int status;

    @SerializedName("result")
    private Result result;

    public Result getResult() {
        return result;
    }

    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status=status;
    }
}
