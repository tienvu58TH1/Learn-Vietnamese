package com.example.projectda.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.projectda.R;
import com.example.projectda.activity.LoginActivity;
import com.example.projectda.activity.MainActivity;
import com.example.projectda.activity.TextToSpeechActivity;
import com.example.projectda.activity.TopActivity;
import com.example.projectda.adapter.TextToSpeechAdapter;
import com.example.projectda.adapter.TopSTTAdapter;
import com.example.projectda.adapter.ViewPagerTopPlayAdapter;
import com.example.projectda.models.Track;
import com.example.projectda.models.User;
import com.example.projectda.utils.CheckConnection;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopSTTFragment extends Fragment {
    private TopSTTAdapter topSTTAdapter;
    private ArrayList<User> users;
    private ProgressBar progressBarTopSTT;
    public TopSTTFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_stt,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        users=new ArrayList<>();
        progressBarTopSTT=view.findViewById(R.id.progressBarTopSTT);
        setupView(view);
        if (CheckConnection.haveNetworkConnection(getContext())){
            getData();
        }else {
            LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
        }

    }

    private void getData() {

        Call<List<User>> call= LoginActivity.apiInterface.getTopLevelSpeech();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.body()==null){
                    return;
                }
                users= (ArrayList<User>) response.body();
                progressBarTopSTT.setVisibility(View.GONE);
                topSTTAdapter.updateTopSTTAdapter(users);
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                LoginActivity.prefConfig.displayToast(t.getMessage());
            }
        });
    }

    private void setupView(final View view) {
        RecyclerView recyclerView=view.findViewById(R.id.recyclerViewTopSTT);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        topSTTAdapter=new TopSTTAdapter(users);
        recyclerView.setAdapter(topSTTAdapter);
    }


}
