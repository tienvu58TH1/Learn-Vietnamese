package com.example.projectda.fragments;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.projectda.activity.HistoryActivity;
import com.example.projectda.activity.LoginActivity;
import com.example.projectda.activity.TextToSpeechActivity;
import com.example.projectda.adapter.ViewPagerQuestionTTSAdapter;
import com.example.projectda.callback.CallbackMediaPlayer;
import com.example.projectda.models.Question;
import com.example.projectda.models.User;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPagerQuestionTextToSpeech extends Fragment {

    private int mPosition;
    private Toolbar tbQuestion;
    private TextToSpeechActivity context;
    private ViewPager vpQuesitonTTS;
    private ArrayList<Question> mQuestionViewPagerTTS;
    private TextView tvReachLevel;
    private Animation animationPointingDown;
    private ImageView imgPointingDown;
    public static ImageView btnCheck;
    private EditText edtContent;
    private MediaPlayer mediaPlayer;
    private ViewPagerQuestionTTSAdapter viewPagerAdapter;

    public ViewPagerQuestionTextToSpeech(int position){
        this.mPosition=position;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=(TextToSpeechActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_viewpager_question_tts,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        actionBar(view);
        init(view);
        viewPagerAdapter=new ViewPagerQuestionTTSAdapter(context, mQuestionViewPagerTTS, new CallbackMediaPlayer() {
            @Override
            public void onClickPlaying(int position, ProgressBar progressBar) {
                if (mediaPlayer!=null){
                    mediaPlayer.reset();
                }
                playMp3(position,progressBar);
            }
        });
        vpQuesitonTTS.setAdapter(viewPagerAdapter);
        setDataViewPager();
        vpQuesitonTTS.setCurrentItem(mPosition);
        setupEvent();
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
        mQuestionViewPagerTTS.clear();
        for (Question question:TextToSpeechActivity.questions){
            if (question.getLevel()<=TextToSpeechActivity.levelwrite){
                mQuestionViewPagerTTS.add(question);
            }
        }
        for (int i=0;i<mQuestionViewPagerTTS.size();i++){
            String pos= String.valueOf(i);
            String fileName = context.getExternalCacheDir().getAbsolutePath();
            final File file=new File(fileName+"/audiotts"+pos+".mp3");
            try {
                file.createNewFile();
                if (CheckConnection.haveNetworkConnection(getContext())){
                    postDataWithVolley(i,file);
                }else {
                    LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        viewPagerAdapter.notifyDataSetChanged();
    }

    private void init(View view) {
        mQuestionViewPagerTTS=new ArrayList<>();

        imgPointingDown=view.findViewById(R.id.imgPointingDown);
        vpQuesitonTTS=view.findViewById(R.id.vpQuestionTTS);
        tvReachLevel=view.findViewById(R.id.tvReachLevelTTS);

        if (TextToSpeechActivity.levelwrite > TextToSpeechActivity.questions.size()){
            tvReachLevel.setText(getString(R.string.max_level));
        }else {
            tvReachLevel.setText(getResources().getString(R.string.level_achieved)+" "+TextToSpeechActivity.levelwrite+"/"+TextToSpeechActivity.questions.size());
        }

        animationPointingDown=new AlphaAnimation(1,0);
        animationPointingDown.setDuration(2000);
        animationPointingDown.setRepeatCount(Animation.INFINITE);
        imgPointingDown.startAnimation(animationPointingDown);

        edtContent=view.findViewById(R.id.edtContent);
        btnCheck=view.findViewById(R.id.btnCheck);
    }

    private void postDataWithVolley(final int position, final File file) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("text", mQuestionViewPagerTTS.get(position).getContent());
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
                Toast.makeText(context,getString(R.string.register_error) + position,Toast.LENGTH_SHORT).show();
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
        ApiClient.getQueue(context).add(inputStreamVolleyRequest);
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

    private void playMp3(int position, final ProgressBar progressBar) {
        String fileName = context.getExternalCacheDir().getAbsolutePath();
        String file=fileName+"/audiotts"+position+".mp3";

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mediaPlayer!=null){
            mediaPlayer.reset();
        }
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

    private void checkQuestion(){
        final int positionTTS=vpQuesitonTTS.getCurrentItem();
        String ans= edtContent.getText().toString().toUpperCase().trim();
        String ques=TextToSpeechActivity.questions.get(positionTTS).getContent().toUpperCase().trim();

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
        if (t>=TextToSpeechActivity.questions.get(positionTTS).getScorepass()){
            Toast.makeText(getContext(),getResources().getString(R.string.you_get)+" "+t+" "+getResources().getString(R.string.point_and_pass),Toast.LENGTH_SHORT).show();
            if (TextToSpeechActivity.questions.get(positionTTS).getLevel()==TextToSpeechActivity.levelwrite){
                TextToSpeechActivity.levelwrite++;
                if (CheckConnection.haveNetworkConnection(getContext())){
                    TextToSpeechActivity.updateLevelWrite();
                }else {
                    LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
                }

                if (TextToSpeechActivity.levelwrite > TextToSpeechActivity.questions.size()){
                    tvReachLevel.setText(getString(R.string.max_level));
                }else {
                    tvReachLevel.setText(getResources().getString(R.string.level_achieved)+" "+TextToSpeechActivity.levelwrite+"/"+TextToSpeechActivity.questions.size());
                }
                setDataViewPager();
            }
            if (CheckConnection.haveNetworkConnection(getContext())){
                updateHistory(positionTTS,t);
            }else {
                LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
            }
        }else {
            Toast.makeText(getContext(),getResources().getString(R.string.you_get)+" "+t+" "+getResources().getString(R.string.point_and_not_pass),Toast.LENGTH_SHORT).show();
        }
    }

    private void updateHistory(final int positionTTS, int t){
        Call<User> callScore=TextToSpeechActivity.apiInterface.insertUserQuestion(TextToSpeechActivity.prefConfig.readIdUser(),TextToSpeechActivity.questions.get(positionTTS).getIdQuestion(),t, Calendar.getInstance().getTimeInMillis());
        callScore.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body()==null){
                    return;
                }
                if (response.body().getResponse().equals("ok")){
                    vpQuesitonTTS.setCurrentItem(positionTTS+1);
                }else {
                    TextToSpeechActivity.prefConfig.displayToast(getResources().getString(R.string.error_save_score));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                TextToSpeechActivity.prefConfig.displayToast(getResources().getString(R.string.something_wrong));
            }
        });
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
                //context.getSupportFragmentManager().beginTransaction().addToBackStack("t").replace(R.id.fragment_tts,new HistoryFragment(1)).commit();
                Intent intent=new Intent(context, HistoryActivity.class);
                intent.putExtra("category",1);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
