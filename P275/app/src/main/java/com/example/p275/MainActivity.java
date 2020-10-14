package com.example.p275;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("[TEST]", "onCreate");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("[TEST]", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreState();
        Log.d("[TEST]", "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("[TEST]", "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveState();
        Log.d("[TEST]", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("[TEST]", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("[TEST]", "onDestroy");
    }


    //토스트로 저장 된 날자를 불러온다
    public void restoreState(){
        sp = getSharedPreferences("st", Activity.MODE_PRIVATE);
        if(sp != null && sp.contains("date")){
            String result = sp.getString("date","");
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }
    }

    //날자를 저장한다
    public void saveState(){
        sp = getSharedPreferences("st", Activity.MODE_PRIVATE); // Activity.MODE_PRIVATE는 이 앱만 볼 수 있다
        SharedPreferences.Editor editor = sp.edit();
        Date d = new Date();
        editor.putString("date", d.toString());
        editor.commit();
    }


}