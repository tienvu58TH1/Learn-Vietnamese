package com.example.projectda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectda.R;
import com.example.projectda.callback.CallbackAlphabet;
import com.example.projectda.callback.CallbackQuestionManager;
import com.example.projectda.models.Question;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionManagementAdapter extends RecyclerView.Adapter<QuestionManagementAdapter.ViewHolder> {
    private ArrayList<Question> mQuestion;
    private CallbackQuestionManager callback;

    public QuestionManagementAdapter(ArrayList<Question> questions, CallbackQuestionManager callback){
        this.mQuestion=questions;
        this.callback=callback;
    }

    public void updateAdapter(ArrayList<Question> questions){
        mQuestion=questions;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_question_management_custom,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        View view= holder.getView();
        final Question question=mQuestion.get(position);
        TextView tvId=view.findViewById(R.id.tvIdQuestion);
        TextView tvLevel=view.findViewById(R.id.tvLevel);
        TextView tvScorepass=view.findViewById(R.id.tvScorePass);
        TextView tvCategory=view.findViewById(R.id.tvCategory);
        TextView tvContent=view.findViewById(R.id.tvContent);
        tvId.setText(question.getIdQuestion()+"");
        tvLevel.setText(question.getLevel()+"");
        tvScorepass.setText(question.getScorepass()+"");
        tvCategory.setText(question.getCategory()+"");
        tvContent.setText(question.getContent());
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
        return mQuestion.size();
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
