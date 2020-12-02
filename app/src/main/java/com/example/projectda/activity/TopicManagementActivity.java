package com.example.projectda.activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.example.projectda.R;
import com.example.projectda.fragments.TopicManagementFragment;
import com.example.projectda.models.Topic;
import com.example.projectda.utils.service.ApiClient;
import com.example.projectda.utils.service.ApiInterface;
import com.example.projectda.utils.CheckConnection;

import java.util.ArrayList;
import java.util.List;

public class TopicManagementActivity extends AppCompatActivity {
    public static ApiInterface apiInterface;
    public static ArrayList<Topic> mTopics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_management);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        mTopics=new ArrayList<>();
        setupView();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            setData();
        }else {
            LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
        }

    }
    private void setupView() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_topic_manager,new TopicManagementFragment()).commit();
    }

    public static void setData() {
        mTopics.clear();
        Call<List<Topic>> call= apiInterface.getAllTopics();
        call.enqueue(new Callback<List<Topic>>() {
            @Override
            public void onResponse(Call<List<Topic>> call, Response<List<Topic>> response) {
                if (response.body()==null){
                    return;
                }
                mTopics= (ArrayList<Topic>) response.body();
                TopicManagementFragment.updateAdapter();
            }

            @Override
            public void onFailure(Call<List<Topic>> call, Throwable t) {
                Log.d("BBB",t.getMessage());
            }
        });
    }
}