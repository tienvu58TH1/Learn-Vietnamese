package com.example.projectda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.projectda.R;
import com.example.projectda.adapter.ViewPagerTopPlayAdapter;
import com.google.android.material.tabs.TabLayout;

public class TopActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerTopPlayAdapter viewPagerTopPlayAdapter;
    private static Toolbar tbTopPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        actionBar();
        setupView();
    }

    private void setupView(){
        tabLayout=findViewById(R.id.tabLayoutTop);
        viewPager=findViewById(R.id.viewPagerTop);
        tabLayout.setupWithViewPager(viewPager);
        viewPagerTopPlayAdapter = new ViewPagerTopPlayAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerTopPlayAdapter);
    }

    private void actionBar() {
        tbTopPlayer=findViewById(R.id.tbTopPlayer);
        setSupportActionBar(tbTopPlayer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbTopPlayer.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        tbTopPlayer.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}