package com.example.projectda.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.projectda.R;
import com.example.projectda.activity.LoginActivity;
import com.example.projectda.activity.QuestionManagementActivity;
import com.example.projectda.adapter.QuestionManagementAdapter;
import com.example.projectda.callback.CallbackQuestionManager;
import com.example.projectda.models.User;
import com.example.projectda.utils.CheckConnection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.projectda.activity.QuestionManagementActivity.mQuestion;

public class QuestionManagementFragment extends Fragment {
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    public static ProgressBar progressBar,progressBarBottom;
    public static QuestionManagementAdapter questionManagementAdapter;
    private QuestionManagementActivity mContext;

    private Boolean isLoading=true;
    private int pastVisbleItems,totallItemCount;
    private int view_threashold =17;

    public QuestionManagementFragment(){};

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext= (QuestionManagementActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_question_management,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recyclerViewQSTTManagement);
        progressBar=view.findViewById(R.id.progressBar);
        progressBarBottom=view.findViewById(R.id.progressBarBottom);
        if (mQuestion.size()==0){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
        setupView();
        actionBar(view);
    }

    private void setupView() {
        final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        questionManagementAdapter= new QuestionManagementAdapter(mQuestion, new CallbackQuestionManager() {
            @Override
            public void onItemClick(int position) {
                mContext.getSupportFragmentManager().beginTransaction().addToBackStack("t").replace(R.id.fragment_manager,new DetailQuestionManagementFragment(position)).commit();
            }
            @Override
            public void onLongClick(final int id) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle(getResources().getString(R.string.delete_title));
                builder.setMessage(getResources().getString(R.string.delete_content));
                builder.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (CheckConnection.haveNetworkConnection(getContext())){
                            deleteQuestion(id);
                        }else {
                            LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
                        }
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

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(questionManagementAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //visibleItemCount=layoutManager.getChildCount();
                totallItemCount=layoutManager.getItemCount();
                pastVisbleItems=layoutManager.findFirstVisibleItemPosition();
                if (dy>0){
                    if (isLoading){
                        if (totallItemCount>QuestionManagementActivity.previous_total){
                            isLoading=false;
                            QuestionManagementActivity.previous_total=totallItemCount;
                        }

                    }
                    if (!isLoading && (totallItemCount<=(pastVisbleItems+ view_threashold))){
                        isLoading=true;
                        progressBarBottom.setVisibility(View.VISIBLE);
                        QuestionManagementActivity.pagenumber++;
                        if (CheckConnection.haveNetworkConnection(getContext())){
                            QuestionManagementActivity.addData();
                        }else {
                            LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
                        }

                    }
                }
            }
        });
    }

    private void deleteQuestion(int id){
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        Call<User> call=QuestionManagementActivity.apiInterface.deleteQuestion(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressDialog.dismiss();
                if (response.body()==null) return;
                if (response.body().getResponse().equals("ok")){
                    Toast.makeText(getContext(),getString(R.string.delete_success),Toast.LENGTH_SHORT).show();
                    QuestionManagementActivity.setData();
                }else {
                    Toast.makeText(getContext(),getString(R.string.delete_error),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(),getString(R.string.register_error),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void actionBar(View view) {
        toolbar=view.findViewById(R.id.tbQSTTManagement);
        mContext.setSupportActionBar(toolbar);
        mContext.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.finish();
            }
        });
    }

    public static void updateAdapter() {
        questionManagementAdapter.updateAdapter(mQuestion);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.draw_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemInsert:
                mContext.getSupportFragmentManager().beginTransaction().addToBackStack("t").replace(R.id.fragment_manager,new DetailQuestionManagementFragment(-1)).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
