package com.example.projectda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.projectda.R;

public class MenuTestActivity extends AppCompatActivity {

    private LinearLayout testSTT,testTTS,statistics;
    private Toolbar tbMenuTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_test);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        testSTT=findViewById(R.id.testSTT);
        testTTS=findViewById(R.id.testTTS);
        statistics=findViewById(R.id.statistics);
        testTTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),TextToSpeechActivity.class);
                startActivity(intent);
            }
        });
        testSTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SpeechToTextActivity.class);
                startActivity(intent);
            }
        });
        statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), StatisticActivity.class);
                startActivity(intent);
            }
        });
        actionBar();
    }

    private void actionBar() {
        tbMenuTest=findViewById(R.id.tbMenuTest);
        setSupportActionBar(tbMenuTest);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbMenuTest.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        tbMenuTest.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}