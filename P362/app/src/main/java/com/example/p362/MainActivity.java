package com.example.p362;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;

    BroadcastReceiver broadcastReceiver;

    //어떤 브로드캐스트를 받을지 등록한다
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        //어떤 퍼미션을 등록할건지 (AndroidManifest.xml에 기입되어야 함)
        String permissions [] = {
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS,
                Manifest.permission.RECEIVE_SMS
        };
        ActivityCompat.requestPermissions(this,permissions,101);

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
                        imageView.setImageResource(R.drawable.wifi);
                    }
                    //다 연결 안되있다면
                    else{
                        imageView.setImageResource(R.drawable.nwifi);
                    }
                // 문자를 받으면면 텍스트뷰에 시하기
                }else if(cmd.equals("android.provider.Telephony.SMS_RECEIVED")){
                    Toast.makeText(context, "SMS_RECEIVED", Toast.LENGTH_SHORT).show();
                    Bundle bundle = intent.getExtras();
                    Object [] obj = (Object [])bundle.get("pdus");
                    SmsMessage [] messages = new SmsMessage[obj.length];
                    for(int i=0; i<obj.length; i++){
                        String format = bundle.getString("format");
                        messages[i] = SmsMessage.createFromPdu(
                                (byte[]) obj[i], format);
                    }
                    String msg = "";
                    if(messages != null && messages.length > 0){
                        msg += messages[0].getOriginatingAddress()+"\n";
                        msg += messages[0].getMessageBody().toLowerCase()+"\n";
                        msg += new Date(messages[0].getTimestampMillis()).toString();
                        textView.setText(msg);
                    }
                }

            }
        };

        registerReceiver(broadcastReceiver, intentFilter); //가동시킨다 (오면 받는다)

    } // end onCreate


    //반드시 앱이 끝날 때 브로드캐스트리시버 연결을 끊어줘야한다!!
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }


    public void ckbt(View v){
        if(v.getId() == R.id.button){
            //퍼미션 콜폰을 확인한다
            int check = PermissionChecker.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE);
            if(check == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-9090-9898"));
                startActivity(intent);
            }else{
                Toast.makeText(this, "DENIED", Toast.LENGTH_SHORT).show();
            }
        }else if(v.getId() == R.id.button2){
            //문자를 보낸다  여기도 체크해줘야된다(지금 생략 됨!)
            SmsManager smsManager =
                    SmsManager.getDefault();
            smsManager.sendTextMessage(
                    "tel:010-2323-0432",
                    null,
                    "hi, Man ...",
                    null,
                    null
            );
            Toast.makeText(this, "Send ..OK", Toast.LENGTH_SHORT).show();

        }

    }

}