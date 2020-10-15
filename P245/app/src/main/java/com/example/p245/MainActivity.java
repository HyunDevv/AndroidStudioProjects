package com.example.p245;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    LinearLayout container;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.container);
    }

    public void bt(View v){
        if(v.getId() == R.id.button){
            container.removeAllViews();
            LayoutInflater inflater =
                    (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.login, container, true);
        }else if(v.getId() == R.id.button2){
            container.removeAllViews();
            LayoutInflater inflater =
                    (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.register, container, true);
        }else if(v.getId() == R.id.button3){
            container.removeAllViews();
            LayoutInflater inflater =
                    (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.info, container, true);
            TextView tv = container.findViewById(R.id.textView2);

        }else if(v.getId() == R.id.button4){
            container.removeAllViews();
            LayoutInflater inflater =
                    (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.list, container, true);
        }
    }


    public void btprogress(View v){
        ProgressDialog progressDialog = null;

        if(v.getId() == R.id.button5){
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setTitle("login ...");
            progressDialog.show();
        }else if(v.getId() == R.id.button6){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("My Dialog");
            builder.setMessage("Regitser?");

            //OK을 누르면 종료
            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Register Success!", Toast.LENGTH_SHORT).show();
                    container.removeAllViews();
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
    }




}