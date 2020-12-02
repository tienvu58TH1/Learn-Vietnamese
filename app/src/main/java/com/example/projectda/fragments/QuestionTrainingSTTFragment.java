package com.example.projectda.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.projectda.R;
import com.example.projectda.activity.LoginActivity;
import com.example.projectda.activity.ViewPagerQuestionTrainingSTTActivity;
import com.example.projectda.callback.CallbackAlphabet;
import com.example.projectda.utils.service.ApiClient;
import com.example.projectda.utils.CheckConnection;
import com.example.projectda.utils.InputStreamVolleyRequest;
import com.example.projectda.utils.LocaleManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class QuestionTrainingSTTFragment extends Fragment {
    private int position;
    private String fileName;
    private File file;
    private static ImageView imgHelp;
    private ImageView imgTranslate;
    CallbackAlphabet callbackMediaPlayer;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callbackMediaPlayer= (CallbackAlphabet) context;
    }

    public QuestionTrainingSTTFragment(int i){
        position=i;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question_training_stt,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvQuestion=view.findViewById(R.id.tvQuestion);
        tvQuestion.setText(ViewPagerQuestionTrainingSTTActivity.mQuestionTopicViewPagerSTT.get(position).getContent());
        TextView tvLevel=view.findViewById(R.id.tvLevel);
        tvLevel.setText("Level "+ViewPagerQuestionTrainingSTTActivity.mQuestionTopicViewPagerSTT.get(position).getLevel());
        TextView tvProgress=view.findViewById(R.id.tvProgress);
        tvProgress.setText((position+1)+"/"+ViewPagerQuestionTrainingSTTActivity.mQuestionTopicViewPagerSTT.size());
        imgHelp=view.findViewById(R.id.imgHelp);
        imgTranslate=view.findViewById(R.id.imgTranslate);
        fileName = getActivity().getExternalCacheDir().getAbsolutePath();
        file=new File(fileName+"/audiotrainingstt"+position+".mp3");

        try {
            file.createNewFile();
            if (CheckConnection.haveNetworkConnection(getContext())){
                postDataWithVolley();
            }else {
                LoginActivity.prefConfig.displayToast(getString(R.string.no_network));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        postDataWithVolley();
        imgHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callbackMediaPlayer.onItemClick(position);
            }
        });
        imgTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.prefConfig.displayToast(ViewPagerQuestionTrainingSTTActivity.mQuestionTopicViewPagerSTT.get(position).getTranslate());
            }
        });
    }

    private void postDataWithVolley() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("text", ViewPagerQuestionTrainingSTTActivity.mQuestionTopicViewPagerSTT.get(position).getContent());
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
                saveMp3(response);
            }
        },new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),getResources().getString(R.string.register_error) + position,Toast.LENGTH_SHORT).show();
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

    private void saveMp3(byte[] mp3SoundByteArray){
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
            fos.write(mp3SoundByteArray);
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
