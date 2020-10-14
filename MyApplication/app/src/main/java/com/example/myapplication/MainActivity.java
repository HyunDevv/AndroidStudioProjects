package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView himg;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        himg = findViewById(R.id.himg);
        button2 = findViewById(R.id.button2);
    }

    // 이미지를 안 보이게 한다, button2의 텍스트를 바꾼다, Log로 디버그를 찾는다
    public void clickBt(View view){
        himg.setVisibility(View.INVISIBLE);
        button2.setText(R.string.bt_text);
        Log.d("[TEST]", "-----------");
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
    }

//    // 모바일 네이버로 이동
//    // 현재 앱에서 다른 것을 호출할 때 intent를 사용한다
//    public void clickBt2(View view){
//        Intent intent = new Intent(Intent.ACTION_VIEW,
//                Uri.parse("http://m.naver.com"));
//        startActivity(intent);
//    }
//
//    // 전화 거는 곳으로 이동
//    public void clickBt3(View view){
//        Intent intent = new Intent(Intent.ACTION_VIEW,
//                Uri.parse("tel:010-9878-9838"));
//        startActivity(intent);
//    }

    // 한 함수로 버튼들을 제어
    public void clickBts(View view){
        Intent intent = null;

        if(view.getId() == R.id.button2){
            intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://m.naver.com"));
        }else if(view.getId() == R.id.button3){
            intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("tel:010-9878-9838"));
        }
        // 전화를 건다 (함부로 전화를 걸지 않도록 퍼미션을 줘야한다!)
        // 보안인 작업을 실행하려면 AndroidManifest.xml에 추가하고
        // onCreate()에서 허용을 물어보고
        // 여기 함수에서 퍼미션 체크를 해줘야한다.
        else if(view.getId() == R.id.button4){
            intent = new Intent(Intent.ACTION_CALL,
                    Uri.parse("tel:010-9878-9838"));
            if(checkSelfPermission(Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED){
                return;
            }
        }
        startActivity(intent);
    }



}