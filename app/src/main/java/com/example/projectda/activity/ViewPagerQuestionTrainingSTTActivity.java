package com.example.projectda.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectda.R;
import com.example.projectda.adapter.ViewPagerQuestionTrainingSTTAdapter;
import com.example.projectda.callback.CallbackAlphabet;
import com.example.projectda.models.Data;
import com.example.projectda.models.Hypotheses;
import com.example.projectda.models.QuestionTopic;
import com.example.projectda.models.Result;
import com.example.projectda.utils.service.ApiClientSTT;
import com.example.projectda.utils.service.ApiInterface;
import com.example.projectda.utils.CheckConnection;
import com.example.projectda.utils.LocaleManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewPagerQuestionTrainingSTTActivity extends AppCompatActivity implements CallbackAlphabet {

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static final String LOG_TAG = "AudioRecordTest";
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};
    private static String fileName = null;
    private MediaRecorder recorder = null;
    private MediaPlayer player = null;
    private MediaPlayer mediaPlayer;
    private ImageView btnPlayMP3,btnUpload;
    boolean isRecording =false;
    private boolean isPlaying =false;
    private Toolbar tbQuestion;
    private ImageView imgMic,imgSound,imgPointingDown;
    private int mPosition;
    private ViewPager vpQuesitonSTT;
    private ViewPagerQuestionTrainingSTTAdapter viewPagerQuestionTrainingSTTAdapter;
    public static ArrayList<QuestionTopic> mQuestionTopicViewPagerSTT;
    private boolean isShowUpload=false;
    private boolean isShowPlay=false;
    private Animation animationSound,animationPointingDown;
    private Call<List<Data>> callData;
    private int idtopic,numbertopic;
    private ApiInterface apiInterface;
    private TextView tvUpdating;
    private ProgressBar progressBarCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_question_training_stt);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
        Intent intent=getIntent();
        apiInterface= ApiClientSTT.getApiClient().create(ApiInterface.class);
        idtopic=intent.getIntExtra("idtopic",0);
        numbertopic=intent.getIntExtra("title",0);
        init();
        checkShowIcon();
        actionBar();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            setDataViewPager();
        }else {
            LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
        }
        vpQuesitonSTT.setCurrentItem(mPosition);
        setupEvent();
    }

    private void init() {
        mQuestionTopicViewPagerSTT =new ArrayList<>();

        animationSound = new AlphaAnimation(1,0); //to change visibility from visible to invisible
        animationSound.setDuration(500); //1 second duration for each animation cycle
        animationSound.setInterpolator(new LinearInterpolator());
        animationSound.setRepeatCount(Animation.INFINITE); //repeating indefinitely
        animationSound.setRepeatMode(Animation.REVERSE); //animation will start from end point once ended.

        animationPointingDown=new AlphaAnimation(1,0);
        animationPointingDown.setDuration(2000);
        animationPointingDown.setRepeatCount(Animation.INFINITE);

        imgPointingDown=findViewById(R.id.imgPointingDown);
        imgPointingDown.startAnimation(animationPointingDown);

        fileName = getExternalCacheDir().getAbsolutePath();
        fileName += "/audiorecord.mp3";

        btnUpload=findViewById(R.id.btnUpload);
        btnPlayMP3=findViewById(R.id.btnStartMp3);
        imgSound=findViewById(R.id.imgSound);
        imgMic=findViewById(R.id.btnRecord);

        vpQuesitonSTT=findViewById(R.id.vpQuestionSTT);
        tvUpdating=findViewById(R.id.tvUpdating);

        progressBarCheck=findViewById(R.id.progressBarCheck);
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
        mQuestionTopicViewPagerSTT.clear();
        Call<List<QuestionTopic>> call=TrainingTopicActivity.apiInterface.getQuestionTraining(idtopic);
        call.enqueue(new Callback<List<QuestionTopic>>() {
            @Override
            public void onResponse(Call<List<QuestionTopic>> call, Response<List<QuestionTopic>> response) {
                if (response.body()==null) return;
                mQuestionTopicViewPagerSTT = (ArrayList<QuestionTopic>) response.body();

                if (mQuestionTopicViewPagerSTT.size()!=0){
                    viewPagerQuestionTrainingSTTAdapter=new ViewPagerQuestionTrainingSTTAdapter(getSupportFragmentManager());
                    vpQuesitonSTT.setAdapter(viewPagerQuestionTrainingSTTAdapter);
                    vpQuesitonSTT.setVisibility(View.VISIBLE);
                    tvUpdating.setVisibility(View.GONE);
                }else {
                    tvUpdating.setVisibility(View.VISIBLE);
                    imgMic.setEnabled(false);
                }

            }

            @Override
            public void onFailure(Call<List<QuestionTopic>> call, Throwable t) {

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }

        if (!permissionToRecordAccepted ) finish();
    }

    private void setupEvent() {
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
                if (!isPlaying){
                    onRecord();
                }
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (isShowUpload){
                        Toast.makeText(getApplicationContext(),getResources().getString(R.string.please_wait),Toast.LENGTH_SHORT).show();
                        isShowUpload=false;
                        progressBarCheck.setVisibility(View.VISIBLE);
                        imgMic.setEnabled(false);
                        checkShowIcon();
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                            uploadFileWithRetrofit();
                        }else {
                            imgMic.setEnabled(true);
                            progressBarCheck.setVisibility(View.GONE);
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
/*                    if (isPlaying){
                        stopPlaying();
                        imgMic.setEnabled(true);
                    }*/
                    if (player!=null){
                        player.reset();
                        //isPlaying =false;
                        imgMic.setEnabled(true);
                    }

                    isShowPlay=false;
                    isShowUpload=false;
                    checkShowIcon();

                    if (callData!=null){
                        callData.cancel();
                    }
                }
                if (mediaPlayer!=null){
                    mediaPlayer.reset();
                    isPlaying=false;
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
    protected void onDestroy() {
        super.onDestroy();
        if (player!=null){
            //stopPlaying();
            player.reset();
            //isPlaying =false;
        }
        if (callData!=null){
            callData.cancel();
        }
        if (mediaPlayer!=null){
            mediaPlayer.reset();
            isPlaying=false;
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
        //isPlaying =true;
    }

    private void stopPlaying() {
        player.release();
        player = null;
        //isPlaying =false;
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
                progressBarCheck.setVisibility(View.GONE);
                isShowUpload=true;
                checkShowIcon();
                if (response.body()==null){
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.response_null),Toast.LENGTH_SHORT).show();
                }else {
                    if (response.body().size()==0){
                        Toast.makeText(getApplicationContext(),getResources().getString(R.string.data_null),Toast.LENGTH_SHORT).show();
                    }else {
                        Data data=response.body().get(0);
                        Result result=data.getResult();
                        Hypotheses hypotheses=result.getHypotheses().get(0);
                        String text=hypotheses.getTranscript();
                        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
                        checkQuestion(text);

                    }
                }
            }
            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                imgMic.setEnabled(true);
                progressBarCheck.setVisibility(View.GONE);
                if (callData.isCanceled()){

                }else {
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.something_wrong),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void actionBar() {
        tbQuestion=findViewById(R.id.tbQuestion);
        tbQuestion.setTitle("Topic "+numbertopic);
        setSupportActionBar(tbQuestion);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbQuestion.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        tbQuestion.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
    }

    private void checkQuestion(String textRecord){
        final int positionVP=vpQuesitonSTT.getCurrentItem();
        String ans= textRecord.toUpperCase().trim();
        String ques= mQuestionTopicViewPagerSTT.get(positionVP).getContent().toUpperCase().trim();
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
        Toast.makeText(getApplicationContext(),getResources().getString(R.string.you_get)+" "+t+" "+getResources().getString(R.string.points),Toast.LENGTH_SHORT).show();
    }

    private int searchmax(int a,int b){
        if (a>=b){
            return a;
        }else {
            return b;
        }
    }

    @Override
    public void onItemClick(int position) {
        if (!isRecording){
            if (mediaPlayer!=null){
                mediaPlayer.reset();
            }
            playMp3(position);
        }

    }

    private void playMp3(int position){
        isPlaying=true;
        String fileName = getExternalCacheDir().getAbsolutePath();
        String file=fileName+"/audiotrainingstt"+position+".mp3";
        mediaPlayer= new MediaPlayer();
        mediaPlayer.reset();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            mediaPlayer.setDataSource(fis.getFD());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                isPlaying=false;
            }
        });
    }
}