package com.example.androidnangcaoca2;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class NotificationActivity extends AppCompatActivity {
    private EditText txtTitle, txtBody;
    private Button btnSendNotifi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        txtTitle = findViewById(R.id.txtNotifiTitle);
        txtBody = findViewById(R.id.txtNotifiContent);
        btnSendNotifi = findViewById(R.id.btnSendNotification);
        btnSendNotifi.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                sendNotification(txtTitle.getText().toString(), txtBody.getText().toString());
            }
        });
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.e("TEST", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        Log.i("TEST", token);
                        // Log and toast
                    }
                });
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
