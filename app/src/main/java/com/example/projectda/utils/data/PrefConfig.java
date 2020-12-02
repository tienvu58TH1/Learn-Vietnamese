package com.example.projectda.utils.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.widget.Toast;

import com.example.projectda.R;

import java.io.UnsupportedEncodingException;

public class PrefConfig {

    private SharedPreferences sharedPreferences;
    private Context context;

    public PrefConfig(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences(context.getString(R.string.pref_file),Context.MODE_PRIVATE);
    }

    public void writeLoginStatus(boolean status){
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status),status);
        editor.commit();
    }

    public boolean readLoginStatus(){
        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status),false);
    }

    public void writeDataAlphabetStatus(boolean status){
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_alphabet_status),status);
        editor.commit();
    }

    public boolean readDataAlphabetStatus(){
        return sharedPreferences.getBoolean(context.getString(R.string.pref_alphabet_status),true);
    }

    public void writeName(String name){
        String name1= encodeBase64(name);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_name),name1);
        editor.commit();
    }

    public void writePath(String path){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_path),path);
        editor.commit();
    }

    public void writeLevelWrite(int levelwrite){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(context.getString(R.string.pref_levelwrite),levelwrite);
        editor.commit();
    }

    public void writeIdUser(int iduser){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(context.getString(R.string.pref_iduser),iduser);
        editor.commit();
    }

    public void writeLevelSpeech(int levelspeech){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(context.getString(R.string.pref_levelspeech),levelspeech);
        editor.commit();
    }

    public void writeCheckAdmin(int checkadmin){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(context.getString(R.string.pref_checkadmin),checkadmin);
        editor.commit();
    }

    public int readCheckAdmin(){
        int checkAdmin=sharedPreferences.getInt(context.getString(R.string.pref_checkadmin),0);
        return checkAdmin;
    }

    public int readLevelSpeech(){
        int levelspeech=sharedPreferences.getInt(context.getString(R.string.pref_levelspeech),1);
        return levelspeech;
    }

    public int readIdUser(){
        int id=sharedPreferences.getInt(context.getString(R.string.pref_iduser),-1);
        return id;
    }

    public int readLevelWrite(){
        int levelwrite=sharedPreferences.getInt(context.getString(R.string.pref_levelwrite),1);
        return levelwrite;
    }

    public String readName(){
        String name=decodeBase64(sharedPreferences.getString(context.getString(R.string.pref_name),"user"));
        return name;
    }

    public String readPath(){
        String path=sharedPreferences.getString(context.getString(R.string.pref_path),"path");
        return path;
    }

    public void displayToast(String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    private String encodeBase64(String s){
        byte[] data = new byte[0];
        try {
            data = s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String base64 = Base64.encodeToString(data, Base64.DEFAULT);
        return base64;
    }

    private String decodeBase64(String s){
        byte[] data = Base64.decode(s, Base64.DEFAULT);
        String text="";
        try {
            text = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return text;
    }

    public void writeUserName(String username){
        String name1= encodeBase64(username);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_username),name1);
        editor.commit();
    }
    public String readUserName(){
        String name=decodeBase64(sharedPreferences.getString(context.getString(R.string.pref_username),""));
        return name;
    }

    public void writePassWord(String password){
        String name1= encodeBase64(password);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_password),name1);
        editor.commit();
    }
    public String readPassWord(){
        String name=decodeBase64(sharedPreferences.getString(context.getString(R.string.pref_password),""));
        return name;
    }

    public String readLocate()
    {
        String language = sharedPreferences.getString(context.getString(R.string.pref_mylang),"");
        return language;
    }

    public void writeLocate(String language)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_mylang),language);
        editor.commit();
    }
}
