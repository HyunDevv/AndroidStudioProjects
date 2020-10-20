package com.example.p552;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp; // 앱이 삭제가 되지 않는 한 저장되어 있다

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("login",MODE_PRIVATE); //외부에서 접근하지 못하게 한다
        String status = sp.getString("status","");
        Toast.makeText(this, status, Toast.LENGTH_LONG).show();
    }

    //SharedPreferences에 status를 ok로 저장
    public void ck(View v){
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("status","ok");
        edit.commit();
    }

    //SharedPreferences에 status 삭제
    public void ck2(View v){
        SharedPreferences.Editor edit = sp.edit();
        edit.remove("status");
        edit.commit();
    }

}