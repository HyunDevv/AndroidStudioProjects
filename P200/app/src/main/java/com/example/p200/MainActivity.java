package com.example.p200;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    LinearLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        view = findViewById(R.id.view);
        // 리스너로 이벤트 처리
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //이벤트에 대한 정보를 받아온다
                int action = event.getAction();
                float x = event.getX();
                float y = event.getY();
                if(action == MotionEvent.ACTION_DOWN){
                    print("DOWN:"+x+","+y);
                }else if(action == MotionEvent.ACTION_MOVE){
                    print("MOVE:"+x+","+y);
                }else if(action == MotionEvent.ACTION_UP){
                    print("UP:"+x+","+y);
                }

                return true;
            }
        });
    } // onCreate end


    public void print(String str){
        textView.setText(str);
    }

//    //키가 눌렸을 때 실행하는 함수
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode == KeyEvent.KEYCODE_BACK){
//            Toast.makeText(this, ""+"BACK KEY PRESSED", Toast.LENGTH_SHORT).show();
//        }
//
//        return true;
//    }


    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        Toast.makeText(this, ""+"BACK KEY PRESSED", Toast.LENGTH_SHORT).show();
        finish();
    }
}