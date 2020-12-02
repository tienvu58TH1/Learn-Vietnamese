package com.example.projectda.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectda.R;
import com.example.projectda.activity.SpeechToTextActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class QuestionSTTFragment extends Fragment {
    private int position;

    public QuestionSTTFragment(int i){
        position=i;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question_stt,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvQuestion=view.findViewById(R.id.tvQuestion);
        tvQuestion.setText(SpeechToTextActivity.questionsSTT.get(position).getContent());
        TextView tvLevel=view.findViewById(R.id.tvLevel);
        tvLevel.setText("Level "+SpeechToTextActivity.questionsSTT.get(position).getLevel());
        TextView tvScorePass=view.findViewById(R.id.tvScorePass);
        tvScorePass.setText("Scorepass: "+SpeechToTextActivity.questionsSTT.get(position).getScorepass());
    }
}
