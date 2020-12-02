package com.example.projectda.utils.notification;

import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import com.example.projectda.R;
import com.example.projectda.activity.LoginActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG="BBB";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());


        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        }

    }

    private void showNotification(String title, String body) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder_1 = new NotificationCompat.Builder(getApplicationContext(),getString(R.string.channel_id))
                .setSmallIcon(R.drawable.icon_app)
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                ;
        notificationManager.notify(0,builder_1.build());
    }


    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        //Toast.makeText(getApplicationContext(),s+"", Toast.LENGTH_SHORT).show();
    }


}
