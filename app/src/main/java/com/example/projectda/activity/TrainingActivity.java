package com.example.projectda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.projectda.R;

public class TrainingActivity extends AppCompatActivity {
    private Toolbar tbTraining;
    private LinearLayout trainingAlphabet, trainingTTS,trainingSTT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        actionBar();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        trainingAlphabet=findViewById(R.id.trainingAlphabet);
        trainingTTS =findViewById(R.id.trainingTTS);
        trainingSTT =findViewById(R.id.trainingSTT);
        trainingTTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),TrainingTopicActivity.class);
                intent.putExtra("category",1);
                startActivity(intent);
            }
        });
        trainingSTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),TrainingTopicActivity.class);
                intent.putExtra("category",0);
                startActivity(intent);
            }
        });
        trainingAlphabet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AlphabetActivity.class);
                startActivity(intent);
            }
        });
    }

    private void actionBar() {
        tbTraining=findViewById(R.id.tbTraining);
        setSupportActionBar(tbTraining);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbTraining.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        tbTraining.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}