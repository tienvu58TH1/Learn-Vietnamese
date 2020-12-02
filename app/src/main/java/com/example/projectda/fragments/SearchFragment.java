package com.example.projectda.fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.example.projectda.R;
import com.example.projectda.activity.LoginActivity;
import com.example.projectda.activity.MainActivity;
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
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment {
    private static final String TAG="BBB";
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private EditText edtContent;
    private Button btnListen;
    private MainActivity context;
    private Toolbar tbSearch;
    public SearchFragment(){};

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=(MainActivity)context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (context.isPlaying){
            context.onPauseMp3();
        }
        edtContent=view.findViewById(R.id.edtContent);
        btnListen=view.findViewById(R.id.btnListen);
        btnListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckConnection.haveNetworkConnection(getContext())){
                    postDataWithVolley();
                }else {
                    LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
                }

            }
        });
        context.tbMain.setVisibility(View.GONE);
        actionBar(view);
    }

    private void actionBar(View view) {
        tbSearch=view.findViewById(R.id.tbSearch);
        context.setSupportActionBar(tbSearch);
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbSearch.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        tbSearch.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.getSupportFragmentManager().popBackStack();
            }
        });
    }
    private void postDataWithVolley() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("text", edtContent.getText().toString());
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
        headers.put("token",LocaleManager.TOKEN);
        InputStreamVolleyRequest inputStreamVolleyRequest= null;
        inputStreamVolleyRequest = new InputStreamVolleyRequest(Request.Method.POST, LocaleManager.URL, new com.android.volley.Response.Listener<byte[]>() {
            @Override
            public void onResponse(byte[] response) {
                playMp3(response);
            }
        },new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
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
        ApiClient.getQueue(getContext()).add(inputStreamVolleyRequest);
    }
    private void playMp3(byte[] mp3SoundByteArray) {
        try {

            String fileName = getActivity().getExternalCacheDir().getAbsolutePath();
            File file=new File(fileName+"/audio.mp3");
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(mp3SoundByteArray);
            fos.close();

            mediaPlayer.reset();
            FileInputStream fis = new FileInputStream(file);
            mediaPlayer.setDataSource(fis.getFD());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
