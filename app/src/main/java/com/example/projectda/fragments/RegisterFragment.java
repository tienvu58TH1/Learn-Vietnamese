package com.example.projectda.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.projectda.activity.LoginActivity;
import com.example.projectda.R;
import com.example.projectda.models.User;
import com.example.projectda.utils.CheckConnection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {
    private Button btnRegister;
    private EditText edtName, edtUsername, edtPassword, edtPhone, edtEmail;
    private Toolbar tbRegister;
    private LoginActivity context;

    public RegisterFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = (LoginActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnRegister = view.findViewById(R.id.btnSaveInfo);
        edtName = view.findViewById(R.id.edtName);
        edtUsername = view.findViewById(R.id.tvUsername);
        edtPassword = view.findViewById(R.id.edtPassword);
        edtPhone = view.findViewById(R.id.edtPhone);
        edtEmail = view.findViewById(R.id.edtEmail);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtName.getText().toString().equals("")
                        && !edtUsername.getText().toString().equals("")
                        && !edtPassword.getText().toString().equals("")
                        && !edtEmail.getText().toString().equals("")
                        && !edtPhone.getText().toString().equals("")) {
                    if (edtPhone.getText().toString().length() < 10) {
                        LoginActivity.prefConfig.displayToast(getResources().getString(R.string.phone_erro));
                    }else if (!isEmailValid(edtEmail.getText().toString())){
                        LoginActivity.prefConfig.displayToast(getResources().getString(R.string.email_erro));
                    }else if (edtPassword.getText().toString().length() <= 6) {
                        LoginActivity.prefConfig.displayToast(getResources().getString(R.string.password_short));
                    } else {
                        if (CheckConnection.haveNetworkConnection(getContext())) {
                            performRegistrantion();
                        } else {
                            LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
                        }
                    }
                } else {
                    LoginActivity.prefConfig.displayToast(getResources().getString(R.string.register_empty));
                }
            }
        });
        actionBar(view);
    }

    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void actionBar(View view) {
        tbRegister = view.findViewById(R.id.tbRegister);
        context.setSupportActionBar(tbRegister);
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbRegister.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        tbRegister.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.getSupportFragmentManager().popBackStack();
            }
        });
    }

    private void performRegistrantion() {
        String image = "default";
        String name = edtName.getText().toString().toUpperCase();
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        String phone = edtPhone.getText().toString();
        String email = edtEmail.getText().toString();
        Call<User> call = LoginActivity.apiInterface.performRegistrantion(name, username, password,phone,email, image);
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Register...");
        progressDialog.show();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressDialog.dismiss();
                User user = response.body();
                if (!response.isSuccessful()) {
                    return;
                }
                if (user == null) return;
                if (user.getResponse().equals("ok")) {
                    LoginActivity.prefConfig.displayToast(getResources().getString(R.string.register_success));
                    getActivity().getSupportFragmentManager().popBackStack();
                } else if (user.getResponse().equals("exits")) {
                    LoginActivity.prefConfig.displayToast(getResources().getString(R.string.register_exits));
                } else if (user.getResponse().equals("error")) {
                    LoginActivity.prefConfig.displayToast(getResources().getString(R.string.register_error));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressDialog.dismiss();
                LoginActivity.prefConfig.displayToast(t.getMessage());
            }
        });
    }


}
