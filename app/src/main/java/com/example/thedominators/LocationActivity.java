package com.example.thedominators;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationActivity extends FragmentActivity implements OnMapReadyCallback {

    FusedLocationProviderClient fusedLocationProviderClient;
    double lati,longi ;
    private String latii ,longii,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Bundle extras = getIntent().getExtras();
        name = extras.getString("Name");
        latii = extras.getString("Lati");
        longii = extras.getString("Longi");
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        supportMapFragment.getMapAsync(LocationActivity.this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        lati = Double.valueOf(latii);
        longi = Double.valueOf(longii);
        LatLng latLng = new LatLng(lati,longi);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(String.valueOf(name));
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,5));
        googleMap.addMarker(markerOptions);


    }
}