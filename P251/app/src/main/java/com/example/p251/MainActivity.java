package com.example.p251;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//Activity간 전환하기
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //화면 SecondActivity을 띄운다
    public void ckbt(View v){
        Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
        intent.putExtra("data", 100); //데이터를 담아서 다음 액티비티로 전달
        startActivity(intent);
    }

}