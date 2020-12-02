package com.example.projectda.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectda.R;
import com.example.projectda.activity.AdminManagerAcitivity;
import com.example.projectda.activity.MainActivity;
import com.example.projectda.activity.QuestionManagementActivity;
import com.example.projectda.activity.TrainingActivity;
import com.example.projectda.activity.TrainingManagementActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class ManagerFragment extends Fragment {

    private CardView cvMain,cvQuestionManagement,cvTrainingManagement;
    private AdminManagerAcitivity context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=(AdminManagerAcitivity) context;
    }

    public ManagerFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manager,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cvQuestionManagement=view.findViewById(R.id.cvQuestionManagement);
        cvTrainingManagement=view.findViewById(R.id.cvTrainingManagement);
        cvMain=view.findViewById(R.id.cvMain);
        cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain();
            }
        });
        cvQuestionManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             openQuestion();
            }
        });
        cvTrainingManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTraining();
            }
        });
        context.tbManager.setVisibility(View.VISIBLE);
    }

    private void openQuestion() {
        Intent intent=new Intent(getContext(), QuestionManagementActivity.class);
        startActivity(intent);
    }
    private void openTraining() {
        Intent intent=new Intent(getContext(), TrainingManagementActivity.class);
        startActivity(intent);
    }
    private void openMain() {
        Intent intent=new Intent(getContext(), MainActivity.class);
        intent.putExtra("keymain",1);
        startActivity(intent);
    }
}
