package com.example.androidnangcaoca2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;

public class Bai6FacebookActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    LoginButton loginButton;
    private static final String EMAIL = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai6_facebook);
        loginButton = findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        System.out.println("test");
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if(accessToken != null && !accessToken.isExpired()) {
            Intent intent = new Intent(Bai6FacebookActivity.this, profileActivity.class);
            startActivity(intent);
        }
//        getHash();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.i(EMAIL, "Call success login");
                Intent intent = new Intent(Bai6FacebookActivity.this, profileActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(Bai6FacebookActivity.this, "failed", Toast.LENGTH_SHORT).show();
                System.out.println(exception.getMessage());
            }
        });
    }
    public void getHash() {

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.androidnangcaoca2",                  //Insert your own package name.
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
