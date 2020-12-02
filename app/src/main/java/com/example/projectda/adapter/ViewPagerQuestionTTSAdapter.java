package com.example.projectda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.projectda.R;
import com.example.projectda.activity.TextToSpeechActivity;
import com.example.projectda.callback.CallbackMediaPlayer;
import com.example.projectda.models.Question;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerQuestionTTSAdapter extends PagerAdapter {
    private ArrayList<Question> mQuestions;
    private LayoutInflater inflater;
    private Context context;
    private CallbackMediaPlayer callbackMediaPlayer;

    public ViewPagerQuestionTTSAdapter(Context context, ArrayList<Question> questions, CallbackMediaPlayer callbackMediaPlayer){
        this.mQuestions=questions;
        inflater= LayoutInflater.from(context);
        this.context=context;
        this.callbackMediaPlayer=callbackMediaPlayer;
    }

    @Override
    public int getCount() {
        return mQuestions.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {

        View view=inflater.inflate(R.layout.fragment_question_tts,container,false);
        TextView tvLevel =view.findViewById(R.id.tvLevelTTS);
        final ProgressBar progressBar=view.findViewById(R.id.progressBarTTS);
        ImageView btnListen=view.findViewById(R.id.btnListen);
        TextView tvScorePass=view.findViewById(R.id.tvScorePassTTS);

        progressBar.setProgress(0);

        tvLevel.setText("Level "+ TextToSpeechActivity.questions.get(position).getLevel());
        tvScorePass.setText("Scorepass: "+TextToSpeechActivity.questions.get(position).getScorepass());
        btnListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callbackMediaPlayer.onClickPlaying(position,progressBar);
            }
        });

        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
