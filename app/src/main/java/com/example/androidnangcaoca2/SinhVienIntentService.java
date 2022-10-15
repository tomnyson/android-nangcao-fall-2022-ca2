package com.example.androidnangcaoca2;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class SinhVienIntentService extends IntentService {


    public SinhVienIntentService() {
        super("SinhVienIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Toast.makeText(this, "onHandleIntent", Toast.LENGTH_SHORT).show();
    }


}
