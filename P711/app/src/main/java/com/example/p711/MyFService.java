package com.example.p711;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFService extends FirebaseMessagingService {



    public MyFService() {
    }

    //생성
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        String title = remoteMessage.getNotification().getTitle();
        String control = remoteMessage.getData().get("control");
        String data = remoteMessage.getData().get("data");
        Log.d("[TAG]:",title+" "+control+" "+data);

    }
}
