package com.example.androidnangcaoca2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Bai2Activity extends AppCompatActivity {
    private ThongBaoReceiver thongBaoReceiver = new ThongBaoReceiver();
    public final static String ACTION_UPPERCASE_TEXT= "com.example.androidnangcaoca2_UPPERCASE";
    private final String ACTION_SEND="send_data";
    private EditText txtName;
    private Button btnSend, btnSendLocal;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(ACTION_SEND)) {
                String ketqua = intent.getStringExtra("kq");
                Toast.makeText(context, ketqua, Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(broadcastReceiver,
                        new IntentFilter(ACTION_SEND));

        txtName = findViewById(R.id.txtbroadText);
        btnSend = findViewById(R.id.btnSendBroad);
        btnSendLocal = findViewById(R.id.btnSendLocal);

        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(ACTION_UPPERCASE_TEXT);
        registerReceiver(thongBaoReceiver, filter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Bai2Activity.this,
                        ThongBaoReceiver.class);
                intent.setAction(ACTION_UPPERCASE_TEXT);
                intent.putExtra("name", txtName.getText().toString());
                sendBroadcast(intent);
            }
        });
        btnSendLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TEST", "send here");
                String ketqua = txtName.getText().toString();
                Intent intent = new Intent(ACTION_SEND);
                intent.putExtra("kq", ketqua);
                LocalBroadcastManager.getInstance(Bai2Activity.this).sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(thongBaoReceiver != null) {
            unregisterReceiver(thongBaoReceiver);
        }
        if(broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }
}
