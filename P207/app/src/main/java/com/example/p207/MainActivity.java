package com.example.p207;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText et;
    TextView textView;

    String str;

    //앱이 처음 실행될 때
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show("onCreate");
        Log.d("[TEST]", "onCreate");

        button = findViewById(R.id.button);
        et = findViewById(R.id.et);
        textView = findViewById(R.id.textView2);

        //버튼 클릭 시 텍스트에디터의 텍스트를 가져온다
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = et.getText().toString();  //.getText()는 Editable자료형이라 형변환을 해주어야한다
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });

    }

    //화면 방향 변환 시 이벤트 처리 (AndroidManifest.xml을 수정해야한다)
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_main);
            Toast.makeText(this, "LANDSCAPE", Toast.LENGTH_SHORT).show();
        }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_main);
            Toast.makeText(this, "PORTRAIT", Toast.LENGTH_SHORT).show();
        }
    }

    //앱이 보여질 때
    @Override
    protected void onStart() {
        super.onStart();
        show("onStart");
        Log.d("[TEST]", "onStart");
    }

    //홈버튼 눌렀을 때 or 화면에서 사라질 때 스탑된다
    @Override
    protected void onStop() {
        super.onStop();
        show("onStop");
        Log.d("[TEST]", "onStop");
    }

    //앱이 꺼질 때 실행 된다
    @Override
    protected void onDestroy() {
        super.onDestroy();
        show("onDestroy");
        Log.d("[TEST]", "onDestroy");
    }

    public void show(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}