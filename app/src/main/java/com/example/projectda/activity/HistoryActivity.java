package com.example.projectda.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.projectda.R;
import com.example.projectda.adapter.HistoryAdapter;
import com.example.projectda.models.History;
import com.example.projectda.utils.service.ApiClient;
import com.example.projectda.utils.service.ApiInterface;
import com.example.projectda.utils.CheckConnection;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    public static ProgressBar progressBar,progressBarBottom;
    private int ctg;
    private HistoryAdapter historyAdapter;
    private ArrayList<History> mHistories;
    private ApiInterface apiInterface;

    private Boolean isLoading=true;
    private int pastVisbleItems,totallItemCount;
    private int view_threashold =17;
    private int pagenumber=0;
    private int previous_total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mHistories=new ArrayList<>();
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);

        Intent intent=getIntent();
        ctg=intent.getIntExtra("category",-1);
        recyclerView=findViewById(R.id.recyclerViewHistorySTT);
        progressBar=findViewById(R.id.progressBar);
        progressBarBottom=findViewById(R.id.progressBarBottom);
        setupView();
        actionBar();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            setData();
        }else {
            LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
        }

    }
    private void setData() {
        Call<List<History>> call=apiInterface.getHistoryUser(LoginActivity.prefConfig.readIdUser(),ctg,pagenumber);
        call.enqueue(new Callback<List<History>>() {
            @Override
            public void onResponse(Call<List<History>> call, Response<List<History>> response) {
                if (response.body()==null) return;

                for (History history: response.body()){
                    mHistories.add(history);
                }

                historyAdapter.updateAdapter(mHistories);
                progressBar.setVisibility(View.GONE);
                progressBarBottom.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<History>> call, Throwable t) {

            }
        });
    }

    private void setupView() {
        historyAdapter=new HistoryAdapter(mHistories);
        final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(historyAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //visibleItemCount=layoutManager.getChildCount();
                totallItemCount=layoutManager.getItemCount();
                pastVisbleItems=layoutManager.findFirstVisibleItemPosition();
                if (dy>0){
                    if (isLoading){
                        if (totallItemCount>previous_total){
                            isLoading=false;
                            previous_total=totallItemCount;
                        }

                    }
                    if (!isLoading && (totallItemCount<=(pastVisbleItems+ view_threashold))){
                        isLoading=true;
                        progressBarBottom.setVisibility(View.VISIBLE);
                        pagenumber++;
                        setData();
                    }
                }
            }
        });
    }

    private void actionBar() {
        toolbar=findViewById(R.id.tbHistory);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}