package com.example.projectda.activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.example.projectda.R;
import com.example.projectda.fragments.QuestionManagementFragment;
import com.example.projectda.models.Question;
import com.example.projectda.utils.service.ApiClient;
import com.example.projectda.utils.service.ApiInterface;
import com.example.projectda.utils.CheckConnection;

import java.util.ArrayList;
import java.util.List;

public class QuestionManagementActivity extends AppCompatActivity {
    public static ApiInterface apiInterface;
    public static ArrayList<Question> mQuestion;
    public static int pagenumber=0;
    public static int previous_total=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_management);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        mQuestion=new ArrayList<>();
        setupView();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            setData();
        }else {
            LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
        }

    }

    private void setupView() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_manager,new QuestionManagementFragment()).commit();
    }

    public static void setData() {
        mQuestion.clear();
        pagenumber=0;
        previous_total=0;
        Call<List<Question>> call= apiInterface.getAllQuestions(0);
        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if (response.body()==null){
                    return;
                }
                mQuestion= (ArrayList<Question>) response.body();
                QuestionManagementFragment.updateAdapter();
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Log.d("BBB",t.getMessage());
            }
        });
    }
    public static void addData() {
        Call<List<Question>> call= apiInterface.getAllQuestions(pagenumber);
        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if (response.body()==null){
                    return;
                }
                for (Question question: response.body()){
                    mQuestion.add(question);
                }
                QuestionManagementFragment.updateAdapter();
                QuestionManagementFragment.progressBarBottom.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Log.d("BBB",t.getMessage());
                QuestionManagementFragment.progressBarBottom.setVisibility(View.GONE);
            }
        });
    }
}