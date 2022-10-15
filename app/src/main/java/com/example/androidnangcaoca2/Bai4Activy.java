package com.example.androidnangcaoca2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.androidnangcaoca2.dto.Coupon;
import com.example.androidnangcaoca2.dto.QLCoupon;

public class Bai4Activy extends AppCompatActivity {
    private ImageButton btnCheck;
    private EditText txtCode;
    private final String ACTION_CHECK="check";
    private TextView txtKetQuaCode;
    private QLCoupon coupons = new QLCoupon();

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(ACTION_CHECK)) {
                String code = intent.getStringExtra("code");
                Coupon coupon = coupons.getDetail(code);
                if(coupon != null) {
                    txtKetQuaCode.setText("có khuyến mại"+ coupon.getDiscount());
                }else {
                    txtKetQuaCode.setText("Ko khuyến mại");
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai4_activy);
        btnCheck = findViewById(R.id.btnCheck);
        txtCode = findViewById(R.id.txtCheckCode);
        txtKetQuaCode= findViewById(R.id.txtKetQuaCode);
        // đăng ký
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter(ACTION_CHECK));
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = txtCode.getText().toString();
                Intent intent = new Intent(ACTION_CHECK);
                intent.putExtra("code", code);
                LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(intent);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}
