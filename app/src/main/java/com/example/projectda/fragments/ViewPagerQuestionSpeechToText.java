package com.example.projectda.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectda.R;
import com.example.projectda.activity.HistoryActivity;
import com.example.projectda.activity.LoginActivity;
import com.example.projectda.activity.SpeechToTextActivity;
import com.example.projectda.adapter.ViewPagerQuestionSTTAdapter;
import com.example.projectda.models.Data;
import com.example.projectda.models.Hypotheses;
import com.example.projectda.models.Question;
import com.example.projectda.models.Result;
import com.example.projectda.models.User;
import com.example.projectda.utils.service.ApiClientSTT;
import com.example.projectda.utils.service.ApiInterface;
import com.example.projectda.utils.CheckConnection;
import com.example.projectda.utils.LocaleManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPagerQuestionSpeechToText extends Fragment {

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static final String LOG_TAG = "AudioRecordTest";
//    public static final String TOKEN="MJTgul1eREW5Pf0kdnvTTYrGnA24EDIZ1i3yPWz2-tVvVIpj5B09BSTGUrQPZF9u";
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};
    private static String fileName = null;
    private MediaRecorder recorder = null;
    private MediaPlayer player = null;
    private ImageView btnPlayMP3,btnUpload;
    boolean isRecording =false;
    boolean isPlaying =false;
    private ApiInterface apiInterface;
    private Toolbar tbQuestion;
    private SpeechToTextActivity context;
    private ImageView imgMic,imgSound,imgPointingDown;
    private int mPosition;
    private ViewPager vpQuesitonSTT;
    private ViewPagerQuestionSTTAdapter viewPagerQuestionSTTAdapter;
    private ArrayList<Question> mQuestionViewPager;
    private boolean isShowUpload=false;
    private boolean isShowPlay=false;
    private TextView tvReachLevel;
    private Animation animationSound,animationPointingDown;
    private Call<List<Data>> callData;

    public ViewPagerQuestionSpeechToText(int position){
        this.mPosition=position;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context= (SpeechToTextActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_viewpager_question_stt,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActivityCompat.requestPermissions(getActivity(), permissions, REQUEST_RECORD_AUDIO_PERMISSION);
        init(view);
        checkShowIcon();
        actionBar(view);
        viewPagerQuestionSTTAdapter=new ViewPagerQuestionSTTAdapter(getActivity().getSupportFragmentManager(),mQuestionViewPager);
        vpQuesitonSTT.setAdapter(viewPagerQuestionSTTAdapter);
        setDataViewPager();
        vpQuesitonSTT.setCurrentItem(mPosition);
        setupEvent();
    }

    private void init(View view) {
        mQuestionViewPager=new ArrayList<>();

        animationSound = new AlphaAnimation(1,0); //to change visibility from visible to invisible
        animationSound.setDuration(500); //1 second duration for each animation cycle
        animationSound.setInterpolator(new LinearInterpolator());
        animationSound.setRepeatCount(Animation.INFINITE); //repeating indefinitely
        animationSound.setRepeatMode(Animation.REVERSE); //animation will start from end point once ended.

        animationPointingDown=new AlphaAnimation(1,0);
        animationPointingDown.setDuration(2000);
        animationPointingDown.setRepeatCount(Animation.INFINITE);

        imgPointingDown=view.findViewById(R.id.imgPointingDown);
        imgPointingDown.startAnimation(animationPointingDown);

        fileName = getActivity().getExternalCacheDir().getAbsolutePath();
        fileName += "/audiorecord.mp3";
        apiInterface= ApiClientSTT.getApiClient().create(ApiInterface.class);

        btnUpload=view.findViewById(R.id.btnUpload);
        btnPlayMP3=view.findViewById(R.id.btnStartMp3);
        imgSound=view.findViewById(R.id.imgSound);
        tvReachLevel=view.findViewById(R.id.tvReachLevel);
        imgMic=view.findViewById(R.id.btnRecord);


        vpQuesitonSTT=view.findViewById(R.id.vpQuestionSTT);
    }

    private void checkShowIcon(){
        if (isShowUpload){
            btnUpload.setImageResource(R.drawable.ic_baseline_check_circle_24);
        }else {
            btnUpload.setImageResource(R.drawable.ic_baseline_check_circle_outline_24);
        }
        if (isShowPlay){
            btnPlayMP3.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
        }else {
            btnPlayMP3.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
        }
    }

    private void setDataViewPager() {
        mQuestionViewPager.clear();
        for (Question question:SpeechToTextActivity.questionsSTT){
            if (question.getLevel()<=SpeechToTextActivity.levelspeech){
                mQuestionViewPager.add(question);
            }
        }
        viewPagerQuestionSTTAdapter.updateAdapter(mQuestionViewPager);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) getActivity().finish();
    }

    private void setupEvent() {
        tvReachLevel.setText( getResources().getString(R.string.level_achieved)+" "+SpeechToTextActivity.levelspeech+"/"+SpeechToTextActivity.questionsSTT.size());
        btnPlayMP3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onPlay(isPlaying);
                if (isShowPlay){
                    isShowPlay=false;
                    imgMic.setEnabled(false);
                    checkShowIcon();
                    startPlaying();
                    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            isShowPlay=true;
                            checkShowIcon();
                            player.release();
                            player = null;
                            imgMic.setEnabled(true);
                        }
                    });
                }
            }
        });

        imgMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecord();
                //setDataViewPager();
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (isShowUpload){
                        Toast.makeText(getActivity(),getResources().getString(R.string.please_wait),Toast.LENGTH_SHORT).show();
                        isShowUpload=false;
                        imgMic.setEnabled(false);
                        checkShowIcon();
                        if (CheckConnection.haveNetworkConnection(getContext())){
                            uploadFileWithRetrofit();
                        }else {
                            imgMic.setEnabled(true);
                            LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        vpQuesitonSTT.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (mPosition!=position){
                    if (isRecording){
                        isRecording =false;
                        animationSound.cancel();
                        stopRecording();
                        imgSound.setVisibility(View.INVISIBLE);
                        imgMic.setImageResource(R.drawable.micon);
                    }
                    if (isPlaying){
                        stopPlaying();
                        imgMic.setEnabled(true);
                    }

                    isShowPlay=false;
                    isShowUpload=false;
                    checkShowIcon();

                    if (callData!=null){
                        callData.cancel();
                    }
                }
                mPosition=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void onRecord() {
        if (isRecording) {
            //disable Mic and viewpager
            imgMic.setEnabled(false);
            vpQuesitonSTT.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });

            isRecording =false;
            stopRecording();
            animationSound.cancel();
            imgSound.setVisibility(View.INVISIBLE);
            imgMic.setImageResource(R.drawable.micon);
            isShowPlay=true;
            isShowUpload=true;
            checkShowIcon();

            //enable mic and viewpager after 1s
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imgMic.setEnabled(true);
                    vpQuesitonSTT.setOnTouchListener(null);
                }
            },1000);
        } else {
            //disable Mic and viewpager
            imgMic.setEnabled(false);
            vpQuesitonSTT.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });

            isShowPlay=false;
            isShowUpload=false;
            checkShowIcon();
            startRecording();
            isRecording =true;

            imgSound.setVisibility(View.VISIBLE);
            imgMic.setImageResource(R.drawable.micstop);

            imgSound.startAnimation(animationSound);

            //enable mic and viewpager after 1s
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imgMic.setEnabled(true);
                    vpQuesitonSTT.setOnTouchListener(null);
                }
            },1000);
        }
    }

    private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);

        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
        recorder.start();

    }

    private void stopRecording() {
        recorder.stop();
        recorder.release();
        recorder = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isRecording){
            isRecording =false;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stopRecording();
            animationSound.cancel();
            imgSound.setVisibility(View.INVISIBLE);
            imgMic.setImageResource(R.drawable.micstop);
            isShowPlay=true;
            isShowUpload=true;
            checkShowIcon();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (isPlaying){
            stopPlaying();
        }
        if (callData!=null){
            callData.cancel();
        }
    }


    private void startPlaying() {
        player = new MediaPlayer();
        try {
            player.setDataSource(fileName);
            player.prepare();
            player.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
        isPlaying =true;
    }

    private void stopPlaying() {
        player.release();
        player = null;
        isPlaying =false;
    }

    private void uploadFileWithRetrofit() throws IOException {
        File file=new File(fileName);
        RequestBody requestBody=RequestBody.create(MediaType.parse("*/*"),file);
        MultipartBody.Part requestData=MultipartBody.Part.createFormData("file",file.getName(),requestBody);
        callData=apiInterface.uploadFileData(requestData, LocaleManager.TOKEN);
        callData.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, retrofit2.Response<List<Data>> response) {
                imgMic.setEnabled(true);
                isShowUpload=true;
                checkShowIcon();
                if (response.body()==null){
                    Toast.makeText(getContext(),getResources().getString(R.string.response_null),Toast.LENGTH_SHORT).show();
                }else {
                    if (response.body().size()==0){
                        Toast.makeText(getContext(),getResources().getString(R.string.data_null),Toast.LENGTH_SHORT).show();
                    }else {
                        Data data=response.body().get(0);
                        Result result=data.getResult();
                        Hypotheses hypotheses=result.getHypotheses().get(0);
                        String text=hypotheses.getTranscript();
                        Toast.makeText(getActivity(),text,Toast.LENGTH_SHORT).show();
                        checkQuestion(text);

                    }
                }
            }
            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                imgMic.setEnabled(true);
                if (callData.isCanceled()){

                }else {
                    Toast.makeText(getContext(),getResources().getString(R.string.something_wrong),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void actionBar(View view) {
        tbQuestion=view.findViewById(R.id.tbQuestion);
        context.setSupportActionBar(tbQuestion);
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbQuestion.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        tbQuestion.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.getSupportFragmentManager().popBackStack();
            }
        });
    }

    private void checkQuestion(String textRecord){
        final int positionVP=vpQuesitonSTT.getCurrentItem();
        String ans= textRecord.toUpperCase().trim();
        String ques= SpeechToTextActivity.questionsSTT.get(positionVP).getContent().toUpperCase().trim();
        String[] arrAns=ans.split(" ");
        String[] arrQues=ques.split(" ");
        int t=0;
        int m=arrQues.length;
        int n=arrAns.length;
        int[][] f=new int[m+1][n+1];

        for(int i=0;i<=m;i++){
            f[i][0]=0;
        }

        for(int i=0;i<=n;i++){
            f[0][i]=0;
        }

        for(int j=1;j<=n;j++){
            for(int i=1;i<=m;i++){
                if(arrQues[i-1].equalsIgnoreCase(arrAns[j-1])){
                    f[i][j]=f[i-1][j-1]+1;

                }else{
                    f[i][j]=searchmax(f[i-1][j],f[i][j-1]);
                }
            }
        }
        t=f[m][n]*100/m;
        int d=n-m;
        if (d>0){
            t=t-d*5;
        }
        if (t<0){
            t=0;
        }
        if (t>=SpeechToTextActivity.questionsSTT.get(positionVP).getScorepass()){
            Toast.makeText(getContext(),getResources().getString(R.string.you_get)+" "+t+" "+getResources().getString(R.string.point_and_pass),Toast.LENGTH_LONG).show();
            if (SpeechToTextActivity.questionsSTT.get(positionVP).getLevel()==SpeechToTextActivity.levelspeech){

                SpeechToTextActivity.levelspeech++;
                if (CheckConnection.haveNetworkConnection(getContext())){
                    SpeechToTextActivity.updateLevelSpeech();
                }else {
                    LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
                }
                tvReachLevel.setText(getResources().getString(R.string.level_achieved)+" "+SpeechToTextActivity.levelspeech+"/"+SpeechToTextActivity.questionsSTT.size());
                setDataViewPager();
            }
            Call<User> callScore= SpeechToTextActivity.apiInterface.insertUserQuestion(SpeechToTextActivity.prefConfig.readIdUser(),SpeechToTextActivity.questionsSTT.get(positionVP).getIdQuestion(),t, Calendar.getInstance().getTimeInMillis());
            callScore.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.body()==null){
                        return;
                    }
                    if (response.body().getResponse().equals("ok")){
                        vpQuesitonSTT.setCurrentItem(positionVP+1);
                    }else {
                        SpeechToTextActivity.prefConfig.displayToast(getResources().getString(R.string.error_save_score));
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    SpeechToTextActivity.prefConfig.displayToast(getResources().getString(R.string.something_wrong));
                }
            });

        }else {
            Toast.makeText(getContext(),getResources().getString(R.string.you_get)+" "+t+" "+getResources().getString(R.string.point_and_not_pass),Toast.LENGTH_LONG).show();
        }
    }

    private int searchmax(int a,int b){
        if (a>=b){
            return a;
        }else {
            return b;
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.draw_menu_history,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_history:
                //context.getSupportFragmentManager().beginTransaction().addToBackStack("t").replace(R.id.fragment_STT,new HistoryFragment(0)).commit();
                Intent intent=new Intent(context, HistoryActivity.class);
                intent.putExtra("category",0);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
