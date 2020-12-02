package com.example.projectda.utils.notification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseIdService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String refreshToken= FirebaseInstanceId.getInstance().getToken();
        Log.d("BBB",refreshToken);
    }

    public static String getToken(){
        return FirebaseInstanceId.getInstance().getToken();
    }
}
