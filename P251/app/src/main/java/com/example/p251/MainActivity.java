package com.example.p251;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

//Activity간 전환하기
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String [] permisstions = {
                Manifest.permission.CALL_PHONE
        };
        //위 배열안에 있는 허용들을 요청한다 (101은 요청)
        ActivityCompat.requestPermissions(this,permisstions,101);
    }

    //화면 SecondActivity을 띄운다
    public void ckbt(View v){
        Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
        intent.putExtra("data", 100); //데이터를 담아서 다음 액티비티로 전달
        intent.putExtra("str", "String Data");

        startActivity(intent);
    }


}