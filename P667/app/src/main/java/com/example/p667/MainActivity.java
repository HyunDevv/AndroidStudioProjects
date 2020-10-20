package com.example.p667;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        //권한 설정
        String [] permission = {
                Manifest.permission.ACCESS_FINE_LOCATION
        };
        ActivityCompat.requestPermissions(this,permission,101);
        // LOCATION 권한을 허용하지 않으면 앱을 종료시킨다
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED){
            Toast.makeText(this, "Finish", Toast.LENGTH_SHORT).show();
            finish();
        }

        MyLocation myLocation = new MyLocation();
        // 권한이 허용되어 내려오면 진행
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, //GPS,NETWORK,PASSIVE 3종류가 있다
                1, // 이 시간마다 갱신
                0, // 조금만 움직(미터)여도 좌표를 받는다
                myLocation // 여기서 받는다

        );
    }


    public void ck(View v){
//        startMyLocation();
    }

//    //버튼을 눌렀을 때 좌표를 받는 함수
//    private void startMyLocation() {
//        Location location = null;
//        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED){
//            Toast.makeText(this, "Finish", Toast.LENGTH_SHORT).show();
//            finish();
//        }
//        location = locationManager.getLastKnownLocation(
//                LocationManager.GPS_PROVIDER
//        );
//        if(location != null){
//            double lat = location.getLatitude();
//            double lon = location.getLongitude();
//            textView.setText(lat+" " +lon);
//        }
//    }


    class MyLocation implements LocationListener{

        //위치가 바뀌면 자동으로 좌표를 불러들인다
        @Override
        public void onLocationChanged(@NonNull Location location) {
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            textView.setText(lat+" "+lon);
        }
    }

}