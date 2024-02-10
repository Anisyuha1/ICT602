package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.healthcareapp.databinding.ActivityMapsBinding;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;

import androidx.core.app.ActivityCompat;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    MarkerOptions marker;
    LatLng centerlocation ;


    Vector<MarkerOptions> markerOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        centerlocation = new LatLng(3.0, 101);
        markerOptions = new Vector<>();

        markerOptions.add(new MarkerOptions().title("Hospital Tuanku Fauziah kangar")
                .position(new LatLng(6.440954455881447, 100.19132845955683))
                .snippet("Open everyday")

        );


        markerOptions.add(new MarkerOptions().title("Medication Theraphy Adherence Clinic HTF")
                .position(new LatLng(6.441306720224044, 100.19127510489713))
                .snippet("Open everyday")

        );

        markerOptions.add(new MarkerOptions().title("Jabatan Kecemasan dan Trauma Hospital Tuanku Fauziah")
                .position(new LatLng(6.442042336138119, 100.19146285952581))
                .snippet("Open everyday")

        );


        markerOptions.add(new MarkerOptions().title("Zon E-Healing")
                .position(new LatLng(6.441893080827447, 100.19013248383727))
                .snippet("Open everyday")

        );


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for (MarkerOptions mark : markerOptions) {
            mMap.addMarker(mark);
        }
        enableMyLocation();


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerlocation, 6));
    }


    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        // 1. Check if permissions are granted, if so, enable the my location layer
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
            }
        } else {
            String perms[]= {"android.permission.ACCESS_FINE_LOCATION"};

            ActivityCompat.requestPermissions(this,perms, 200);
        }


    }


}