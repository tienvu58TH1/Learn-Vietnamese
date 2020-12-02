package com.example.projectda.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectda.activity.LoginActivity;
import com.example.projectda.R;
import com.example.projectda.activity.MainActivity;
import com.example.projectda.models.User;
import com.example.projectda.utils.CheckConnection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassWordFragment extends Fragment {

    private Button btnUpdatePassword;
    private EditText edtPassword,edtNewPassword,edtConfirmPassword;
    private MainActivity context;
    private Toolbar tbChangePassword;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=(MainActivity) context;
    }

    public ChangePassWordFragment(){}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_change_password,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtPassword =view.findViewById(R.id.edtPassword);
        edtNewPassword =view.findViewById(R.id.edtNewPassword);
        edtConfirmPassword=view.findViewById(R.id.edtConfirmPassword);
        btnUpdatePassword=view.findViewById(R.id.btnUpdatePassword);
        btnUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtPassword.getText().toString().equals("") && !edtNewPassword.getText().toString().equals("") && !edtConfirmPassword.getText().toString().equals("")){
                    if (edtNewPassword.getText().toString().length()<=6){
                        LoginActivity.prefConfig.displayToast(getResources().getString(R.string.password_short));
                    }else {
                        updatePassword();
                    }
                }else {
                    LoginActivity.prefConfig.displayToast(getResources().getString(R.string.register_empty));
                }

            }
        });
        context.tbMain.setVisibility(View.GONE);
        actionBar(view);

    }

    private void actionBar(View view) {
        tbChangePassword=view.findViewById(R.id.tbChangePassword);
        context.setSupportActionBar(tbChangePassword);
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbChangePassword.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        tbChangePassword.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.getSupportFragmentManager().popBackStack();
            }
        });
    }

    private void updatePassword() {
        if(edtNewPassword.getText().toString().equals(edtConfirmPassword.getText().toString())){
            if (edtNewPassword.getText().toString().equals(edtPassword.getText().toString())){
                Toast.makeText(getActivity(),getResources().getString(R.string.password_is_the_same),Toast.LENGTH_SHORT).show();
            }
            else {
                if (CheckConnection.haveNetworkConnection(getContext())){
                    updatePasswordUser();
                }else {
                    LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
                }

            }
        }
        else {
            Toast.makeText(getActivity(),getResources().getString(R.string.password_is_not_correct),Toast.LENGTH_SHORT).show();
        }
    }
    private void updatePasswordUser(){
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        final String password= edtPassword.getText().toString();
        String username=LoginActivity.prefConfig.readUserName();
        final String newpassword= edtNewPassword.getText().toString();
        Call<User> call= LoginActivity.apiInterface.updatePassword(username,password,newpassword);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressDialog.dismiss();
                User user=response.body();
                if (!response.isSuccessful()){
                    return;
                }
                if (user==null) return;
                if (user.getResponse().equals("ok")){
                    LoginActivity.prefConfig.displayToast(getResources().getString(R.string.update_password_success));
                    LoginActivity.prefConfig.writePassWord(newpassword);
                    edtPassword.setText("");
                    edtConfirmPassword.setText("");
                    edtNewPassword.setText("");
                    //getActivity().getSupportFragmentManager().popBackStack();
                }
                else if (user.getResponse().equals("error")){
                    LoginActivity.prefConfig.displayToast(getResources().getString(R.string.register_error));
                }
                else if (user.getResponse().equals("failed")){
                    LoginActivity.prefConfig.displayToast(getResources().getString(R.string.wrong_password));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressDialog.dismiss();
                LoginActivity.prefConfig.displayToast(getResources().getString(R.string.register_error));
            }
        });
    }

}
