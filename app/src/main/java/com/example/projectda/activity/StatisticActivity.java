package com.example.projectda.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;

import com.example.projectda.R;
import com.example.projectda.adapter.StatisticAdapter;
import com.example.projectda.fragments.DetailQuestionManagementFragment;
import com.example.projectda.models.Statistic;
import com.example.projectda.utils.CheckConnection;

import java.util.ArrayList;
import java.util.List;

public class StatisticActivity extends AppCompatActivity {
    private Toolbar tbStatistic;
    private ArrayList<Statistic> mStatistics;
    private ProgressBar progressBar,progressBarBottom;
    private RecyclerView recyclerView;

    private Boolean isLoading=true;
    private int pastVisbleItems,totallItemCount;
    private int view_threashold =17;
    private int pagenumber=0;
    private int previous_total=0;
    private StatisticAdapter statisticAdapter;
    private LayoutAnimationController layoutAnimationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mStatistics=new ArrayList<>();

        recyclerView=findViewById(R.id.recyclerViewStatistic);
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
        Call<List<Statistic>> call=LoginActivity.apiInterface.getMediumScore(LoginActivity.prefConfig.readIdUser(),pagenumber);
        call.enqueue(new Callback<List<Statistic>>() {
            @Override
            public void onResponse(Call<List<Statistic>> call, Response<List<Statistic>> response) {
                if (response.body()==null) return;

                for (Statistic statistic: response.body()){
                    mStatistics.add(statistic);
                }

                recyclerView.setLayoutAnimation(layoutAnimationController);

                statisticAdapter.updateAdapter(mStatistics);

                progressBar.setVisibility(View.GONE);
                progressBarBottom.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Statistic>> call, Throwable t) {
                LoginActivity.prefConfig.displayToast(getString(R.string.register_error));
            }
        });
    }

    private void setupView() {
        statisticAdapter=new StatisticAdapter(mStatistics);
        layoutAnimationController= AnimationUtils.loadLayoutAnimation(getApplicationContext(),R.anim.layout_animation_left_to_right);
        final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(statisticAdapter);
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
        tbStatistic =findViewById(R.id.tbStatistic);
        setSupportActionBar(tbStatistic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbStatistic.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        tbStatistic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.draw_menu_help,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_help:
                showDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialog(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.title_help));
        builder.setMessage(getResources().getString(R.string.content_help));
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        Dialog dialog=builder.create();
        dialog.show();
    }
}