package com.example.p475;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView1, textView2;
    MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        myHandler = new MyHandler();

        Thread t = new Thread(new MyThread());
        t.start();
}


    class MyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData();
            int data1 =  bundle.getInt("data1",0);
            int data2 =  bundle.getInt("data2",0);
            textView1.setText(data1+"KM");
            textView2.setText(data2+"rpm");
        }
    }



    class MyThread implements Runnable{

        @Override
        public void run() {
            Random random = new Random();
            int rd1=0;
            int rd2=0;

            while(true){
                try {
                    rd1 = random.nextInt(200);
                    rd2 = random.nextInt(5000);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = myHandler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("data1",rd1);
                bundle.putInt("data2",rd2);
                message.setData(bundle);
                myHandler.sendMessage(message);


            }
        }
    }




}