package com.example.ws;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tx_id, tx_pwd;
    HttpAsync httpAsync;
    ActionBar actionBar;
    BroadcastReceiver broadcastReceiver;
    //어떤 브로드캐스트를 받을지 등록한다
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String [] permission = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        ActivityCompat.requestPermissions(this,
                permission, 101);

        tx_id = findViewById(R.id.tx_id);
        tx_pwd = findViewById(R.id.tx_pwd);

        //액션바 삭제
        actionBar = getSupportActionBar();
        actionBar.hide();


        //브로드캐스트의 사용
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String cmd = intent.getAction();
                ConnectivityManager cm = null;
                NetworkInfo mobile = null;
                NetworkInfo wifi = null;

                //네트워크의 변경 상태에 따라 이미지뷰를 바꾼다
                //브로드캐스트를 까보니..네트워크가 변경 되었다면
                if(cmd.equals("android.net.conn.CONNECTIVITY_CHANGE")){
                    //현재 네트워크 상태를 받아온다
                    cm = (ConnectivityManager) context.getSystemService(
                            Context.CONNECTIVITY_SERVICE
                    );
                    mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                    wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                    //네트워크가 연결 되었다면
                    if(mobile != null && mobile.isConnected()){

                    }
                    //와이파이가 연결 되었다면
                    else if(wifi != null && wifi.isConnected()){

                    }
                    //다 연결 안되있다면
                    else{
                        Toast.makeText(context, "Check your Internet Connection!!", Toast.LENGTH_LONG).show();
                    }

                }

            }
        };

        registerReceiver(broadcastReceiver, intentFilter); //가동시킨다 (오면 받는다)



    }


    //반드시 앱이 끝날 때 브로드캐스트리시버 연결을 끊어줘야한다!!
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }



    public void ck(View v){
        String id = tx_id.getText().toString();
        String pwd = tx_pwd.getText().toString();
        String url = "http://192.168.0.103/android/login.jsp";
        url += "?id="+id+"&pwd="+pwd;
        //String result = HttpConnect.getString(url); <- 서브스레드 안에서 해야한다!!
        httpAsync = new HttpAsync();
        httpAsync.execute(url);
    }

    class HttpAsync extends AsyncTask<String,String,String>{

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Login ...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            //Manifest 수정 필요!
            String url = strings[0];
            String result = HttpConnect.getString(url);
            return result;
        }

        @Override
        protected void onProgressUpdate(String... values) {

        }

        @Override
        protected void onPostExecute(String s) {
            //Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            String s1 = s.trim();

            if(s1.equals("1")){
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                //intent.putExtra("data", 100); //데이터를 담아서 다음 액티비티로 전달
                //intent.putExtra("str", "String Data");

                startActivity(intent);
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Login fail");
                builder.setMessage("Check your ID or PWD!");

                //OK을 누르면 종료
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }

    }

}