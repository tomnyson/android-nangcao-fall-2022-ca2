package com.example.androidnangcaoca2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androidnangcaoca2.dto.SinhVien;
import com.example.androidnangcaoca2.provider.SinhVienProvider;

import java.util.ArrayList;
import java.util.List;

public class Bai3Activity extends AppCompatActivity {
    private Button btnShow, btnCallLog, btnCustomProvider, btnDSSV;
    private ListView lv;
    private List<String> list = new ArrayList<String>();
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private static final int PERMISSIONS_REQUEST_READ_CallLOG = 2;
    //link vidu https://www.tutorialspoint.com/android/android_content_providers.htm
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai4);
        btnShow = findViewById(R.id.btnShowContact);
        lv = findViewById(R.id.lvShowContact);
        btnCallLog = findViewById(R.id.btnShowCallLog);
        btnCustomProvider = findViewById(R.id.btnCustomProvider);
        btnDSSV = findViewById(R.id.btngetDSSV);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (ContextCompat.checkSelfPermission(getBaseContext(),
                            Manifest.permission.READ_CONTACTS
                    ) == PackageManager.PERMISSION_DENIED) {
                        // chưa cấp quyền
                        ActivityCompat.requestPermissions(Bai3Activity.this,
                                new String[]{Manifest.permission.READ_CONTACTS},
                                PERMISSIONS_REQUEST_READ_CONTACTS
                        );
                    } else {
                        //ok
                        loadData();
                    }


                } catch (Exception e) {
                    Log.i("TEST", e.getMessage());
                }
            }
        });

        btnCallLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (ContextCompat.checkSelfPermission(getBaseContext(),
                            Manifest.permission.READ_CALL_LOG
                    ) == PackageManager.PERMISSION_DENIED) {
                        // chưa cấp quyền
                        ActivityCompat.requestPermissions(Bai3Activity.this,
                                new String[]{Manifest.permission.READ_CALL_LOG},
                                PERMISSIONS_REQUEST_READ_CallLOG
                        );
                    } else {
                        //ok
                        loadLogCall();
                    }


                } catch (Exception e) {
                    Log.i("TEST", e.getMessage());
                }
            }
        });

        btnCustomProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SinhVien sv = new SinhVien("1","a",10);
                ContentValues values = new ContentValues();
                int Random = (int)(Math.random()*100);
                values.put("massv",Random );
                values.put("ten", sv.getTen());
                values.put("diemtb", sv.getDtb());
                Uri uri = getContentResolver().insert(
                        SinhVienProvider.CONTENT_URI, values);

                Toast.makeText(getBaseContext(),
                        uri.toString(), Toast.LENGTH_LONG).show();
            }
        });

        btnDSSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = managedQuery(SinhVienProvider.CONTENT_URI, null,
                        null, null, null);
                if(c.moveToFirst()) {
                    while (c.isAfterLast() == false) {
//                        String massv = c.getString(c.getColumnIndex("massv"));
//                        String massv = c.getString(c.getColumnIndex("massv"));
                        c.moveToNext();
                    }
                }
            }
        });

    }

    public void loadData() {
        Uri uri = Uri.parse("content://contacts/people");
//                    CursorLoader loader = new CursorLoader(getBaseContext(), uri,
//                            null,
//                            null,
//                            null,
//                            null);
//                    Cursor cursor = loader.loadInBackground();
        Cursor cursor = getContentResolver().query(uri, null,
                null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            String colId = CallLog.Calls._ID;
            String coName = CallLog.Calls.NUMBER;
            String colTime = CallLog.Calls.DURATION;
            String colType = CallLog.Calls.TYPE;
            int idxId = cursor.getColumnIndex(colId);
            int id = cursor.getInt(idxId);
            int idxName = cursor.getColumnIndex(coName);
            String name = cursor.getString(idxName);
            int idxTime = cursor.getColumnIndex(colTime);
            String time = cursor.getString(idxTime);
            int idxType = cursor.getColumnIndex(colType);
            String type = cursor.getString(idxType);

            list.add(id + "-" + name + "-" + time + "-" + type);
            cursor.moveToNext();
        }
        cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
    }


    public void loadLogCall() {
//                    CursorLoader loader = new CursorLoader(getBaseContext(), uri,
//                            null,
//                            null,
//                            null,
//                            null);
//                    Cursor cursor = loader.loadInBackground();
        Cursor cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null,
                null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            String colId = CallLog.Calls._ID;
            String coName = CallLog.Calls.NUMBER;
            String colTime = CallLog.Calls.DURATION;
            String colType = CallLog.Calls.TYPE;
            int idxId = cursor.getColumnIndex(colId);
            int id = cursor.getInt(idxId);
            int idxName = cursor.getColumnIndex(coName);
            String name = cursor.getString(idxName);
            int idxTime = cursor.getColumnIndex(colTime);
            String time = cursor.getString(idxTime);
            int idxType = cursor.getColumnIndex(colType);
            String type = cursor.getString(idxType);

            list.add(id + "-" + name + "-" + time + "-" + type);
            cursor.moveToNext();
        }
        cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_CONTACTS:
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    loadData();

                } else {
                    Toast.makeText(this, "chưa cấp quyền", Toast.LENGTH_SHORT).show();
                }
                break;
            case PERMISSIONS_REQUEST_READ_CallLOG:
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    loadLogCall();

                } else {
                    Toast.makeText(this, "chưa cấp quyền", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
