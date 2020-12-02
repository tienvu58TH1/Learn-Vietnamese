package com.example.projectda.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectda.R;
import com.example.projectda.callback.CallbackQuestionManager;
import com.example.projectda.models.History;
import com.example.projectda.models.QuestionTopic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private ArrayList<History> mHistories;
    private CallbackQuestionManager callback;

    public HistoryAdapter(ArrayList<History> histories){
        mHistories=histories;
    }

    public void updateAdapter(ArrayList<History> histories){
        mHistories=histories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_history_custom,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        View view= holder.getView();
        final History history=mHistories.get(position);
        TextView tvLevel=view.findViewById(R.id.tvLevel);
        TextView tvScore=view.findViewById(R.id.tvScore);
        TextView tvTime=view.findViewById(R.id.tvTime);
        tvLevel.setText("Level "+history.getLevel());
        tvScore.setText(history.getScore()+"");
        tvTime.setText(convertTime(history.getTime()));
    }

    private String convertTime(long time){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm dd/MM/yyyy");
        return simpleDateFormat.format(time);
    }

    @Override
    public int getItemCount() {
        return mHistories.size();
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
