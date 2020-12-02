package com.example.projectda.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectda.R;
import com.example.projectda.callback.CallbackQuestionManager;
import com.example.projectda.models.Question;
import com.example.projectda.models.Topic;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TopicManagementAdapter extends RecyclerView.Adapter<TopicManagementAdapter.ViewHolder> {
    private ArrayList<Topic> mTopics;
    private CallbackQuestionManager callback;

    public TopicManagementAdapter(ArrayList<Topic> topics, CallbackQuestionManager callback){
        this.mTopics =topics;
        this.callback=callback;
    }

    public void updateAdapter(ArrayList<Topic> topics){
        mTopics =topics;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_topic_management_custom,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        View view= holder.getView();
        final Topic topic= mTopics.get(position);
        TextView tvId=view.findViewById(R.id.tvIdTopic);
        TextView tvNumber=view.findViewById(R.id.tvNumberTopic);
        TextView tvCategory=view.findViewById(R.id.tvCategory);
        TextView tvTitle=view.findViewById(R.id.tvTitleTopic);
        tvId.setText(topic.getIdTopic()+"");
        tvNumber.setText("Topic "+topic.getNumbertopic());
        tvCategory.setText(topic.getCategory()+"");
        tvTitle.setText(topic.getTitleTopic());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onItemClick(position);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                callback.onLongClick(topic.getIdTopic());
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mTopics.size();
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
