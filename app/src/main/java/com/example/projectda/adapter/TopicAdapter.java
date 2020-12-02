package com.example.projectda.adapter;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.projectda.R;
import com.example.projectda.callback.CallbackAlphabet;
import com.example.projectda.callback.CallbackTopic;
import com.example.projectda.models.Alphabet;
import com.example.projectda.models.Topic;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {
    private ArrayList<Topic> mTopics;
    private CallbackTopic callback;
    private Context context;

    public TopicAdapter(ArrayList<Topic> topics, CallbackTopic callback, Context context){
        this.mTopics=topics;
        this.callback=callback;
        this.context=context;
    }

    public void updateTopicAdapter(ArrayList<Topic> topics){
        mTopics=topics;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_topic_custom,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        View view= holder.getView();
        final Topic topic=mTopics.get(position);
        TextView tvNameTopic=view.findViewById(R.id.tvNameTopic);
        TextView tvTitleTopic=view.findViewById(R.id.tvTitleTopic);
        tvNameTopic.setText("Topic "+topic.getNumbertopic());
        tvTitleTopic.setText(topic.getTitleTopic());
        CardView cardView=view.findViewById(R.id.cardViewTopic);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onItemClick(topic.getIdTopic(),topic.getNumbertopic());
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
