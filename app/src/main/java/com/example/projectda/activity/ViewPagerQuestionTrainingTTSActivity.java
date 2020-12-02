package com.example.projectda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.projectda.R;
import com.example.projectda.adapter.ViewPagerQuestionTrainingTTSAdapter;
import com.example.projectda.callback.CallbackMediaPlayer;
import com.example.projectda.models.QuestionTopic;
import com.example.projectda.utils.service.ApiClient;
import com.example.projectda.utils.CheckConnection;
import com.example.projectda.utils.InputStreamVolleyRequest;
import com.example.projectda.utils.LocaleManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewPagerQuestionTrainingTTSActivity extends AppCompatActivity {

    private int mIdTopic;
    private Toolbar tbQuestion;
    private ViewPager vpQuesitonTTS;
    public static ArrayList<QuestionTopic> mQuestionTopicViewPagerTTS;
    private Animation animationPointingDown;
    private ImageView imgPointingDown;
    public static ImageView btnCheck;
    private EditText edtContent;
    private int numbertopic;
    private TextView tvUpdating;
    private ViewPagerQuestionTrainingTTSAdapter viewPagerAdapter;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_question_training_tts);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Intent intent=getIntent();
        mIdTopic =intent.getIntExtra("idtopic",0);
        numbertopic=intent.getIntExtra("title",0);
        actionBar();
        init();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            setDataViewPager();
        }else {
            LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
        }
        setupEvent();
    }

    private void postDataWithVolley(final int position, final File file) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("text", mQuestionTopicViewPagerTTS.get(position).getContent());
            jsonObject.put("voice", "hn-quynhanh2");
            jsonObject.put("id", "2");
            jsonObject.put("without_filter", false);
            jsonObject.put("speed", 0.7);
            jsonObject.put("tts_return_option", 2);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String datajson=jsonObject.toString();
        final HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("token", LocaleManager.TOKEN);
        InputStreamVolleyRequest inputStreamVolleyRequest= null;
        inputStreamVolleyRequest = new InputStreamVolleyRequest(Request.Method.POST, LocaleManager.URL, new com.android.volley.Response.Listener<byte[]>() {
            @Override
            public void onResponse(byte[] response) {
                //Toast.makeText(getActivity(),"ready " + position,Toast.LENGTH_SHORT).show();
                //Log.d(TAG, "response: " + position);
                saveMp3(response,file);
            }
        },new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),getString(R.string.register_error) + position,Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return datajson.getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }
        };
        ApiClient.getQueue(getApplicationContext()).add(inputStreamVolleyRequest);
    }

    private void saveMp3(byte[] mp3SoundByteArray,File file){
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
            fos.write(mp3SoundByteArray);
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupEvent() {
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtContent.getText().toString().equals("")){
                    checkQuestion();
                    edtContent.setText("");
                }
            }
        });
        vpQuesitonTTS.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mediaPlayer!=null){
                    mediaPlayer.reset();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setDataViewPager() {
        mQuestionTopicViewPagerTTS.clear();
        Call<List<QuestionTopic>> call=TrainingTopicActivity.apiInterface.getQuestionTraining(mIdTopic);
        call.enqueue(new Callback<List<QuestionTopic>>() {
            @Override
            public void onResponse(Call<List<QuestionTopic>> call, Response<List<QuestionTopic>> response) {
                if (response.body()==null){
                    return;
                }
                mQuestionTopicViewPagerTTS = (ArrayList<QuestionTopic>) response.body();

                for (int i = 0; i< mQuestionTopicViewPagerTTS.size(); i++){
                    String pos= String.valueOf(i);
                    String fileName = getExternalCacheDir().getAbsolutePath();
                    final File file=new File(fileName+"/audiotraining"+pos+".mp3");
                    try {
                        file.createNewFile();
                        postDataWithVolley(i,file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (mQuestionTopicViewPagerTTS.size()!=0){
/*                    viewPagerQuestionTrainingTTSAdapter=new ViewPagerQuestionTrainingTTSAdapter(getSupportFragmentManager());
                    vpQuesitonTTS.setAdapter(viewPagerQuestionTrainingTTSAdapter);*/

                    viewPagerAdapter=new ViewPagerQuestionTrainingTTSAdapter(getApplicationContext(), mQuestionTopicViewPagerTTS, new CallbackMediaPlayer() {
                        @Override
                        public void onClickPlaying(int position, ProgressBar progressBar) {
                            if (mediaPlayer!=null){
                                mediaPlayer.reset();
                            }
                            playMp3(position,progressBar);
                        }
                    });
                    vpQuesitonTTS.setAdapter(viewPagerAdapter);
                    vpQuesitonTTS.setVisibility(View.VISIBLE);
                    tvUpdating.setVisibility(View.GONE);
                }else {
                    tvUpdating.setVisibility(View.VISIBLE);
                    edtContent.setEnabled(false);
                }

            }

            @Override
            public void onFailure(Call<List<QuestionTopic>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer!=null){
            mediaPlayer.reset();
        }
    }

    private void init() {
        mQuestionTopicViewPagerTTS =new ArrayList<>();

        imgPointingDown=findViewById(R.id.imgPointingDown);
        vpQuesitonTTS=findViewById(R.id.vpQuestionTTS);

        animationPointingDown=new AlphaAnimation(1,0);
        animationPointingDown.setDuration(2000);
        animationPointingDown.setRepeatCount(Animation.INFINITE);
        imgPointingDown.startAnimation(animationPointingDown);

        edtContent=findViewById(R.id.edtContent);
        btnCheck=findViewById(R.id.btnCheck);
        tvUpdating=findViewById(R.id.tvUpdating);
    }

    private void playMp3(int position, final ProgressBar progressBar) {
        String fileName = getExternalCacheDir().getAbsolutePath();
        String file=fileName+"/audiotraining"+position+".mp3";

        mediaPlayer= new MediaPlayer();
        mediaPlayer.reset();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            mediaPlayer.setDataSource(fis.getFD());
            mediaPlayer.prepare();
            progressBar.setMax(mediaPlayer.getDuration());
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final CountDownTimer countDownTimer=new CountDownTimer(20000,50) {
            @Override
            public void onTick(long l) {
                progressBar.setProgress(mediaPlayer.getCurrentPosition());
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (countDownTimer!=null){
                    countDownTimer.cancel();
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

    private void checkQuestion(){
        final int positionTTS=vpQuesitonTTS.getCurrentItem();
        String ans= edtContent.getText().toString().toUpperCase().trim();
        String ques= mQuestionTopicViewPagerTTS.get(positionTTS).getContent().toUpperCase().trim();

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
}