package com.example.projectda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectda.R;
import com.example.projectda.activity.LoginActivity;
import com.example.projectda.activity.ViewPagerQuestionTrainingTTSActivity;
import com.example.projectda.callback.CallbackMediaPlayer;
import com.example.projectda.models.QuestionTopic;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerQuestionTrainingTTSAdapter extends PagerAdapter {
    private ArrayList<QuestionTopic> questionTopics;
    private LayoutInflater inflater;
    private Context context;
    private CallbackMediaPlayer callbackMediaPlayer;

    public ViewPagerQuestionTrainingTTSAdapter(Context context, ArrayList<QuestionTopic> idImages, CallbackMediaPlayer callbackMediaPlayer){
        this.questionTopics =idImages;
        inflater= LayoutInflater.from(context);
        this.context=context;
        this.callbackMediaPlayer=callbackMediaPlayer;
    }

    @Override
    public int getCount() {
        return questionTopics.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {

        View view=inflater.inflate(R.layout.fragment_question_training_tts,container,false);
        TextView tvLevel =view.findViewById(R.id.tvLevelTTS);
        final ProgressBar progressBar=view.findViewById(R.id.progressBarTTS);
        ImageView btnListen=view.findViewById(R.id.btnListen);
        TextView tvProgress=view.findViewById(R.id.tvProgress);
        ImageView imgHelp=view.findViewById(R.id.imgHelp);
        ImageView imgTranslate=view.findViewById(R.id.imgTranslate);
        progressBar.setProgress(0);

        tvLevel.setText("Level "+ ViewPagerQuestionTrainingTTSActivity.mQuestionTopicViewPagerTTS.get(position).getLevel());
        tvProgress.setText((position+1)+"/"+ViewPagerQuestionTrainingTTSActivity.mQuestionTopicViewPagerTTS.size());
        btnListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //playMp3(file,progressBar);
                callbackMediaPlayer.onClickPlaying(position,progressBar);
            }
        });
        imgHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,ViewPagerQuestionTrainingTTSActivity.mQuestionTopicViewPagerTTS.get(position).getContent(),Toast.LENGTH_LONG).show();
            }
        });
        imgTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.prefConfig.displayToast(ViewPagerQuestionTrainingTTSActivity.mQuestionTopicViewPagerTTS.get(position).getTranslate());
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
