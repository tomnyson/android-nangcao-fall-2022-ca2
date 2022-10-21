package com.example.androidnangcaoca2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseNotificationService extends FirebaseMessagingService {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        sendNotification(message.getNotification().getTitle(),
                message.getNotification().getBody());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendNotification(String title, String body) {
        NotificationChannel channel= new NotificationChannel("channel1",
                "hello",
                NotificationManager.IMPORTANCE_HIGH);
        NotificationManager manager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        //Creating the notification object
        NotificationCompat.Builder notification=new NotificationCompat.Builder(this,"channel1");
        //notification.setAutoCancel(true);
        notification.setContentTitle(title);
        notification.setContentText(body);
        notification.setSmallIcon(R.drawable.ic_launcher_foreground);
        //make the notification manager to issue a notification on the notification's channel
        manager.notify(121,notification.build());
    }
}
