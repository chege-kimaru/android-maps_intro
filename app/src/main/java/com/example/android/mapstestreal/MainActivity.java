package com.example.android.mapstestreal;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    boolean mapReady = false;

    MarkerOptions renton;
    MarkerOptions kent;

    static final CameraPosition SEATTLE = CameraPosition.builder()
            .target(new LatLng(47.654, -122.349))
            .zoom(17)
            .bearing(0)
            .tilt(45)
            .build();

    static final CameraPosition TOKYO = CameraPosition.builder()
            .target(new LatLng(35.654, 139.349))
            .zoom(17)
            .bearing(90)
            .tilt(45)
            .build();

    static final CameraPosition DUBLIN = CameraPosition.builder()
            .target(new LatLng(53.3478, -6.2597))
            .zoom(17)
            .bearing(90)
            .tilt(45)
            .build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });

        Button btnSatellite = (Button) findViewById(R.id.btnSatellite);
        btnSatellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });

        Button btnHybrid = (Button) findViewById(R.id.btnHybrid);
        btnHybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });


        Button btnSeattle = (Button) findViewById(R.id.btnSeattle);
        btnSeattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    flyTo(SEATTLE);
            }
        });

        Button btnTokyo = (Button) findViewById(R.id.btnTokyo);
        btnTokyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    flyTo(TOKYO);
            }
        });

        Button btnDublin = (Button) findViewById(R.id.btnDublin);
        btnDublin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    flyTo(DUBLIN);
            }
        });

        renton = new MarkerOptions()
                .position(new LatLng(47.88888, -122.12845))
                .title("Renton")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));

        kent = new MarkerOptions()
                .position(new LatLng(47.3867, -122.25812))
                .title("Kent");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }



    private void flyTo(CameraPosition target) {
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(target), 1000, null);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady = true;
        mMap = googleMap;
        mMap.addMarker(kent);
        mMap.addMarker(renton);
        LatLng newyork = new LatLng(40.7484, -73.9857);
        CameraPosition target = CameraPosition.builder().target(newyork).zoom(14).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(target));
        mMap.addPolyline(new PolylineOptions().geodesic(true)
                .add(new LatLng(47.389, -122.26))
                .add(new LatLng(46.00, -122.26))
                .add(new LatLng(47.389, -119.33)));
        mMap.addCircle(new CircleOptions()
                .center(new LatLng(46.00, -122.26))
                .radius(5000)
                .strokeColor(Color.GREEN)
                .fillColor(Color.argb(64, 0, 255, 0)));
    }
}
