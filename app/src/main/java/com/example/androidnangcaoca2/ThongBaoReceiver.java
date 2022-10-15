package com.example.androidnangcaoca2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

public class ThongBaoReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.i("TEST", "On internet" + intent.getAction());
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            if(isNetworkAvailable(context)) {
                Log.i("TEST", "On internet");
            } else {
                Log.i("TEST", "OFF internet");
            }
        }else if(intent.getAction().equals(Bai2Activity.ACTION_UPPERCASE_TEXT)) {
            String name = intent.getStringExtra("name");
            Toast.makeText(context, name.toUpperCase(), Toast.LENGTH_SHORT).show();
        }


    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null &&
                connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
