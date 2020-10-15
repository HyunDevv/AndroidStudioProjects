package com.example.p217;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;  // 프로그레스바 만들기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar); // 프로그레스바 가져오기
    }

    //안드로이드에서 제공하는 기본 토스트의 위치변경
    public void clickb1(View v){
        Toast t = Toast.makeText(this,"Toast1...",Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();
    }

    // 내가 토스트를 만들어서 사용한다
    public void clickb2(View v){

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.toast_layout));
        TextView tv = view.findViewById(R.id.textView);
        tv.setText("INPUT TEXT");


        Toast t = new Toast(this);
        t.setGravity(Gravity.CENTER,0,0);
        t.setDuration(Toast.LENGTH_LONG);
        t.setView(view);
        t.show();
    }

    //스낵바 사용
    public void clickb3(View v){
        Snackbar.make(v, "Snack Bar", Snackbar.LENGTH_LONG).show();
    }

    // 다이얼로그 사용
    public void clickb4(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("My Dialog");
        builder.setMessage("Are You Exit Now");
        builder.setIcon(R.drawable.b1);

        //OK을 누르면 종료
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        //NO를 누르면 그대로
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //프로그래스 바, 스피닝 프로그래스
    public void btprogress(View v){
        ProgressDialog progressDialog = null;
        
        if(v.getId() == R.id.button5){
            int pdata = progressBar.getProgress();
            progressBar.setProgress(pdata + 1);
        }else if(v.getId() == R.id.button6){
            int pdata = progressBar.getProgress();
            progressBar.setProgress(pdata - 1);
        }else if(v.getId() == R.id.button7){
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setTitle("Downloading ...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }else if(v.getId() == R.id.button8){
            progressDialog.dismiss();
        }
    }





    //뒤로가기를 눌렀을 때 내가 만든 레이아웃을 setview를 해서 dialog를 만든다
    @Override
    public void onBackPressed() {
        //super.onBackPressed();  여기가 없어야 동작한다!

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog,
                (ViewGroup) findViewById(R.id.dialog_layout));


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("My Dialog");
        builder.setMessage("Are You Exit Now");
        builder.setView(view);
        builder.setIcon(R.drawable.b1);

        //OK을 누르면 종료
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        //NO를 누르면 그대로
        builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }


} // end class