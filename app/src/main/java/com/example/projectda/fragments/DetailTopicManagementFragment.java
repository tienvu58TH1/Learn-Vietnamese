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
import com.example.projectda.activity.TopicManagementActivity;
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

public class DetailTopicManagementFragment extends Fragment {

    private int mPosition;
    private EditText edtIdTopic,edtNumberTopic,edtTitle;
    private Spinner spinner;
    private Toolbar toolbar;
    private TopicManagementActivity mContext;
    private Button btnUpdate;
    private TextView tvTitle;

    public DetailTopicManagementFragment(int id){
        mPosition =id;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext= (TopicManagementActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_topic,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtIdTopic=view.findViewById(R.id.edtIdTopic);
        edtNumberTopic=view.findViewById(R.id.edtNumberTopic);
        edtTitle=view.findViewById(R.id.edtTitleTopic);
        spinner=view.findViewById(R.id.spinnerCategory);
        btnUpdate=view.findViewById(R.id.btnUpdate);
        tvTitle=view.findViewById(R.id.tvTitle);
        ArrayList<String> list=new ArrayList<>();
        list.add("Speaking");
        list.add("Listening");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,list);
        spinner.setAdapter(arrayAdapter);

        if (mPosition !=-1){
            edtIdTopic.setText(TopicManagementActivity.mTopics.get(mPosition).getIdTopic()+"");
            edtNumberTopic.setText(TopicManagementActivity.mTopics.get(mPosition).getNumbertopic()+"");
            edtTitle.setText(TopicManagementActivity.mTopics.get(mPosition).getTitleTopic());

            if (TopicManagementActivity.mTopics.get(mPosition).getCategory()==1){
                spinner.setSelection(1);
            }else {
                spinner.setSelection(0);
            }
        }else {
            tvTitle.setText(getString(R.string.insert_topic));
            btnUpdate.setText(getString(R.string.insert));
        }
        actionBar(view);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPosition!=-1){
                    if (TextUtils.isEmpty(edtNumberTopic.getText().toString())
                            || TextUtils.isEmpty(edtTitle.getText().toString())){
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
        if (TextUtils.isEmpty(edtNumberTopic.getText().toString())
                || TextUtils.isEmpty(edtTitle.getText().toString())){
            Toast.makeText(getActivity(),getString(R.string.register_empty),Toast.LENGTH_SHORT).show();
        }else {
            final ProgressDialog progressDialog=new ProgressDialog(getContext());
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            Call<User> call=TopicManagementActivity.apiInterface.insertTopic(Integer.parseInt(edtNumberTopic.getText().toString()),edtTitle.getText().toString(),spinner.getSelectedItemPosition());
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    progressDialog.dismiss();
                    if (response.body()==null) return;

                    if (response.body().getResponse().equals("ok")){
                        TopicManagementActivity.setData();
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
        if (Integer.parseInt(edtNumberTopic.getText().toString())==TopicManagementActivity.mTopics.get(mPosition).getNumbertopic()
                && edtTitle.getText().toString().trim().equals(TopicManagementActivity.mTopics.get(mPosition).getTitleTopic().trim())
                && spinner.getSelectedItemPosition()==TopicManagementActivity.mTopics.get(mPosition).getCategory()){

        }else {
            final ProgressDialog progressDialog=new ProgressDialog(getContext());
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            Call<User> call=TopicManagementActivity.apiInterface.updateTopic(TopicManagementActivity.mTopics.get(mPosition).getIdTopic(),Integer.parseInt(edtNumberTopic.getText().toString()),edtTitle.getText().toString(),spinner.getSelectedItemPosition());
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    progressDialog.dismiss();
                    if (response.body()==null) return;

                    if (response.body().getResponse().equals("ok")){
                        TopicManagementActivity.setData();
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
        toolbar=view.findViewById(R.id.tbDetailTopic);
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
