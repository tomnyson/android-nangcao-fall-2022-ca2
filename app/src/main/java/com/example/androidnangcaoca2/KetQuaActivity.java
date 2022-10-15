package com.example.androidnangcaoca2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class KetQuaActivity extends AppCompatActivity {
    private TextView kq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);
        kq = findViewById(R.id.txtketqua);
        Intent intent = getIntent();
        if(intent != null) {
            int tich = intent.getIntExtra("kq",0);
            kq.setText(tich+"");
        }
    }
}
