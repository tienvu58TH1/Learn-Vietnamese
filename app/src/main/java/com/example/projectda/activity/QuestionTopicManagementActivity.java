package com.example.projectda.activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.example.projectda.R;
import com.example.projectda.fragments.QuestionTopicManagementFragment;
import com.example.projectda.models.QuestionTopic;
import com.example.projectda.utils.service.ApiClient;
import com.example.projectda.utils.service.ApiInterface;
import com.example.projectda.utils.CheckConnection;

import java.util.ArrayList;
import java.util.List;

public class QuestionTopicManagementActivity extends AppCompatActivity {
    public static ArrayList<QuestionTopic> mQuestionTopics;
    public static ApiInterface apiInterface;
    public static int pagenumber=0;
    public static int previous_total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_topic_management);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        mQuestionTopics=new ArrayList<>();
        setupView();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            setData();
        }else {
            LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
        }


    }

    private void setupView() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_questiontopic_manager,new QuestionTopicManagementFragment()).commit();
    }

    public static void setData() {
        mQuestionTopics.clear();
        pagenumber=0;
        previous_total=0;
        Call<List<QuestionTopic>> call= apiInterface.getAllQuestionTopics(0);
        call.enqueue(new Callback<List<QuestionTopic>>() {
            @Override
            public void onResponse(Call<List<QuestionTopic>> call, Response<List<QuestionTopic>> response) {
                if (response.body()==null){
                    return;
                }
                mQuestionTopics= (ArrayList<QuestionTopic>) response.body();
                QuestionTopicManagementFragment.updateAdapter();
            }

            @Override
            public void onFailure(Call<List<QuestionTopic>> call, Throwable t) {
                Log.d("BBB",t.getMessage());
            }
        });
    }

    public static void addData() {
        Call<List<QuestionTopic>> call= apiInterface.getAllQuestionTopics(pagenumber);
        call.enqueue(new Callback<List<QuestionTopic>>() {
            @Override
            public void onResponse(Call<List<QuestionTopic>> call, Response<List<QuestionTopic>> response) {
                if (response.body()==null){
                    return;
                }
                for (QuestionTopic questionTopic: response.body()){
                    mQuestionTopics.add(questionTopic);
                }
                QuestionTopicManagementFragment.updateAdapter();
                QuestionTopicManagementFragment.progressBarBottom.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<QuestionTopic>> call, Throwable t) {
                Log.d("BBB",t.getMessage());
                QuestionTopicManagementFragment.progressBarBottom.setVisibility(View.GONE);
            }
        });
    }
}