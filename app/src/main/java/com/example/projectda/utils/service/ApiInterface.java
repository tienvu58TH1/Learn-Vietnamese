package com.example.projectda.utils.service;

import com.example.projectda.models.Data;
import com.example.projectda.models.History;
import com.example.projectda.models.Question;
import com.example.projectda.models.QuestionTopic;
import com.example.projectda.models.Statistic;
import com.example.projectda.models.Topic;
import com.example.projectda.models.Track;
import com.example.projectda.models.User;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("register.php")
    Call<User> performRegistrantion(
            @Field("name") String Name,
            @Field("username") String UserName,
            @Field("password") String PassWord,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("image") String Image
    );

    @FormUrlEncoded
    @POST("updateImageUser.php")
    Call<User> updateImageUser(
            @Field("username") String UserName,
            @Field("image") String Image
    );

    @FormUrlEncoded
    @POST("updateNameUser.php")
    Call<User> updateNameUser(
            @Field("username") String UserName,
            @Field("name") String Name
    );

    @FormUrlEncoded
    @POST("updatePassword.php")
    Call<User> updatePassword(
            @Field("username") String UserName,
            @Field("password") String PassWord,
            @Field("newpassword") String newPassWord
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<User> performUserLogin(
            @Field("username") String UserName,
            @Field("password") String PassWord
    );

    @Multipart
    @POST("/voice/api/asr/v1/rest/decode_file")
    Call<List<Data>> uploadFileData(
            @Part MultipartBody.Part file,
            @Header("token") String token
    );

    @GET("getTracks.php")
    Call<List<Track>> getTracksMp3();


    @GET("getQuestions.php")
    Call<List<Question>> getQuestions(
            @Query("category") int category
    );

    @FormUrlEncoded
    @POST("updateLevelSpeech.php")
    Call<User> updateLevelSpeech(
            @Field("username") String UserName,
            @Field("levelspeech") int levelspeech
    );

    @FormUrlEncoded
    @POST("updateLevelWrite.php")
    Call<User> updateLevelWrite(
            @Field("username") String UserName,
            @Field("levelwrite") int levelwrite
    );

    @GET("getLevelWrite.php")
    Call<User> getLevelWrite(
            @Query("username") String username
    );

    @GET("getLevelSpeech.php")
    Call<User> getLevelSpeech(
            @Query("username") String username
    );

    @GET("getTopLevelWrite.php")
    Call<List<User>> getTopLevelWrite(
    );

    @GET("getTopLevelSpeech.php")
    Call<List<User>> getTopLevelSpeech(
    );

    @FormUrlEncoded
    @POST("insertUserQuestion.php")
    Call<User> insertUserQuestion(
            @Field("iduser") int iduser,
            @Field("idquestion") int idquestion,
            @Field("score") int score,
            @Field("time") long time
    );

    @GET("getTopics.php")
    Call<List<Topic>> getTopics(
            @Query("category") int category
    );

    @GET("getQuestionTopic.php")
    Call<List<QuestionTopic>> getQuestionTraining(
            @Query("idtopic") int idtopic
    );

    @GET("getAllQuestions.php")
    Call<List<Question>> getAllQuestions(
            @Query("page") int page
    );

    @FormUrlEncoded
    @POST("updateQuestion.php")
    Call<User> updateQuestion(
            @Field("idquestion") int idquestion,
            @Field("level") int level,
            @Field("scorepass") int scorepass,
            @Field("category") int category,
            @Field("content") String content
    );

    @FormUrlEncoded
    @POST("insertQuestion.php")
    Call<User> insertQuestion(
            @Field("level") int level,
            @Field("scorepass") int scorepass,
            @Field("category") int category,
            @Field("content") String content
    );

    @FormUrlEncoded
    @POST("deleteQuestion.php")
    Call<User> deleteQuestion(
            @Field("idquestion") int idquestion
    );

    @GET("getAllTopics.php")
    Call<List<Topic>> getAllTopics(
    );

    @FormUrlEncoded
    @POST("updateTopic.php")
    Call<User> updateTopic(
            @Field("idtopic") int idtopic,
            @Field("numbertopic") int numbertopic,
            @Field("titletopic") String titletopic,
            @Field("category") int category
    );

    @FormUrlEncoded
    @POST("insertTopic.php")
    Call<User> insertTopic(
            @Field("numbertopic") int numbertopic,
            @Field("titletopic") String titletopic,
            @Field("category") int category
    );

    @FormUrlEncoded
    @POST("deleteTopic.php")
    Call<User> deleteTopic(
            @Field("idtopic") int idtopic
    );

    @GET("getAllQuestionTopics.php")
    Call<List<QuestionTopic>> getAllQuestionTopics(
            @Query("page") int page
    );

    @FormUrlEncoded
    @POST("updateQuestionTopic.php")
    Call<User> updateQuestionTopic(
            @Field("idquestiontopic") int idquestiontopic,
            @Field("level") int level,
            @Field("idtopic") int idtopic,
            @Field("translate") String translate,
            @Field("content") String content
    );

    @FormUrlEncoded
    @POST("insertQuestionTopic.php")
    Call<User> insertQuestionTopic(
            @Field("level") int level,
            @Field("idtopic") int idtopic,
            @Field("translate") String translate,
            @Field("content") String content
    );

    @FormUrlEncoded
    @POST("deleteQuestionTopic.php")
    Call<User> deleteQuestionTopic(
            @Field("idquestiontopic") int idquestiontopic
    );

    @GET("getUserQuestion.php")
    Call<List<History>> getHistoryUser(
            @Query("iduser") int iduser,
            @Query("category") int category,
            @Query("page") int page
    );

    @GET("getMediumScore.php")
    Call<List<Statistic>> getMediumScore(
            @Query("iduser") int iduser,
            @Query("page") int page
    );

}
