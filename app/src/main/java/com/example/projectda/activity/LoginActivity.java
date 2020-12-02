package com.example.projectda.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.projectda.R;
import com.example.projectda.fragments.LoginFragment;
import com.example.projectda.utils.service.ApiClient;
import com.example.projectda.utils.service.ApiInterface;
import com.example.projectda.utils.data.PrefConfig;

public class LoginActivity extends AppCompatActivity {

    public static PrefConfig prefConfig;
    public static ApiInterface apiInterface;
    public static boolean isCreate=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET},REQUEST_CODE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        prefConfig=new PrefConfig(this);
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        setupView();
    }

    private void setupView() {
        if (findViewById(R.id.fragment_container)!=null){
            if (prefConfig.readLoginStatus()){
                if (prefConfig.readCheckAdmin()==0){
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent=new Intent(getApplicationContext(),AdminManagerAcitivity.class);
                    startActivity(intent);
                    finish();
                }

            }else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LoginFragment()).commit();
            }
        }
    }


}