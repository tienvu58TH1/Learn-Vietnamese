package com.example.projectda.activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.projectda.R;
import com.example.projectda.utils.data.PrefConfig;
import com.example.projectda.fragments.TextToSpeechFragment;
import com.example.projectda.models.Question;
import com.example.projectda.models.User;
import com.example.projectda.utils.service.ApiClient;
import com.example.projectda.utils.service.ApiInterface;
import com.example.projectda.utils.CheckConnection;

import java.util.ArrayList;
import java.util.List;

public class TextToSpeechActivity extends AppCompatActivity {

    public static ArrayList<Question> questions;
    private int category=1;
    public static ApiInterface apiInterface;
    public static int levelwrite=0;
    public static PrefConfig prefConfig;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        questions=new ArrayList<>();
        prefConfig=new PrefConfig(this);
        progressBar=findViewById(R.id.progressBar);
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            getLevelWrite();
        }else {
            LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
        }
        if (MainActivity.isPlaying){
            MainActivity.onPauseMp3();
        }

    }

    private void getLevelWrite() {
        Call<User> call= apiInterface.getLevelWrite(prefConfig.readUserName());
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
                    prefConfig.writeLevelWrite(user.getLevelWrite());
                }else if (user.getResponse().equals("error")){
                    prefConfig.displayToast(getResources().getString(R.string.register_error));
                }
                levelwrite=prefConfig.readLevelWrite();
                getQuestionsWithRetrofit();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
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
                questions= (ArrayList<Question>) response.body();
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_tts,new TextToSpeechFragment()).commit();
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                prefConfig.displayToast(t.getMessage());
            }
        });
    }
    public static void updateLevelWrite(){
        if (levelwrite!=prefConfig.readLevelWrite()){
            Call<User> call= apiInterface.updateLevelWrite(prefConfig.readUserName(),levelwrite);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    prefConfig.writeLevelWrite(levelwrite);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    prefConfig.displayToast(t.getMessage());
                }
            });
        }

    }
}