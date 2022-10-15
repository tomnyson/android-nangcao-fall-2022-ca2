package com.example.androidnangcaoca2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.example.androidnangcaoca2.dto.SinhVien;

public class ThongBaoService extends Service {
    public ThongBaoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        if(intent != null) {
//            int kq = intent.getExtras().getInt("kq");
//            Toast.makeText(this, kq+"", Toast.LENGTH_SHORT).show();
            SinhVien sv = (SinhVien) intent.getSerializableExtra("sv");
            Toast.makeText(this, sv.toString(), Toast.LENGTH_LONG).show();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }
}
