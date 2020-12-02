package com.example.projectda.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;
import com.example.projectda.R;
import com.example.projectda.activity.LoginActivity;
import com.example.projectda.callback.CallbackMp3;
import com.example.projectda.models.User;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class TopSTTAdapter extends RecyclerView.Adapter<TopSTTAdapter.ViewHolder> {
    private ArrayList<User> users;
    private CallbackMp3 callback;

    public TopSTTAdapter(ArrayList<User> users, CallbackMp3 callback){
        this.users=users;
        this.callback=callback;
    }
    public TopSTTAdapter(ArrayList<User> users){
        this.users=users;
    }

    public void updateTopSTTAdapter(ArrayList<User> users){
        this.users=users;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.top_custom,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        View view= holder.getView();
        final User user= users.get(position);
        ImageView imgTopPlayer=view.findViewById(R.id.imgTopPlayer);
        TextView tvTopPlayer=view.findViewById(R.id.tvTopPlayer);
        TextView tvLevel=view.findViewById(R.id.tvLevel);
        TextView tvTop=view.findViewById(R.id.tvTop);
        tvTop.setText(position+1+"");
        tvLevel.setText(user.getLevelSpeech()+"");
        tvTopPlayer.setText(user.getName());
        String urlImage=user.getPath();
        Glide.with(view).load(urlImage).signature(new ObjectKey(String.valueOf(System.currentTimeMillis()))).into(imgTopPlayer);
    }

    @Override
    public int getItemCount() {
        return users.size();
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
