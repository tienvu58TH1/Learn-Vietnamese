package com.example.projectda.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectda.R;
import com.example.projectda.activity.TextToSpeechActivity;
import com.example.projectda.adapter.TextToSpeechAdapter;
import com.example.projectda.callback.CallbackQuestion;
import com.example.projectda.utils.ItemOffsetDecoration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TextToSpeechFragment extends Fragment {
    private int column=3;
    private TextToSpeechAdapter textToSpeechAdapter;
    private Toolbar tbTTS;
    private TextToSpeechActivity context;

    public TextToSpeechFragment(){}

    @Override
    public void onAttach(@NonNull Context context) {
        this.context= (TextToSpeechActivity) context;
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_texttospeech,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        actionBar(view);
        setupRecyclerViewQuestion(view);
    }
    private void setupRecyclerViewQuestion(final View view) {
        RecyclerView recyclerView=view.findViewById(R.id.recyclerViewQuestion);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),column);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new ItemOffsetDecoration(15));
        textToSpeechAdapter=new TextToSpeechAdapter(TextToSpeechActivity.questions, new CallbackQuestion() {
            @Override
            public void onItemClick(int position) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_tts,new ViewPagerQuestionTextToSpeech(position)).commit();
            }
        },getContext(),column,TextToSpeechActivity.levelwrite);
        recyclerView.setAdapter(textToSpeechAdapter);
    }

    private void actionBar(View view) {
        tbTTS=view.findViewById(R.id.tbTTS);
        context.setSupportActionBar(tbTTS);
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbTTS.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        tbTTS.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.finish();
            }
        });
    }

}
