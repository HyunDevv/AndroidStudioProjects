package com.example.ws;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

public class SecondActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ActionBar actionBar;

    Fragment1 fragment1;
    Fragment2 fragment2;

    FragmentManager fragmentManager;

    NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();

        //처음 화면을 fragment1로 설정한다
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(
                R.id.framelayout, fragment1).commit();

        //액션바 삭제
        actionBar = getSupportActionBar();
        actionBar.hide();


        //하단탭 설정
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.tab1){
                    fragmentManager.beginTransaction().replace(
                            R.id.framelayout, fragment1).commit();
                    Toast.makeText(SecondActivity.this, "최신영화순위", Toast.LENGTH_SHORT).show();
                }else if(menuItem.getItemId() == R.id.tab2){
                    fragmentManager.beginTransaction().replace(
                            R.id.framelayout, fragment2).commit();
                    Toast.makeText(SecondActivity.this, "지도", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });  // end function


        //FCM사용
        FirebaseMessaging.getInstance().subscribeToTopic("car").
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "FCM Complete...";
                        if (!task.isSuccessful()) {
                            msg = "FCM Fail";
                        }
                        Log.d("[TAG]", msg);

                    }
                });

        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this); // 브로드캐스트를 받을 준비
        lbm.registerReceiver(receiver, new IntentFilter("notification")); // notification이라는 이름의 정보를 받겠다

    } // end onCreate


    // MyFService.java의 intent 정보를 BroadcastReceiver를 통해 받는다
    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String title = intent.getStringExtra("title");
                String control = intent.getStringExtra("control");
                String data = intent.getStringExtra("data");
                //tx.setText(control + " " + data);
                Toast.makeText(SecondActivity.this, title + " " + control + " " + data, Toast.LENGTH_SHORT).show();


                // 상단알람 사용
                manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder = null;
                if (Build.VERSION.SDK_INT >= 26) {
                    if (manager.getNotificationChannel("ch2") == null) {
                        manager.createNotificationChannel(
                                new NotificationChannel("ch2", "chname", NotificationManager.IMPORTANCE_DEFAULT));
                    }
                    builder = new NotificationCompat.Builder(context, "ch2");
                } else {
                    builder = new NotificationCompat.Builder(context);
                }

                Intent intent1 = new Intent(context, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(
                        context, 101, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

                builder.setAutoCancel(true);
                builder.setContentIntent(pendingIntent);
                builder.setContentTitle(title);
                builder.setContentText(control + " " + data);
                builder.setSmallIcon(R.drawable.d1);
                Notification noti = builder.build();
                manager.notify(1, noti);
            }

        }

    }; // end BroadcastReceiver


}