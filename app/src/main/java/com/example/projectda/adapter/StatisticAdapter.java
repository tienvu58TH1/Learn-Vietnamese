package com.example.projectda.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectda.R;
import com.example.projectda.callback.CallbackQuestionManager;
import com.example.projectda.models.History;
import com.example.projectda.models.Statistic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StatisticAdapter extends RecyclerView.Adapter<StatisticAdapter.ViewHolder> {
    private ArrayList<Statistic> mStatistics;

    public StatisticAdapter(ArrayList<Statistic> statistics){
        mStatistics=statistics;
    }

    public void updateAdapter(ArrayList<Statistic> statistics){
        mStatistics=statistics;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_statistic_custom,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        View view= holder.getView();
        final Statistic statistic=mStatistics.get(position);
        TextView tvLevel=view.findViewById(R.id.tvLevel);
        TextView tvCategory=view.findViewById(R.id.tvCategory);
        TextView tvCount=view.findViewById(R.id.tvCount);
        TextView tvMediumScore=view.findViewById(R.id.tvMediumScore);

        tvLevel.setText(statistic.getLevel()+"");
        if (statistic.getCategory()==0){
            tvCategory.setText("Speaking");
        }else {
            tvCategory.setText("Listening");
        }
        tvCount.setText(statistic.getCount()+"");
        tvMediumScore.setText(String.format("%.1f",statistic.getMediumscore()));
    }

    @Override
    public int getItemCount() {
        return mStatistics.size();
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
