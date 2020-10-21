package com.example.p676;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

// 메인엑티비티에서 지도를 띄운다!
public class MainActivity extends AppCompatActivity {

    SupportMapFragment supportMapFragment;
    GoogleMap gmap;

    TextView textView;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide(); // 액션바 없에기

        //권한 설정 요청
        String [] permission = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION //자기위치버튼 만들 때 있어야 함!
        };
        ActivityCompat.requestPermissions(this,permission,101);

        //supportMapFragment를 사용해 지도를 띄운다
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                gmap = googleMap;

                // LOCATION 권한을 허용하지 않으면 앱을 종료시킨다
                if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED
                        || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED){
                    return;
                }

                gmap.setMyLocationEnabled(true); //자기 위치로 가는 버튼 추가
                LatLng latlng = new LatLng(33.354124, 126.568358); //제주도 좌표
                gmap.addMarker(
                        new MarkerOptions().position(latlng).title("제주도")
                );
                gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 10)); //10은 줌 레벨

            }
        }); //지도를 뿌리겠다..


        //Location
        textView = findViewById(R.id.textView);


        // 권한이 허용되어 내려오면 아래 진행
        MyLocation myLocation = new MyLocation();
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, //GPS,NETWORK,PASSIVE 3종류가 있다
                1, // 이 시간마다 갱신
                0, // 조금만 움직(미터)여도 좌표를 받는다
                myLocation // 여기서 받는다
        );

    } //end onCreate


    class MyLocation implements LocationListener {

        //위치가 바뀌면 자동으로 좌표를 불러들인다
        @Override
        public void onLocationChanged(@NonNull Location location) {
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            textView.setText(lat+" "+lon);

            LatLng latlng = new LatLng(lat, lon);
//            //마커를 찍는다다
//           gmap.addMarker(
//                    new MarkerOptions().position(latlng).title("My Point")
//            );
            //gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 12));
        }
    }

    //앱 정지 시 (자기위치를 안 추적한다)
    @SuppressLint("MissingPermission") //빨간색전등 눌러서 퍼미션은 이미 확인했으니 여기서 확인받지않는다!
    @Override
    protected void onPause() {
        super.onPause();
        if(gmap != null){
            gmap.setMyLocationEnabled(false);
        }
    }

    //앱 실행 다시실행 시
    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {
        super.onResume();
        if(gmap != null){
            gmap.setMyLocationEnabled(true);
        }
    }
}