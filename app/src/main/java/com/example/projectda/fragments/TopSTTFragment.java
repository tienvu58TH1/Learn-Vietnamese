package com.example.projectda.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;

import com.example.projectda.R;
import com.example.projectda.activity.LoginActivity;
import com.example.projectda.adapter.TopSTTAdapter;
import com.example.projectda.callback.CallbackReleaseLongClick;
import com.example.projectda.models.User;
import com.example.projectda.utils.CheckConnection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopSTTFragment extends Fragment {
    private TopSTTAdapter topSTTAdapter;
    private ArrayList<User> mTopUsers;
    private ProgressBar progressBarTopSTT;
    private RecyclerView recyclerView;
    private LayoutAnimationController layoutAnimationController;
    public TopSTTFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_stt,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTopUsers =new ArrayList<>();
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
                mTopUsers = (ArrayList<User>) response.body();
                progressBarTopSTT.setVisibility(View.GONE);

                recyclerView.setLayoutAnimation(layoutAnimationController);
                topSTTAdapter.updateTopSTTAdapter(mTopUsers);
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                LoginActivity.prefConfig.displayToast(t.getMessage());
            }
        });
    }

    private void setupView(final View view) {
        recyclerView=view.findViewById(R.id.recyclerViewTopSTT);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutAnimationController= AnimationUtils.loadLayoutAnimation(getActivity(),R.anim.layout_animation_down_to_up);
        recyclerView.setLayoutManager(layoutManager);
        topSTTAdapter=new TopSTTAdapter(mTopUsers);
        recyclerView.setAdapter(topSTTAdapter);


    }
    private void dragAndDrop(){
        ItemTouchHelper helper=new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN,0) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int positionDragged=viewHolder.getAdapterPosition();
                int positionTarget=target.getAdapterPosition();

                Collections.swap(mTopUsers,positionDragged,positionTarget);
                topSTTAdapter.notifyItemMoved(positionDragged,positionTarget);
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Log.d("BBB","stop");
            }
        });

        helper.attachToRecyclerView(recyclerView);
    }


}
