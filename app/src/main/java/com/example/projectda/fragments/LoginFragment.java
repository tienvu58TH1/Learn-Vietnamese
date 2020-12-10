package com.example.projectda.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projectda.activity.AdminManagerAcitivity;
import com.example.projectda.activity.LoginActivity;
import com.example.projectda.activity.MainActivity;
import com.example.projectda.R;
import com.example.projectda.models.User;
import com.example.projectda.utils.CheckConnection;
import com.example.projectda.utils.LocaleManager;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {
    private TextView tvRegister;
    private EditText edtUsername,edtPassword;
    private Button btnLogin,btnChangeLanguage;
    private LoginActivity context;

    public LoginFragment(){

    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context= (LoginActivity) context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        LocaleManager.loadLocale(getActivity());
        if (!LoginActivity.isCreate){
            LoginActivity.isCreate=true;
            context.recreate();
        }
        super.onViewCreated(view, savedInstanceState);
        tvRegister=view.findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new RegisterFragment()).addToBackStack(null).commit();
            }
        });
        edtUsername=view.findViewById(R.id.tvUsername);
        edtPassword=view.findViewById(R.id.edtPassword);
        edtUsername.setText(LoginActivity.prefConfig.readUserName());
        edtPassword.setText(LoginActivity.prefConfig.readPassWord());
        btnLogin=view.findViewById(R.id.btnLogin);
        btnChangeLanguage=view.findViewById(R.id.btnChangeLanguage);
        btnChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLanguage();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtUsername.getText().toString().equals("")&&!edtPassword.getText().toString().equals("") ){
                    if (CheckConnection.haveNetworkConnection(getContext())){
                        performLogin();
                    }else {
                        LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
                    }

                }else {
                    LoginActivity.prefConfig.displayToast(getResources().getString(R.string.login_empty));
                }

            }
        });
    }
    private void performLogin(){
        final String username=edtUsername.getText().toString();
        final String password=edtPassword.getText().toString();
        Call<User> call= LoginActivity.apiInterface.performUserLogin(username,password);
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Login...");
        progressDialog.show();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressDialog.dismiss();
                if (!response.isSuccessful()){
                    return;
                }
                User user=response.body();
                if (user==null){
                    return;
                }
                if (user.getResponse().equals("ok")){
                    LoginActivity.prefConfig.writeIdUser(user.getIdUser());
                    LoginActivity.prefConfig.writeLoginStatus(true);
                    LoginActivity.prefConfig.writeName(user.getName());
                    LoginActivity.prefConfig.writeUserName(username);
                    LoginActivity.prefConfig.writePassWord(password);
                    LoginActivity.prefConfig.writePath(user.getPath());
                    LoginActivity.prefConfig.writeLevelSpeech(user.getLevelSpeech());
                    LoginActivity.prefConfig.writeLevelWrite(user.getLevelWrite());
                    LoginActivity.prefConfig.writePhone(user.getPhone());
                    LoginActivity.prefConfig.writeEmail(user.getEmail());
                    LoginActivity.prefConfig.writeCheckAdmin(user.getCheckAdmin());
                    if (user.getCheckAdmin()==0){
                        Intent intent=new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }else {
                        Intent intent=new Intent(getContext(), AdminManagerAcitivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else if (user.getResponse().equals("failed")){
                    LoginActivity.prefConfig.displayToast(getResources().getString(R.string.login_fail));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressDialog.dismiss();
                LoginActivity.prefConfig.displayToast(getResources().getString(R.string.onfailure_login));
            }
        });
    }
    private void showChangeLanguage(){
        final String[] listLanguage = {"English","Việt Nam"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose Language");
        builder.setSingleChoiceItems(listLanguage, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i == 0)
                {
                    LocaleManager.setLocale("en",getActivity());
                    context.recreate();
                }
                else
                if(i == 1)
                {
                    LocaleManager.setLocale("vi",getActivity());
                    context.recreate();
                }
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
