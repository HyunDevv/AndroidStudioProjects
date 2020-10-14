package com.example.p251;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        int result = intent.getIntExtra("data",0); //데이터를 받는다 default는 0으로 설정
        Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();

    }
}