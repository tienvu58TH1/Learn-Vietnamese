package com.example.projectda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.projectda.R;

public class TrainingManagementActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private LinearLayout btnTopic,btnQuestionTopic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_management);
        actionBar();
        btnTopic=findViewById(R.id.btnTopic);
        btnQuestionTopic=findViewById(R.id.btnQuestionTopic);
        btnTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),TopicManagementActivity.class);
                startActivity(intent);
            }
        });
        btnQuestionTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),QuestionTopicManagementActivity.class);
                startActivity(intent);
            }
        });

    }

    private void actionBar() {
        toolbar=findViewById(R.id.tbTrainingManagement);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}