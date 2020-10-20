package com.example.p287;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Fragment3 extends Fragment {
    public Fragment3(){}

    SupportMapFragment supportMapFragment;
    GoogleMap gmap;

    TextView textView;
    LocationManager locationManager;
    Fragment map;


    @SuppressLint("MissingPermission")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewGroup = null;
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_2,container,false);
        textView = viewGroup.findViewById(R.id.textView);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(GoogleMap googleMap) {
                gmap = googleMap;

                gmap.setMyLocationEnabled(true); //자기 위치로 가는 버튼 추가(기능도 있음!)
                LatLng latlng = new LatLng(33.354124, 126.568358);
                gmap.addMarker(
                        new MarkerOptions().position(latlng).title("제주도")
                );
                gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 10)); //10은 줌 레벨

            }
        }); //지도를 뿌리겠다..


        //Location
        // 권한이 허용되어 내려오면 진행
        MyLocation myLocation = new MyLocation();
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, //GPS,NETWORK,PASSIVE 3종류가 있다
                1, // 이 시간마다 갱신
                0, // 조금만 움직(미터)여도 좌표를 받는다
                myLocation // 여기서 받는다
        );

        return inflater.inflate(R.layout.fragment_3, container, false);
    } // end onCreatView



    class MyLocation implements LocationListener {

        //위치가 바뀌면 자동으로 좌표를 불러들인다
        @Override
        public void onLocationChanged(@NonNull Location location) {
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            textView.setText(lat+" "+lon);

            LatLng latlng = new LatLng(lat, lon);
//            gmap.addMarker(
//                    new MarkerOptions().position(latlng).title("My Point")
//            );
            //gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 12));
        }
    }


//    //앱 정지 시 (자기위치를 안 추적한다)
//    @SuppressLint("MissingPermission") //빨간색전등 눌러서 퍼미션은 이미 확인했으니 여기서 확인받지않는다!
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if(gmap != null){
//            gmap.setMyLocationEnabled(false);
//        }
//    }
//
//    //앱 실행 다시실행 시
//    @SuppressLint("MissingPermission")
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(gmap != null){
//            gmap.setMyLocationEnabled(true);
//        }
//    }


}
