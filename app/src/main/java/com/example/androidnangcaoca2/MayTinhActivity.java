package com.example.androidnangcaoca2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MayTinhActivity extends AppCompatActivity {
    private EditText sothu1, sothu2;
    private Button btnTich, btnStart, btnStop, btnCall;
    private Intent serviceIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_may_tinh);
        sothu1 = findViewById(R.id.sothu1);
        sothu2 = findViewById(R.id.sothu2);
        btnTich = findViewById(R.id.btnTich);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnCall = findViewById(R.id.btnCall);
        btnTich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try {
                int tich = Integer.parseInt(sothu1.getText().toString()) *
                        Integer.parseInt(sothu2.getText().toString());
                   Intent intent = new Intent(MayTinhActivity.this, KetQuaActivity.class);
                   intent.putExtra("kq", tich);
                   startActivity(intent);

               } catch (Exception e) {
                   Toast.makeText(MayTinhActivity.this, "giá trị không hợp lệ", Toast.LENGTH_SHORT).show();
               }
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int tich = Integer.parseInt(sothu1.getText().toString()) *
                            Integer.parseInt(sothu2.getText().toString());
                    serviceIntent = new Intent(getBaseContext(), ThongBaoService.class);
                    serviceIntent.putExtra("kq", tich);
                    startService(serviceIntent);

                } catch (Exception e) {
                    Toast.makeText(MayTinhActivity.this, "giá trị không hợp lệ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(serviceIntent);
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intentCallService = new Intent(
                      MayTinhActivity.this,
                      SinhVienIntentService.class);
              startService(intentCallService);
            }
        });
    }
}
