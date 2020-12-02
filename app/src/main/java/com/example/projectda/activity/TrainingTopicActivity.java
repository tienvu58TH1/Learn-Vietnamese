package com.example.projectda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.projectda.R;
import com.example.projectda.adapter.TopicAdapter;
import com.example.projectda.callback.CallbackTopic;
import com.example.projectda.models.Topic;
import com.example.projectda.utils.service.ApiClient;
import com.example.projectda.utils.service.ApiInterface;
import com.example.projectda.utils.CheckConnection;

import java.util.ArrayList;
import java.util.List;

public class TrainingTopicActivity extends AppCompatActivity {
    private ArrayList<Topic> mTopics;
    private Toolbar tbTopic;
    private RecyclerView recyclerView;
    private TopicAdapter topicAdapter;
    public static ApiInterface apiInterface;
    private int category=0;
    private ProgressBar progressBarTopic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_topic);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mTopics=new ArrayList<>();
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Intent intent=getIntent();
        category=intent.getIntExtra("category",2);
        progressBarTopic=findViewById(R.id.progressBarTopic);
        actionBar();
        setupView();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            setData();
        }else {
            LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
        }

        if (MainActivity.isPlaying){
            MainActivity.onPauseMp3();
        }
    }

    private void setData() {
        Call<List<Topic>> callListTopic=apiInterface.getTopics(category);
        callListTopic.enqueue(new Callback<List<Topic>>() {
            @Override
            public void onResponse(Call<List<Topic>> call, Response<List<Topic>> response) {
                progressBarTopic.setVisibility(View.GONE);
                if (response.body()==null){
                    return;
                }
                mTopics= (ArrayList<Topic>) response.body();
                topicAdapter.updateTopicAdapter(mTopics);
            }

            @Override
            public void onFailure(Call<List<Topic>> call, Throwable t) {
                progressBarTopic.setVisibility(View.GONE);
                LoginActivity.prefConfig.displayToast(getResources().getString(R.string.register_error));
            }
        });

    }

    private void actionBar() {
        tbTopic=findViewById(R.id.tbChooseTopic);
        setSupportActionBar(tbTopic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbTopic.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        tbTopic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setupView() {
        recyclerView=findViewById(R.id.recyclerViewTopic);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        topicAdapter=new TopicAdapter(mTopics, new CallbackTopic() {
            @Override
            public void onItemClick(int idtopic, int number) {
                if (category==1){
                    Intent intent=new Intent(getApplicationContext(),ViewPagerQuestionTrainingTTSActivity.class);
                    intent.putExtra("idtopic",idtopic);
                    intent.putExtra("title",number);
                    startActivity(intent);
                }else {
                    Intent intent=new Intent(getApplicationContext(),ViewPagerQuestionTrainingSTTActivity.class);
                    intent.putExtra("idtopic",idtopic);
                    intent.putExtra("title",number);
                    startActivity(intent);
                }

            }
        }, getApplicationContext());
        recyclerView.setAdapter(topicAdapter);
    }
}