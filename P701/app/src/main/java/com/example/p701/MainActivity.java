package com.example.p701;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //진동
    public void ck1(View v){
        Vibrator vibrator =
                (Vibrator) getSystemService(Context.VIBRATOR_SERVICE); //getSystem:하드웨어의 기능을 제어한다
        if(Build.VERSION.SDK_INT >= 26){
            vibrator.vibrate(VibrationEffect.createOneShot(1000,10));
        }else{
            vibrator.vibrate(1000);
        }
    }
    //알람소리
    public void ck2(View v){
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), uri);
        ringtone.play();
    }
    //파일소리
    public void ck3(View v){
        MediaPlayer player = MediaPlayer.create(getApplicationContext(), R.raw.mp);
        player.start();
    }
    //상단 알람(이상하게 두번째는 오류가남)
    public void ck4(View v){
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = null;
        if(Build.VERSION.SDK_INT >= 26){
            if(manager.getNotificationChannel("ch1") == null){
                manager.createNotificationChannel(
                        new NotificationChannel("ch1","chname",NotificationManager.IMPORTANCE_DEFAULT));
                builder = new NotificationCompat.Builder(this,"ch1");
            }
        }else{
            builder = new NotificationCompat.Builder(this);
        }
        builder.setContentTitle("Noti Test");
        builder.setContentText("Content Text");
        builder.setSmallIcon(R.drawable.b1);
        Notification noti = builder.build();
        manager.notify(1,noti);
    }
    //상단 알람 클릭 시 앱으로 이동
    public void ck5(View v){
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = null;
        if(Build.VERSION.SDK_INT >= 26){
            if(manager.getNotificationChannel("ch2") == null){
                manager.createNotificationChannel(
                        new NotificationChannel("ch2","chname",NotificationManager.IMPORTANCE_DEFAULT));
                builder = new NotificationCompat.Builder(this,"ch2");
            }
        }else{
            builder = new NotificationCompat.Builder(this);
        }
        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 101, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);
        builder.setContentTitle("Noti Test");
        builder.setContentText("Content Text");
        builder.setSmallIcon(R.drawable.b1);
        Notification noti = builder.build();
        manager.notify(1,noti);
    }


}