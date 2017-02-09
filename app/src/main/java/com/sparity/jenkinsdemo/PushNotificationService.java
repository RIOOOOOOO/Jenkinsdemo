package com.sparity.jenkinsdemo;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.appboy.Appboy;
import com.appboy.support.AppboyLogger;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.sparity.jenkinsdemo.Configuration;

import java.io.IOException;

/**
 * Created by Pavan on 11/3/2016.
 */
public class PushNotificationService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String refreshedToken = null;
        try {
            refreshedToken = FirebaseInstanceId.getInstance().getToken("72915602388","FCM");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Appboy.getInstance(getApplicationContext()).registerAppboyPushMessages(refreshedToken);
        sendRegistrationToServer(refreshedToken);
        AppboyLogger.LogLevel = Log.VERBOSE;
    }


    private void sendRegistrationToServer(String token) {
        Intent intent = new Intent(Configuration.KEY_TOKEN_RECEIVER);
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        intent.putExtra(Configuration.KEY_FCM_TOKEN, token);
        broadcastManager.sendBroadcast(intent);
    }
}