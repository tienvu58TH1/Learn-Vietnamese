package com.example.projectda.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.projectda.R;
import com.example.projectda.activity.SpeechToTextActivity;
import com.example.projectda.adapter.SpeechToTextAdapter;
import com.example.projectda.callback.CallbackQuestion;
import com.example.projectda.utils.ItemOffsetDecoration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SpeechToTextFragment extends Fragment {
    private int column=3;
    private SpeechToTextAdapter speechToTextAdapter;
    private Toolbar tbSTT;
    private SpeechToTextActivity context;

    public SpeechToTextFragment(){}

    @Override
    public void onAttach(@NonNull Context context) {
        this.context= (SpeechToTextActivity) context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_speechtotext,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        actionBar(view);
        setupRecyclerViewQuestion(view);

    }
    private void setupRecyclerViewQuestion(final View view) {
        RecyclerView recyclerView=view.findViewById(R.id.recyclerViewQuestionSTT);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),column);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new ItemOffsetDecoration(15));
        speechToTextAdapter=new SpeechToTextAdapter(SpeechToTextActivity.questionsSTT, new CallbackQuestion() {
            @Override
            public void onItemClick(int position) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_STT,new ViewPagerQuestionSpeechToText(position)).commit();
            }
        },getContext(),column,SpeechToTextActivity.levelspeech);
        recyclerView.setAdapter(speechToTextAdapter);
    }

    private void actionBar(View view) {
        tbSTT=view.findViewById(R.id.tbSTT);
        context.setSupportActionBar(tbSTT);
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbSTT.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        tbSTT.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.finish();
            }
        });
    }

}
