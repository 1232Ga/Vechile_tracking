package com.example.vts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.vts.util.GPSTracker;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener, GoogleMap.OnCameraMoveListener, GoogleMap.OnCameraMoveStartedListener {
    private GoogleMap mMap;

    private MarkerOptions place1, place2, place3;
    GPSTracker gpsTracker;
    double mylat, mylom;
    LatLng Delhi, Noida, GraeterNoida,destinationPoint,destinationPoint1,destinationPoint2,destinationPoint3 ;
    Marker mCurrLocationMarker=null;
    String a,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        gpsTracker = new GPSTracker(MainActivity.this);

        mylom = gpsTracker.getLongitude();
        mylat = gpsTracker.getLatitude();

        // place1 = new MarkerOptions().position(Noida).title("My Location");


        Noida = new LatLng(mylat, mylom);
        GraeterNoida = new LatLng(28.474388, 77.503990);
        Delhi = new LatLng(28.984644, 77.705956);






        place1 = new MarkerOptions().position(new LatLng(mylat, mylom)).title("My Location").icon(BitmapFromVector(getApplicationContext(),R.drawable.ic_directions_bus_24));
        place2 = new MarkerOptions().position(Delhi).title("Destination");
        place3 = new MarkerOptions().position(GraeterNoida).title("Greate Noida");

        place1.draggable(true);


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
    }
    private BitmapDescriptor BitmapFromVector(Context context, int vectorResId) {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);

        // below line is use to set bounds to our vector drawable.
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnCameraMoveListener(this);
        mMap.setOnCameraMoveStartedListener(this);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Noida, 10));


         destinationPoint = getDestinationPoint(new LatLng(mylat, mylom), 90, 1);
         destinationPoint1 = getDestinationPoint1(new LatLng(28.984644, 77.705956), 90, 0);


        destinationPoint2 = getDestinationPoint(new LatLng(mylat, mylom), -90, 1);
        destinationPoint3 = getDestinationPoint1(new LatLng(28.984644, 77.705956), -90, 0);
        //NewLatlong=new LatLng(jj);
        System.out.println("jkjjk"+destinationPoint);
        System.out.println("kljjkjk"+Noida);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        System.out.println("jjhhgjgh"+location);
        mMap.addMarker(place1);
        LatLng coordinate = new LatLng(mylat, mylom); //Store these lat lng values somewhere. These should be constant.
        CameraUpdate locations = CameraUpdateFactory.newLatLngZoom(
                coordinate, 11);
        mMap.animateCamera(locations);
        location.getLatitude();
        location.getLongitude();
        LatLng latLng = new LatLng(mylat, mylom);
        String text="my current location is"+"lat: "+location.getLatitude()+"long: "+location.getLongitude();
        //TextView text1=(TextView) findViewById(R.id.textView1);
        //text1.setText(text+"");
        if(mCurrLocationMarker!=null){
            mCurrLocationMarker.setPosition(latLng);
        }else{
            mCurrLocationMarker = mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title("I am here"));
        }
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();


    }

    @Override
    public void onCameraMove() {
        mMap.addMarker(place1);
        mMap.addMarker(place2);


        place1.draggable(true);
        GPSTracker gpsTracker = new GPSTracker(MainActivity.this);
        System.out.println("lskdjflskjdf__ " + gpsTracker.getLongitude() + "  " + gpsTracker.getLatitude());
        mMap.addPolyline((new PolylineOptions()).add(Noida,Delhi).
                width(18)
                .color(Color.GREEN)
                .geodesic(true));

        mMap.addPolyline((new PolylineOptions()).add(destinationPoint,destinationPoint1).
                width(59)
                .color(getResources().getColor(R.color.lighttransparent))
                .geodesic(true));

        mMap.addPolyline((new PolylineOptions()).add(destinationPoint2,destinationPoint3).
                width(59)
                .color(getResources().getColor(R.color.lighttransparent))
                .geodesic(true));
        Toast.makeText(MainActivity.this, gpsTracker.getLongitude()+ "  "+ gpsTracker.getLatitude(), Toast.LENGTH_SHORT).show();
//        mMap.addPolyline((new PolylineOptions()).add(Noida,Delhi).
//                width(28)
//                .color(Color.RED)
//                .geodesic(true));

        List<PatternItem> dashedPattern = Arrays.asList(new Dash(60), new Gap(60));
        mMap.addPolyline(new PolylineOptions()
                .add(Noida,Delhi)
                .width(8)
                .pattern(dashedPattern)
                .color(Color.BLUE));


    }



    private LatLng getDestinationPoint(LatLng source, double brng, double dist) {
        dist = dist / 6371;
        brng = Math.toRadians(brng);

        double lat1 = Math.toRadians(source.latitude), lon1 = Math.toRadians(source.longitude);
        double lat2 = Math.asin(Math.sin(lat1) * Math.cos(dist) +
                Math.cos(lat1) * Math.sin(dist) * Math.cos(brng));
        double lon2 = lon1 + Math.atan2(Math.sin(brng) * Math.sin(dist) *
                        Math.cos(lat1),
                Math.cos(dist) - Math.sin(lat1) *
                        Math.sin(lat2));
        if (Double.isNaN(lat2) || Double.isNaN(lon2)) {
            return null;
        }
        return new LatLng(Math.toDegrees(lat2), Math.toDegrees(lon2));
    }
    private LatLng getDestinationPoint1(LatLng source, double brng, double dist) {
        dist = dist / 6371;
        brng = Math.toRadians(brng);

        double lat1 = Math.toRadians(source.latitude), lon1 = Math.toRadians(source.longitude);
        double lat2 = Math.asin(Math.sin(lat1) * Math.cos(dist) +
                Math.cos(lat1) * Math.sin(dist) * Math.cos(brng));
        double lon2 = lon1 + Math.atan2(Math.sin(brng) * Math.sin(dist) *
                        Math.cos(lat1),
                Math.cos(dist) - Math.sin(lat1) *
                        Math.sin(lat2));
        if (Double.isNaN(lat2) || Double.isNaN(lon2)) {
            return null;
        }
        return new LatLng(Math.toDegrees(lat2), Math.toDegrees(lon2));
    }










    @Override
    public void onCameraMoveStarted(int i) {
        mMap.addMarker(place1);
        GPSTracker  gpsTracker = new GPSTracker(MainActivity.this);
        System.out.println("lskdjflskjdf__ "+ gpsTracker.getLongitude()+ "  "+ gpsTracker.getLatitude());
      //  Toast.makeText(MainActivity.this, gpsTracker.getLongitude()+ "  "+ gpsTracker.getLatitude(), Toast.LENGTH_SHORT).show();

    }
}