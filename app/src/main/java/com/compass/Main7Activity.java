package com.compass;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.nucldev.engine.SolarSystemCompassLauncher;
import com.nucldev.engine.utils.DegreesConvertion;
import com.compass.solarsystemcompassapp.R;
import com.google.android.gms.location.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main7Activity extends AppCompatActivity {

    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;
    TextView latTextView, lonTextView;

    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        latTextView = findViewById(R.id.latTextView);
        lonTextView = findViewById(R.id.lonTextView);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    int[] latArr = DegreesConvertion.fromDecimal(location.getLatitude());
                                    int[] longArr = DegreesConvertion.fromDecimal(location.getLongitude());
                                    SolarSystemCompassLauncher.latitude = latArr;
                                    SolarSystemCompassLauncher.longitude = longArr;
                                    String latitude = "Latitude:\n"+latArr[0] + "°" + latArr[1] + "'" + latArr[2] + "\"";
                                    String longitude = "Longitude:\n"+longArr[0] + "°" + longArr[1] + "'" + longArr[2] + "\"";
                                    latTextView.setText(latitude);
                                    lonTextView.setText(longitude);
                                    Runnable tsk = new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent(Main7Activity.this,Main2Activity.class);
                                            startActivity(intent);
                                        }
                                    };
                                    service.schedule(tsk,2,TimeUnit.SECONDS);
                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }


    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            int[] latArr = DegreesConvertion.fromDecimal(mLastLocation.getLatitude());
            int[] longArr = DegreesConvertion.fromDecimal(mLastLocation.getLongitude());
            SolarSystemCompassLauncher.latitude = latArr;
            SolarSystemCompassLauncher.longitude = longArr;
            String latitude = "Latitude:\n"+latArr[0] + "°" + latArr[1] + "'" + latArr[2] + "\"";
            String longitude = "Longitude:\n"+longArr[0] + "°" + longArr[1] + "'" + longArr[2] + "\"";
            latTextView.setText(latitude);
            lonTextView.setText(longitude);
            Runnable tsk = new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Main7Activity.this,Main2Activity.class);
                    startActivity(intent);
                }
            };
            service.schedule(tsk,2,TimeUnit.SECONDS);
        }
    };

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;

    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }else
                onBackPressed();
        }

    }

    @Override
    public void onResume(){
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        service.shutdownNow();
    }
}