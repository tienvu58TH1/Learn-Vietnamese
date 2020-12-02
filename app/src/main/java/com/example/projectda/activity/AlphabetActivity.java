package com.example.projectda.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.projectda.R;
import com.example.projectda.adapter.AlphabetAdapter;
import com.example.projectda.callback.CallbackAlphabet;
import com.example.projectda.utils.data.PrefConfig;
import com.example.projectda.models.Alphabet;
import com.example.projectda.utils.service.ApiClient;
import com.example.projectda.utils.CheckConnection;
import com.example.projectda.utils.InputStreamVolleyRequest;
import com.example.projectda.utils.ItemOffsetDecoration;
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
import java.util.Map;

public class AlphabetActivity extends AppCompatActivity {
    private int column=5;
    private AlphabetAdapter alphabetAdapter;
    private ArrayList<Alphabet> mAlphabets;
    private MediaPlayer mMediaPlayer;
    private String fileName;
    private int lenght=0;
    private PrefConfig prefConfig;
    private Toolbar tbAlphabet;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);
        mAlphabets=new ArrayList<>();
        progressBar=findViewById(R.id.progressBar);
        recyclerView=findViewById(R.id.recyclerViewAlphabet);
        fileName = getExternalCacheDir().getAbsolutePath();
        prefConfig=new PrefConfig(this);

        setDataAlphabet();
        if (prefConfig.readDataAlphabetStatus()){
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            for (int i=0;i<mAlphabets.size();i++){
                File file=new File(mAlphabets.get(i).getAudio());
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                for (int i=0;i<mAlphabets.size();i++){
                    postDataWithVolley(i);
                }
            }else {
                LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
            }

        }
        setupView();
        actionBar();
        if (MainActivity.isPlaying){
            MainActivity.onPauseMp3();
        }
    }

    private void actionBar() {
        tbAlphabet=findViewById(R.id.tbAlphabet);
        setSupportActionBar(tbAlphabet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbAlphabet.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        tbAlphabet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setDataAlphabet() {
        mAlphabets.add(new Alphabet("a",fileName+"/alphabet_"+"a.mp3","a"));
        mAlphabets.add(new Alphabet("ă",fileName+"/alphabet_"+"a1.mp3","ă"));
        mAlphabets.add(new Alphabet("â",fileName+"/alphabet_"+"a2.mp3","â"));
        mAlphabets.add(new Alphabet("b",fileName+"/alphabet_"+"b.mp3","bờ"));
        mAlphabets.add(new Alphabet("c",fileName+"/alphabet_"+"c.mp3","cờ"));
        mAlphabets.add(new Alphabet("d",fileName+"/alphabet_"+"d.mp3","dờ"));
        mAlphabets.add(new Alphabet("đ",fileName+"/alphabet_"+"d1.mp3","đờ"));
        mAlphabets.add(new Alphabet("e",fileName+"/alphabet_"+"e.mp3","e"));
        mAlphabets.add(new Alphabet("ê",fileName+"/alphabet_"+"e1.mp3","ê"));
        mAlphabets.add(new Alphabet("g",fileName+"/alphabet_"+"g.mp3","g"));
        mAlphabets.add(new Alphabet("h",fileName+"/alphabet_"+"h.mp3","hờ"));
        mAlphabets.add(new Alphabet("i",fileName+"/alphabet_"+"i.mp3","i"));
        mAlphabets.add(new Alphabet("k",fileName+"/alphabet_"+"k.mp3","k"));
        mAlphabets.add(new Alphabet("l",fileName+"/alphabet_"+"l.mp3","l"));
        mAlphabets.add(new Alphabet("m",fileName+"/alphabet_"+"m.mp3","m"));
        mAlphabets.add(new Alphabet("n",fileName+"/alphabet_"+"n.mp3","n"));
        mAlphabets.add(new Alphabet("o",fileName+"/alphabet_"+"o.mp3","o"));
        mAlphabets.add(new Alphabet("ô",fileName+"/alphabet_"+"o1.mp3","ô"));
        mAlphabets.add(new Alphabet("ơ",fileName+"/alphabet_"+"o2.mp3","ơ"));
        mAlphabets.add(new Alphabet("p",fileName+"/alphabet_"+"p.mp3","pờ"));
        mAlphabets.add(new Alphabet("q",fileName+"/alphabet_"+"q.mp3","quờ"));
        mAlphabets.add(new Alphabet("r",fileName+"/alphabet_"+"r.mp3","r"));
        mAlphabets.add(new Alphabet("s",fileName+"/alphabet_"+"s.mp3","sờ"));
        mAlphabets.add(new Alphabet("t",fileName+"/alphabet_"+"t.mp3","tờ"));
        mAlphabets.add(new Alphabet("u",fileName+"/alphabet_"+"u.mp3","u"));
        mAlphabets.add(new Alphabet("ư",fileName+"/alphabet_"+"u1.mp3","ư"));
        mAlphabets.add(new Alphabet("v",fileName+"/alphabet_"+"v.mp3","vờ"));
        mAlphabets.add(new Alphabet("x",fileName+"/alphabet_"+"x.mp3","xờ"));
        mAlphabets.add(new Alphabet("y",fileName+"/alphabet_"+"y.mp3","y"));
    }

    private void setupView() {

        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),column);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new ItemOffsetDecoration(10));
        alphabetAdapter=new AlphabetAdapter(mAlphabets, new CallbackAlphabet() {
            @Override
            public void onItemClick(int position) {

                if (mMediaPlayer!=null){
                    mMediaPlayer.release();
                }
                playMp3(position);
            }
        }, getApplicationContext(), column);
        recyclerView.setAdapter(alphabetAdapter);
    }


    private void postDataWithVolley(final int position) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("text", mAlphabets.get(position).getPronunciation());
            jsonObject.put("voice", "hn-quynhanh2");
            jsonObject.put("id", "2");
            jsonObject.put("without_filter", false);
            jsonObject.put("speed", 1.0);
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
                saveMp3(response,position);
                lenght+=1;
                if (lenght==mAlphabets.size()){
                    recyclerView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    prefConfig.writeDataAlphabetStatus(false);
                }
                //btnListen.setVisibility(View.VISIBLE);
            }
        },new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("BBB", "Error: " + error.getMessage());
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

    private void saveMp3(byte[] mp3SoundByteArray,int position){
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(mAlphabets.get(position).getAudio());
            fos.write(mp3SoundByteArray);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void playMp3(int position) {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.reset();

        try {
            FileInputStream fis = new FileInputStream(mAlphabets.get(position).getAudio());
            mMediaPlayer.setDataSource(fis.getFD());
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.start();
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mMediaPlayer.release();
            }
        });
    }

}