package com.example.projectda.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectda.R;
import com.example.projectda.callback.CallbackQuestionManager;
import com.example.projectda.models.Question;
import com.example.projectda.models.QuestionTopic;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionTopicManagementAdapter extends RecyclerView.Adapter<QuestionTopicManagementAdapter.ViewHolder> {
    private ArrayList<QuestionTopic> mQuestionTopics;
    private CallbackQuestionManager callback;

    public QuestionTopicManagementAdapter(ArrayList<QuestionTopic> questions, CallbackQuestionManager callback){
        this.mQuestionTopics=questions;
        this.callback=callback;
    }

    public void updateAdapter(ArrayList<QuestionTopic> questions){
        mQuestionTopics=questions;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_questiontopic_management_custom,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        View view= holder.getView();
        final QuestionTopic question=mQuestionTopics.get(position);
        TextView tvId=view.findViewById(R.id.tvIdQuestion);
        TextView tvLevel=view.findViewById(R.id.tvLevel);
        TextView tvIdTopic=view.findViewById(R.id.tvIdTopic);
        TextView tvContent=view.findViewById(R.id.tvContent);
        TextView tvTranslate=view.findViewById(R.id.tvTranslate);
        tvId.setText(question.getIdQuestion()+"");
        tvLevel.setText(question.getLevel()+"");
        tvIdTopic.setText(question.getIdtopic()+"");
        tvContent.setText(question.getContent());
        tvTranslate.setText(question.getTranslate());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onItemClick(position);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                callback.onLongClick(question.getIdQuestion());
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mQuestionTopics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
        }

        public View getView() {
            return view;
        }
    }
}
