package com.example.projectda.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectda.activity.AdminManagerAcitivity;
import com.example.projectda.activity.LoginActivity;
import com.example.projectda.activity.MainActivity;
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

public class InfoFragment extends Fragment {
    private EditText edtName;
    private ImageView ivUser,ivEditName;
    private Button btnChangePassword;
    private ImageView ivSaveInfo;
    private int REQUEST_CODE=112;
    private Bitmap bitmap;
    private TextView tvScoreSpeech,tvScoreWrite;
    private MainActivity context;
    private Toolbar tbInfo;
    private Call<User> call;
    public InfoFragment(){}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=(MainActivity)context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivUser =view.findViewById(R.id.ivUser);
        edtName =view.findViewById(R.id.edtName);
        ivEditName=view.findViewById(R.id.ivEditName);
        btnChangePassword=view.findViewById(R.id.btnChangeInfo);
        ivSaveInfo=view.findViewById(R.id.ivSaveInfo);
        ivSaveInfo.setVisibility(View.GONE);
        tvScoreSpeech=view.findViewById(R.id.tvScoreSpeech);
        tvScoreWrite=view.findViewById(R.id.tvScoreWrite);
        setupView();
        context.tbMain.setVisibility(View.GONE);
        actionBar(view);
    }

    private void actionBar(View view) {
        tbInfo=view.findViewById(R.id.tbInfo);
        context.setSupportActionBar(tbInfo);
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbInfo.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        tbInfo.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.getSupportFragmentManager().popBackStack();
            }
        });
    }

    private void setupView() {
        edtName.setText(LoginActivity.prefConfig.readName());
        if (MainActivity.key==1){
            ivUser.setImageBitmap(AdminManagerAcitivity.bitmapUser);
        }else {
            ivUser.setImageBitmap(MainActivity.bitmapUser);
        }

        tvScoreWrite.setText(LoginActivity.prefConfig.readLevelWrite()+"");
        tvScoreSpeech.setText(LoginActivity.prefConfig.readLevelSpeech()+"");
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.fragment_main,new ChangePassWordFragment()).commit();
            }
        });
        ivEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtName.setEnabled(true);
                ivEditName.setVisibility(View.GONE);
                ivSaveInfo.setVisibility(View.VISIBLE);
            }
        });
        ivSaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtName.getText().toString().toUpperCase().equals(LoginActivity.prefConfig.readName().toUpperCase())){
                    if (CheckConnection.haveNetworkConnection(getContext())){
                        updateNameUser();
                    }else {
                        LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
                    }

                }
                edtName.setEnabled(false);
                ivEditName.setVisibility(View.VISIBLE);
                ivSaveInfo.setVisibility(View.GONE);

            }
        });
        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle(getResources().getString(R.string.title_change_avatar));
                builder.setMessage(getResources().getString(R.string.content_change_avatar));
                builder.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        openImageResoure();
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton(getResources().getString(R.string.cancle), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                Dialog dialog=builder.create();
                dialog.show();
            }
        });
    }
    private void openImageResoure(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,REQUEST_CODE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==REQUEST_CODE && resultCode == getActivity().RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),uri);
                if (CheckConnection.haveNetworkConnection(getContext())){
                    updateImageToServer();
                }else {
                    LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String convertImageToString(){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,20,byteArrayOutputStream);
        byte[] imgByte= byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }
    private void updateImageToServer(){
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Uploading...");
        progressDialog.show();
        String image= convertImageToString();
        String username=LoginActivity.prefConfig.readUserName();
        call= LoginActivity.apiInterface.updateImageUser(username,image);
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
                    LoginActivity.prefConfig.displayToast(getResources().getString(R.string.upload_image_success));
                    ivUser.setImageBitmap(bitmap);
                    if (MainActivity.key==1){
                        AdminManagerAcitivity.bitmapUser=bitmap;
                        AdminManagerAcitivity.ivUser.setImageBitmap(bitmap);
                        MainActivity.bitmapUser=bitmap;
                        MainActivity.ivUser.setImageBitmap(bitmap);
                    }else {
                        MainActivity.bitmapUser=bitmap;
                        MainActivity.ivUser.setImageBitmap(bitmap);
                    }
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressDialog.dismiss();
                if (call.isCanceled()){
                    Log.d("BBB","is cancle");
                }else {
                    LoginActivity.prefConfig.displayToast(getResources().getString(R.string.register_error));
                }


            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (call!=null){
            call.cancel();
        }
    }

    private void updateNameUser(){
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Uploading...");
        progressDialog.show();
        final String name= edtName.getText().toString().toUpperCase();
        String username=LoginActivity.prefConfig.readUserName();
        Call<User> call= LoginActivity.apiInterface.updateNameUser(username,name);
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
                    LoginActivity.prefConfig.displayToast(getResources().getString(R.string.upload_name_success));
                    LoginActivity.prefConfig.writeName(name);
                    MainActivity.tvUser.setText(name);
                }else if (user.getResponse().equals("error")){
                    LoginActivity.prefConfig.displayToast(getResources().getString(R.string.register_error));
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
