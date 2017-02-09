package com.sparity.jenkinsdemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by Pavan on 1/18/2017.
 */

public class Splashscreen extends Activity {

    public String deviceToken;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

         deviceToken = FirebaseInstanceId.getInstance().getToken();
        if (deviceToken!=null&&!deviceToken.equalsIgnoreCase(""))
            navigateToScreen();
        else {
            LocalBroadcastManager.getInstance(this).
                    registerReceiver(tokenReceiver, new IntentFilter(Configuration.KEY_TOKEN_RECEIVER));
        }



    }
    /*FCM TOKEN RECEIVER*/
    BroadcastReceiver tokenReceiver = new BroadcastReceiver() {
        public String deviceToken;

        @Override
        public void onReceive(Context context, Intent intent) {
            deviceToken = intent.getStringExtra(Configuration.KEY_FCM_TOKEN);
            navigateToScreen();
        }
    };

    private void navigateToScreen() {
        try {
            Handler handler = new Handler();
            Runnable run = new Runnable() {
                public void run() {
                    Intent intent = new Intent(Splashscreen.this,
                            MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            };
            handler.postDelayed(run, 2000);
        } catch (Exception e)
        {
               e.printStackTrace();
        }
    }

}
