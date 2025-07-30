package com.example.vts.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import com.example.vts.LiveTracking.DeviceLocation.DeviceGetset;
import com.example.vts.LiveTracking.DeviceLocation.DeviceLatestInterface;
import com.example.vts.LiveTracking.DeviceLocation.DeviceLocationApi;
import com.example.vts.LiveTracking.DeviceLocation.GetDeviceLatestLocation;
import com.example.vts.LiveTracking.DeviceLocation.ResultData;
import com.example.vts.R;
import com.example.vts.base.LocationUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovingCarActivity extends AppCompatActivity  {
    private GoogleMap googleMapHomeFrag;
    private double[] latLng = new double[2];

    LatLng driverLatLng;
    Context context;
    private Marker marker;
    private LocationUtil locationUtilObj;
    private final int REQUEST_CODE_PERMISSION_MULTIPLE = 123;
    private boolean isDeninedRTPs = true;       // initially true to prevent anim(2)
    private boolean showRationaleRTPs = false;
    private float start_rotation;
    Thread thread;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_move);
         context=MovingCarActivity.this;
        checkAndRequestRunTimePermissions();
       // getCurrentLocation();
        initMap();
        thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!thread.isInterrupted()) {
                        Thread.sleep(10000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                              //  locat();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        thread.start();


        //locat();

    }

//    private void locat() {
//        DeviceLocationApi.getRetrofitInstance(context).create(DeviceLatestInterface.class).registration2().enqueue(new Callback<DeviceGetset>() {
//            @Override
//            public void onResponse(Call<DeviceGetset> call, Response<DeviceGetset> response) {
//                System.out.println("ghfghfffgfgf"+response.body().getResultData());
//                ResultData data=response.body().getResultData();
//                System.out.println("hjggjggjg"+data);
//                GetDeviceLatestLocation getDeviceLatestLocation=data.getGetDeviceLatestLocation();
//                System.out.println("jhhjhhjhgj"+getDeviceLatestLocation);
//                Double lat=getDeviceLatestLocation.getLatitude();
//                Double longi=getDeviceLatestLocation.getLongitude();
//                String la= String.valueOf(lat);
//                String lon= String.valueOf(longi);
//                System.out.println("juhjhjhghgg"+la+"    "+lon);
//                Bikeloac(lat,longi);
//            }
//
//            @Override
//            public void onFailure(Call<DeviceGetset> call, Throwable t) {
//
//            }
//        });
//    }

    LatLng firstselected,secondselected;
    List<LatLng> locationsecondarray = new ArrayList<>();
    private void Bikeloac(double lati,double lo) {
        locationsecondarray.add(new LatLng(lati,lo));





        latLng[0] = lati;
        latLng[1] = lo;

        System.out.println("jhjjgjh"+latLng[0]+"    "+latLng[1]);
        if(marker==null){
            marker = googleMapHomeFrag.addMarker(new MarkerOptions()
                    .position(new LatLng(latLng[0], latLng[1]))
                    .title("My Car")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.redcar)));
            googleMapHomeFrag.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng[0], latLng[1]), 18.0f));
        }

        if ((latLng[0] != -1 && latLng[0] != 0) && (latLng[1] != -1 && latLng[1] != 0)) {
            if (marker != null) {
                Location temp = new Location(LocationManager.GPS_PROVIDER);
                temp.setLatitude(lati);
                temp.setLongitude(lo);
                moveVechile(marker, temp);
                if(locationsecondarray.size() ==2){
                    System.out.println("kjhhjjj"+Float.parseFloat(String.valueOf( bearingBetweenLocations(locationsecondarray.get(0),locationsecondarray.get(1)))));
                    rotateMarker(marker,Float.parseFloat(String.valueOf( bearingBetweenLocations(locationsecondarray.get(0),locationsecondarray.get(1)))), start_rotation);
                }

            }
            driverLatLng = new LatLng(latLng[0], latLng[1]);
            if(locationsecondarray.size() ==2){
                firstselected = locationsecondarray.get(0);
                secondselected = locationsecondarray.get(1);
                locationsecondarray.clear();
                locationsecondarray.add(secondselected);
            }
        } else {
            Toast.makeText(this, "Location Not Found", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //locat();
       // getCurrentLocation();
//        if (locationUtilObj != null) {
//            locationUtilObj.checkLocationSettings();
//            locationUtilObj.restart_location_update();
//        }
    }

    private void initMap() {
        SupportMapFragment mSupportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(GoogleMap googleMap) {
                if (googleMap != null) {
                    googleMapHomeFrag = googleMap;
                    googleMapHomeFrag.getUiSettings().setAllGesturesEnabled(true);
                    googleMapHomeFrag.getUiSettings().setScrollGesturesEnabled(true);
                    googleMapHomeFrag.getUiSettings().setCompassEnabled(false);
                    googleMapHomeFrag.getUiSettings().setMapToolbarEnabled(false);


                    if(driverLatLng!=null){
                        if (googleMapHomeFrag != null) {
                            googleMapHomeFrag.moveCamera(CameraUpdateFactory.newLatLngZoom(driverLatLng, 18.0f));
                            googleMapHomeFrag.getUiSettings().setZoomControlsEnabled(true);
                        }
                    }
                }
            }
        });
    }


    private void checkAndRequestRunTimePermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            // Marshmallow+

            if (this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_CODE_PERMISSION_MULTIPLE);

            }
        }


        onRunTimePermissionGranted();
    }
    private void onRunTimePermissionGranted() {

        isDeninedRTPs = false;
      //  getCurrentLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSION_MULTIPLE) {
            if (grantResults.length > 0) {
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        isDeninedRTPs = true;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            showRationaleRTPs = shouldShowRequestPermissionRationale(permission);
                        }
                        break;
                    }

                }
                onRunTimePermissionDenied();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private void onRunTimePermissionDenied() {
        if (isDeninedRTPs) {
            if (!showRationaleRTPs) {
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                            REQUEST_CODE_PERMISSION_MULTIPLE);
                }
            }
        } else {
            onRunTimePermissionGranted();
        }
    }

    public void moveVechile(final Marker myMarker, final Location finalPosition) {
        final LatLng startPosition = myMarker.getPosition();
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        final android.view.animation.Interpolator interpolator = new AccelerateDecelerateInterpolator();
        final float durationInMs = 3000;
        final boolean hideMarker = false;
        handler.post(new Runnable() {
            long elapsed;
            float t;
            float v;

            @Override
            public void run() {
                elapsed = SystemClock.uptimeMillis() - start;
                t = elapsed / durationInMs;
                v = interpolator.getInterpolation(t);
                LatLng currentPosition = new LatLng(
                        startPosition.latitude * (1 - t) + (finalPosition.getLatitude()) * t,
                        startPosition.longitude * (1 - t) + (finalPosition.getLongitude()) * t);
                myMarker.setPosition(currentPosition);
                if (t < 1) {
                    handler.postDelayed(this, 16);
                } else {
                    if (hideMarker) {
                        myMarker.setVisible(false);
                    } else {
                        myMarker.setVisible(true);
                    }
                }
            }
        });
    }
    public void rotateMarker(final Marker marker, final float toRotation, final float st) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        final float startRotation = marker.getRotation();
        final long duration = 1555;
        final Interpolator interpolator = new LinearInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed / duration);
                float rot = t * toRotation + (1 - t) * startRotation;
                marker.setRotation(-rot > 180 ? rot / 2 : rot);
                start_rotation = -rot > 180 ? rot / 2 : rot;
                if (t < 1.0) {
                    handler.postDelayed(this, 16);
                }
            }
        });
    }


    private double bearingBetweenLocations(LatLng latLng1,LatLng latLng2) {

        double PI = 3.14159;
        double lat1 = latLng1.latitude * PI / 180;
        double long1 = latLng1.longitude * PI / 180;
        double lat2 = latLng2.latitude * PI / 180;
        double long2 = latLng2.longitude * PI / 180;

        double dLon = (long2 - long1);

        double y = Math.sin(dLon) * Math.cos(lat2);
        double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1)
                * Math.cos(lat2) * Math.cos(dLon);

        double brng = Math.atan2(y, x);

        brng = Math.toDegrees(brng);
        brng = (brng + 360) % 360;

        return brng;
    }


}
