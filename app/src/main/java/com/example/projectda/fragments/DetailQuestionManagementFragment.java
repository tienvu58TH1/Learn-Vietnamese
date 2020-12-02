package com.example.projectda.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectda.R;
import com.example.projectda.activity.LoginActivity;
import com.example.projectda.activity.QuestionManagementActivity;
import com.example.projectda.models.User;
import com.example.projectda.utils.CheckConnection;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailQuestionManagementFragment extends Fragment {

    private int mPosition;
    private EditText edtId,edtLevel,edtScorepass,edtContent;
    private Spinner spinner;
    private Toolbar toolbar;
    private QuestionManagementActivity mContext;
    private Button btnUpdate;
    private TextView tvTitle;

    public DetailQuestionManagementFragment(int id){
        mPosition =id;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext= (QuestionManagementActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_question,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtId=view.findViewById(R.id.edtId);
        edtLevel=view.findViewById(R.id.edtLevel);
        edtScorepass=view.findViewById(R.id.edtScorepass);
        edtContent=view.findViewById(R.id.edtContent);
        spinner=view.findViewById(R.id.spinnerCategory);
        btnUpdate=view.findViewById(R.id.btnUpdate);
        tvTitle=view.findViewById(R.id.tvTitle);
        ArrayList<String> list=new ArrayList<>();
        list.add("Speaking");
        list.add("Listening");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,list);
        spinner.setAdapter(arrayAdapter);

        if (mPosition !=-1){
            edtId.setText(QuestionManagementActivity.mQuestion.get(mPosition).getIdQuestion()+"");
            edtLevel.setText(QuestionManagementActivity.mQuestion.get(mPosition).getLevel()+"");
            edtScorepass.setText(QuestionManagementActivity.mQuestion.get(mPosition).getScorepass()+"");
            edtContent.setText(QuestionManagementActivity.mQuestion.get(mPosition).getContent());

            if (QuestionManagementActivity.mQuestion.get(mPosition).getCategory()==1){
                spinner.setSelection(1);
            }else {
                spinner.setSelection(0);
            }
        }else {
            tvTitle.setText(getString(R.string.insert_question));
            btnUpdate.setText(getString(R.string.insert));
        }
        actionBar(view);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPosition!=-1){
                    if (TextUtils.isEmpty(edtLevel.getText().toString())
                            || TextUtils.isEmpty(edtScorepass.getText().toString())
                            || TextUtils.isEmpty(edtContent.getText().toString())){
                        Toast.makeText(getActivity(),getString(R.string.register_empty),Toast.LENGTH_SHORT).show();
                    }else {
                        if (CheckConnection.haveNetworkConnection(getContext())){
                            updateQuestion();
                        }else {
                            LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
                        }

                    }

                }else {
                    if (CheckConnection.haveNetworkConnection(getContext())){
                        insertQuestion();
                    }else {
                        LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
                    }

                }

            }
        });
    }

    private void insertQuestion(){
        if (TextUtils.isEmpty(edtLevel.getText().toString())
                || TextUtils.isEmpty(edtScorepass.getText().toString())
                || TextUtils.isEmpty(edtContent.getText().toString())){
            Toast.makeText(getActivity(),getString(R.string.register_empty),Toast.LENGTH_SHORT).show();
        }else {
            final ProgressDialog progressDialog=new ProgressDialog(getContext());
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            Call<User> call=QuestionManagementActivity.apiInterface.insertQuestion(Integer.parseInt(edtLevel.getText().toString()),Integer.parseInt(edtScorepass.getText().toString()),spinner.getSelectedItemPosition(),edtContent.getText().toString());
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    progressDialog.dismiss();
                    if (response.body()==null) return;

                    if (response.body().getResponse().equals("ok")){
                        QuestionManagementActivity.setData();
                        Toast.makeText(getContext(),getString(R.string.insert_success),Toast.LENGTH_SHORT).show();
                        mContext.getSupportFragmentManager().popBackStack();
                    }else {
                        Toast.makeText(getContext(),getString(R.string.insert_error),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(),getString(R.string.register_error),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void updateQuestion(){
        if (Integer.parseInt(edtLevel.getText().toString())==QuestionManagementActivity.mQuestion.get(mPosition).getLevel()
                && Integer.parseInt(edtScorepass.getText().toString())==QuestionManagementActivity.mQuestion.get(mPosition).getScorepass()
                && edtContent.getText().toString().trim().equals(QuestionManagementActivity.mQuestion.get(mPosition).getContent().trim())
                && spinner.getSelectedItemPosition()==QuestionManagementActivity.mQuestion.get(mPosition).getCategory()){

        }else {
            final ProgressDialog progressDialog=new ProgressDialog(getContext());
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            Call<User> call=QuestionManagementActivity.apiInterface.updateQuestion(QuestionManagementActivity.mQuestion.get(mPosition).getIdQuestion(),Integer.parseInt(edtLevel.getText().toString()),Integer.parseInt(edtScorepass.getText().toString()),spinner.getSelectedItemPosition(),edtContent.getText().toString());
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    progressDialog.dismiss();
                    if (response.body()==null) return;

                    if (response.body().getResponse().equals("ok")){
                        QuestionManagementActivity.setData();
                        Toast.makeText(getContext(),getString(R.string.update_success),Toast.LENGTH_SHORT).show();
                        mContext.getSupportFragmentManager().popBackStack();
                    }else {
                        Toast.makeText(getContext(),getString(R.string.update_error),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(),getString(R.string.register_error),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void actionBar(View view) {
        toolbar=view.findViewById(R.id.tbDetailQuestion);
        mContext.setSupportActionBar(toolbar);
        mContext.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.getSupportFragmentManager().popBackStack();
            }
        });
    }

}
