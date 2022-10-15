package com.example.androidnangcaoca2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidnangcaoca2.dto.SinhVien;

public class QLSinhVienActivity extends AppCompatActivity {
    private EditText txtMssv, txtTen, txtDTB;
    private Button btnThem, btnXoa, btnSua, btnXemDS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlsinh_vien_acitvity);
        txtMssv = findViewById(R.id.txtmssv);
        txtTen = findViewById(R.id.txtTen);
        txtDTB = findViewById(R.id.txtdtb);
        // link button
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnXemDS = findViewById(R.id.btnSua);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String mssv = txtMssv.getText().toString();
                    String tenSv = txtTen.getText().toString();
                    Double dtb = Double.parseDouble(txtDTB.getText().toString());
                    if(!mssv.equals("") && !tenSv.equals("") && dtb>=0 && dtb<=10) {
                        SinhVien sv = new SinhVien(mssv, tenSv, dtb);
                        Intent intentService = new Intent(QLSinhVienActivity.this,
                                ThongBaoService.class);
                        intentService.putExtra("sv", sv);
                        startService(intentService);
                    }

                } catch (Exception e) {
                    Toast.makeText(QLSinhVienActivity.this, "error !!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
