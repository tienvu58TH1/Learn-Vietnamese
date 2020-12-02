package com.example.projectda.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectda.R;
import com.example.projectda.activity.MainActivity;
import com.example.projectda.activity.MenuTestActivity;
import com.example.projectda.activity.TopActivity;
import com.example.projectda.activity.TrainingActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class MenuFragment extends Fragment {

    private CardView cvTopPlayer,cvTest,cvTraining,cvMp3,cvSearch,cvInfo;
    private MainActivity context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=(MainActivity) context;
    }

    public MenuFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_main,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cvTopPlayer=view.findViewById(R.id.cvTopPlayer);
        cvTraining=view.findViewById(R.id.cvTraining);
        cvTest=view.findViewById(R.id.cvTest);
        cvMp3=view.findViewById(R.id.cvMp3);
        cvSearch=view.findViewById(R.id.cvSearch);
        cvInfo=view.findViewById(R.id.cvInfo);
        cvTopPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTopPlayer();
            }
        });
        cvTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTest();
            }
        });
        cvTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTraining();
            }
        });
        cvMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMp3();
            }
        });
        cvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearch();
            }
        });
        cvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openInfo();
            }
        });
        context.tbMain.setVisibility(View.VISIBLE);
    }


    private void openTest() {
        Intent intent=new Intent(getContext(), MenuTestActivity.class);
        startActivity(intent);
    }
    private void openTraining() {
        Intent intent=new Intent(getContext(), TrainingActivity.class);
        startActivity(intent);
    }
    private void openInfo() {
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_main,new InfoFragment()).commit();
    }

    private void openTopPlayer() {
        Intent intent=new Intent(getContext(), TopActivity.class);
        startActivity(intent);
    }
    private void openMp3() {
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_main,new Mp3Fragment()).commit();
    }
    private void openSearch() {
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_main,new SearchFragment()).commit();
    }
}
