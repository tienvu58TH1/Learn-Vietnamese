package com.example.projectda.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projectda.activity.MainActivity;
import com.example.projectda.R;
import com.example.projectda.adapter.TrackAdapter;
import com.example.projectda.callback.CallBackPlayComplete;
import com.example.projectda.callback.CallbackMp3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.projectda.activity.MainActivity.tracks;

public class Mp3Fragment extends Fragment {
    private TrackAdapter trackAdapter;
    private ImageView ibPre,ibNext,ibPlay,ivTrack;
    private LinearLayout linearLayoutTrack;

    private TextView tvTitle;
    private Toolbar tbMp3;
    private MainActivity context;
    public Mp3Fragment(){};

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context= (MainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mp3,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivTrack=view.findViewById(R.id.ivTrack);
        ibPlay=view.findViewById(R.id.ibPlay);
        ibNext=view.findViewById(R.id.ibNext);
        ibPre=view.findViewById(R.id.ibPre);
        tvTitle=view.findViewById(R.id.tvTitle);
        linearLayoutTrack=view.findViewById(R.id.linearLayoutTrack);
        setupView();
        checkShowTrack();
        setOnClickButton();
        setupRecyclerViewMp3(view);
        context.tbMain.setVisibility(View.GONE);
        actionBar(view);

    }

    private void actionBar(View view) {
        tbMp3=view.findViewById(R.id.tbMp3);
        context.setSupportActionBar(tbMp3);
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbMp3.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        tbMp3.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.getSupportFragmentManager().popBackStack();
            }
        });
    }
    private void setOnClickButton() {
        ibPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.onStartMp3();
                setupView();
            }
        });
        ibPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.positionTrack--;
                MainActivity.onPreAndNextMp3(new CallBackPlayComplete() {
                    @Override
                    public void onCompleteMp3() {
                        setupView();
                    }
                });
                setupView();
            }
        });
        ibNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.positionTrack++;
                MainActivity.onPreAndNextMp3(new CallBackPlayComplete() {
                    @Override
                    public void onCompleteMp3() {
                        setupView();
                    }
                });
                setupView();
            }
        });
    }

    private void checkShowTrack(){
        if (MainActivity.checkShowTrack){
            linearLayoutTrack.setVisibility(View.VISIBLE);
        }else {
            linearLayoutTrack.setVisibility(View.GONE);
        }
    }
    private void setupView() {
        if (MainActivity.checkShowTrack){
            Glide.with(context).load(tracks.get(MainActivity.positionTrack).getImage()).into(ivTrack);
            tvTitle.setText(tracks.get(MainActivity.positionTrack).getTitle());
            if (MainActivity.isPlaying){
                ibPlay.setImageResource(R.drawable.ic_baseline_pause_24);
            }else {
                ibPlay.setImageResource(R.drawable.ic_play);
            }
            if (MainActivity.positionTrack==tracks.size()-1){
                ibNext.setVisibility(View.INVISIBLE);
                ibPre.setVisibility(View.VISIBLE);
            }
            else if (MainActivity.positionTrack==0){
                ibPre.setVisibility(View.INVISIBLE);
                ibNext.setVisibility(View.VISIBLE);
            }
            else{
                ibNext.setVisibility(View.VISIBLE);
                ibPre.setVisibility(View.VISIBLE);
            }
        }
    }

    private void setupRecyclerViewMp3(final View view) {
        RecyclerView recyclerViewTrack=view.findViewById(R.id.recyclerViewMp3);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerViewTrack.setLayoutManager(layoutManager);
        trackAdapter=new TrackAdapter(tracks, new CallbackMp3() {
            @Override
            public void onItemClick(String link,int position) {
                MainActivity.positionTrack=position;
                MainActivity.onPreAndNextMp3(new CallBackPlayComplete() {
                    @Override
                    public void onCompleteMp3() {
                        setupView();
                    }
                });
                MainActivity.checkShowTrack=true;
                setupView();
                checkShowTrack();
            }
        });
        recyclerViewTrack.setAdapter(trackAdapter);
    }

}
