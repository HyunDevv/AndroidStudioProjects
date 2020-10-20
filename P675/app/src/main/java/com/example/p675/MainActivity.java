package com.example.p675;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    SupportMapFragment supportMapFragment;
    GoogleMap gmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                    gmap = googleMap;
                    LatLng latlng = new LatLng(33.354124, 126.568358);
                    gmap.addMarker(
                        new MarkerOptions().position(latlng).title("제주도")
                    );
                    gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 10)); //10은 줌 레벨

            }
        }); //지도를 뿌리겠다..
    }

    public void ck1(View v){
        LatLng latlng = new LatLng(35.101809, 129.117550);
        gmap.addMarker(
                new MarkerOptions().position(latlng).title("부산").snippet("xxx").icon(BitmapDescriptorFactory.fromResource((R.drawable.d1)))
        );
        gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 10)); //10은 줌 레벨

    }


    public void ck2(View v){
        LatLng latlng = new LatLng(36.797558, 127.220834);
        gmap.addMarker(
                new MarkerOptions().position(latlng).title("천안").snippet("xxx")
        );
        gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 10)); //10은 줌 레벨
    }

}