package com.example.p489;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button, button2;
    SeekBar seekBar;
    TextView textView;
    ImageView imageView;
    MyAsynch myAsynch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(100);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        button2.setEnabled(false); // 시작,멈춤 버튼 중 시작만 활성화
    }

    public void ckbt1(View v){
        myAsynch = new MyAsynch();
        myAsynch.execute(100); //이 함수로 asynch를 실행한다
    }

    public void ckbt2(View v){
        myAsynch.cancel(true);
        myAsynch.onCancelled();
    }

    //                               <  시작  , 중간  ,  결과 >
    class MyAsynch extends AsyncTask<Integer,Integer,String>{

        //스레드 시작전 실행 되는 함수로 실행시 시작 비활성화, 멈춤 활성화
        @Override
        protected void onPreExecute() {
            button.setEnabled(false);
            button2.setEnabled(true);
        }

        //스레드의 run과 같다, 스레드 시작
        @Override
        protected String doInBackground(Integer... integers) {
            int a = integers[0].intValue();
            int sum = 0;
            for(int i=0;i<=a;i++){
                if(isCancelled() == true){
                    break;
                }
                sum += i;
                publishProgress(i);  //Integer i 값을 onProgressUpdate한테 던진다
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "result: " +sum;
        }

        //스레드 도중
        @Override
        protected void onProgressUpdate(Integer... values) {
            int i = values[0].intValue();
            seekBar.setProgress(i);
            if(i<=30){
                imageView.setImageResource(R.drawable.down);
            }else if(i <= 70){
                imageView.setImageResource(R.drawable.middle);
            }else if(i <= 100){
                imageView.setImageResource(R.drawable.up);
            }
        }

        //스레드가 끝나면
        //스레스 시작전 실행 되는 함수로 실행시 시작 활성화, 멈춤 비활성화
        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);
            button.setEnabled(true);
            button2.setEnabled(false);
        }

        // 취소시 시작 활성화, 멈춤 비활성화
        @Override
        protected void onCancelled() {
            seekBar.setProgress(0);
            textView.setText("");
            imageView.setImageResource(R.drawable.ic_launcher_background);
            button.setEnabled(true);
            button2.setEnabled(false);
        }
    }

}