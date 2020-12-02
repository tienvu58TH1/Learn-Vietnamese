package com.example.projectda.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projectda.R;
import com.example.projectda.fragments.Mp3Fragment;
import com.example.projectda.models.Track;
import com.example.projectda.callback.CallbackMp3;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolder> {
    private ArrayList<Track> tracks;
    private CallbackMp3 callback;

    public TrackAdapter(ArrayList<Track> tracks, CallbackMp3 callback){
        this.tracks=tracks;
        this.callback=callback;
    }

    public void updateTrackAdapter(ArrayList<Track> tracks){
        this.tracks=tracks;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.track_custom,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        View view= holder.getView();
        final Track track= tracks.get(position);
        ImageView ivTrack=view.findViewById(R.id.ivTrack);
        TextView tvTitle=view.findViewById(R.id.tvTitle);
        TextView tvAuthor=view.findViewById(R.id.tvAuthor);
        TextView tvCategory=view.findViewById(R.id.tvCategory);
        tvTitle.setText(track.getTitle());
        tvCategory.setText(track.getNamecategory());
        tvAuthor.setText(track.getAuthor());
        Glide.with(view).load(track.getImage()).into(ivTrack);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onItemClick(track.getLink(),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tracks.size();
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
