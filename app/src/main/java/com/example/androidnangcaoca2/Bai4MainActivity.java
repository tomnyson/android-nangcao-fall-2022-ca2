package com.example.androidnangcaoca2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Bai4MainActivity extends AppCompatActivity implements LocationListener {
    private TextView txtKetQua;
    private Location globalLocation;
    private LocationManager locationManager;
    private Button checkConnect;
    private static int DISTANCE = 5;
    private static int TIMEOUT = 1000;
    private static final int  PERMISSION_CHECK_ACCESS_FINE_LOCATION = 1;
    private ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai4_main);
        txtKetQua = findViewById(R.id.txtKetQuaLocation);
        checkConnect = findViewById(R.id.btnCheckConnection);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Bai4MainActivity.this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_CHECK_ACCESS_FINE_LOCATION

            );
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        checkConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(networkInfo.isConnected()) {
                    Toast.makeText(Bai4MainActivity.this, "WIFI", Toast.LENGTH_SHORT).show();
                }
                if(mobileInfo.isConnected()) {
                    Toast.makeText(Bai4MainActivity.this, "MOBILE", Toast.LENGTH_SHORT).show();
                }
            }
        });

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, DISTANCE, TIMEOUT, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, DISTANCE, TIMEOUT, this);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        globalLocation = location;
        txtKetQua.setText("Long"+ globalLocation.getLongitude() + "lat: " + globalLocation.getLatitude());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CHECK_ACCESS_FINE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    txtKetQua.setText("Long"+ globalLocation.getLongitude() + "lat: " + globalLocation.getLatitude());
                } else {
                    Toast.makeText(this, "chưa cấp quyền", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

