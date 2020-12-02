package com.example.projectda.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.projectda.R;
import com.example.projectda.utils.data.PrefConfig;
import com.example.projectda.fragments.SpeechToTextFragment;
import com.example.projectda.models.Question;
import com.example.projectda.models.User;
import com.example.projectda.utils.service.ApiClient;
import com.example.projectda.utils.service.ApiInterface;
import com.example.projectda.utils.CheckConnection;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpeechToTextActivity extends AppCompatActivity {

    public static ArrayList<Question> questionsSTT;
    private int category=0;
    public static ApiInterface apiInterface;
    public static int levelspeech=0;
    public static PrefConfig prefConfig;
    private ProgressBar progressBarSTT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        questionsSTT=new ArrayList<>();
        progressBarSTT=findViewById(R.id.progressBarSTT);
        prefConfig=new PrefConfig(this);
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            getLevelSpeech();
        }else {
            LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
        }
        if (MainActivity.isPlaying){
            MainActivity.onPauseMp3();
        }

    }


    private void getLevelSpeech() {
        Call<User> call= apiInterface.getLevelSpeech(prefConfig.readUserName());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()){
                    return;
                }
                User user=response.body();
                if (user==null){
                    return;
                }
                if (user.getResponse().equals("ok")){
                    prefConfig.writeLevelSpeech(user.getLevelSpeech());
                }else if (user.getResponse().equals("error")){
                    prefConfig.displayToast(getResources().getString(R.string.register_error));
                }
                levelspeech=prefConfig.readLevelSpeech();
                getQuestionsWithRetrofit();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                prefConfig.displayToast(t.getMessage());
            }
        });
    }

    private void getQuestionsWithRetrofit() {
        Call<List<Question>> call= apiInterface.getQuestions(category);
        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if (response.body()==null){
                    return;
                }
                questionsSTT= (ArrayList<Question>) response.body();
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_STT,new SpeechToTextFragment()).commit();
                progressBarSTT.setVisibility(View.GONE);

            }
            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                prefConfig.displayToast(t.getMessage());
            }
        });
    }
    public static void updateLevelSpeech(){
        if (levelspeech!=prefConfig.readLevelSpeech()){
            Call<User> call= apiInterface.updateLevelSpeech(prefConfig.readUserName(),levelspeech);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    prefConfig.writeLevelSpeech(levelspeech);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    prefConfig.displayToast(t.getMessage());
                }
            });
        }

    }
}