package com.example.p458;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView =  findViewById(R.id.webView);
        //안드로이드에서 제공하는 기본적인 브라우저 기능
        webView.setWebViewClient(new WebViewClient());
        //안드로이드 안에서 브라우저 기능을 불러온다
        WebSettings webSettings = webView.getSettings();
        //자바스크립트 허용
        webSettings.setJavaScriptEnabled(true);
        //인터넷 허용 퍼미션 추가
        String permissions[] = {

        };
    }

    public void ckbt(View v){
        if(v.getId() == R.id.button){
            //인터넷 퍼미션을 추가해야 함!
            webView.loadUrl("http://m.naver.com");
        }else if(v.getId() == R.id.button2){
            webView.loadUrl("http://m.daum.net");
        } //java webproject를 불러온다
        else if(v.getId() == R.id.button3){
            webView.loadUrl("http://192.168.0.28/android");
        }
    }

}