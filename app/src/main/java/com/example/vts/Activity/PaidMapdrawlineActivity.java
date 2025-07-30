package com.example.vts.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vts.Activity.EndTripPack.EndTripInterface;
import com.example.vts.Activity.EndTripPack.TripEndgetset;
import com.example.vts.Activity.EravanDetailsIdBase.EravanDetailsIdbase;
import com.example.vts.Activity.EravanDetailsIdBase.EravanIdDetailsbseApi;
import com.example.vts.Activity.EravanDetailsIdBase.EravanIdgetSet;
import com.example.vts.Activity.EravanaIdPAck.EravanaIdApi;
import com.example.vts.Activity.EravanaIdPAck.EravanaIdInterface;
import com.example.vts.Activity.EravanaIdPAck.EravanaListAdapter;
import com.example.vts.Activity.EravanaIdPAck.Eravanaidgetset;
import com.example.vts.Activity.UserDelete.UserDeletegetset;
import com.example.vts.DirectionsJSONParser;
import com.example.vts.LiveTracking.DeviceLocation.DeviceGetset;
import com.example.vts.LiveTracking.DeviceLocation.DeviceLatestInterface;
import com.example.vts.LiveTracking.DeviceLocation.DeviceLocationApi;
import com.example.vts.LiveTracking.DeviceLocation.GetDeviceLatestLocation;
import com.example.vts.LiveTracking.DeviceLocation.Livedevicegetset;
import com.example.vts.LiveTracking.DeviceLocation.ResultData;
import com.example.vts.R;
import com.example.vts.TriplistPack.TripListApi;
import com.example.vts.base.LocationUtil;
import com.example.vts.util.DirectionsJSONParser2;
import com.example.vts.util.GPSTracker;
import com.example.vts.util.GeoCodeLocation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaidMapdrawlineActivity extends AppCompatActivity  {
    private LatLng mDestination;
    private LatLng mOrigin;
    private Polyline mPolyline;
    private Polyline mPolyline1;
    private Polyline mPolyline2;
    View mapView;
    String vecuniqueid;
    String vecno,sourcecars,destcar,datecar,lastupadte;
    ArrayList<LatLng> points1 = null;
    ArrayList<LatLng> points = null;
    BottomSheetBehavior mBottomSheetBehavior;
    ImageView cancel_bottomsheet_item;
    TextView vechilenumber,carststus,caravg,cardistance,vechilenumber1,showmoredetaisls,triptime;
    View bottomSheet;
    LatLng locationorigin,locationdest,LatlngBike;
    private GoogleMap googleMapHomeFrag;
    private double[] latLng = new double[2];
    LatLng driverLatLng;
    private Marker marker;
    private LocationUtil locationUtilObj;
    private final int REQUEST_CODE_PERMISSION_MULTIPLE = 123;
    private boolean isDeninedRTPs = true;
    private boolean showRationaleRTPs = false;
    private float start_rotation;
    Thread thread;
    Context context;
    Double speed;
    String siteid,trackvalue="0";
    Button stoptrack,submit_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid_mapdrawline);
        context=PaidMapdrawlineActivity.this;
        siteid=getIntent().getStringExtra("siteid");
        System.out.println("hjgjhghgh"+siteid);
        initMap();
        bottomSheet = findViewById(R.id.Category_sheet);

        stoptrack = findViewById(R.id.stoptrack);
        submit_btn = findViewById(R.id.submit_btn);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        cancel_bottomsheet_item = findViewById(R.id.cancel_bottomsheet_item);
        vechilenumber = findViewById(R.id.vechilenumber);
        showmoredetaisls = findViewById(R.id.showmoredetaisls);
        vechilenumber1 = findViewById(R.id.vechilenumber1);
        triptime = findViewById(R.id.triptime);
        carststus = findViewById(R.id.carststus);
        caravg = findViewById(R.id.caravg);
        cardistance = findViewById(R.id.cardistance);
        cancel_bottomsheet_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit_btn.setVisibility(View.GONE);
                stoptrack.setVisibility(View.VISIBLE);
                locat();
            }
        });
        stoptrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                log();
            }
        });

        initMap();
//        thread = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    while (!thread.isInterrupted()) {
//                        Thread.sleep(10000);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                               // locat();
//                            }
//                        });
//                    }
//                } catch (InterruptedException e) {
//                }
//            }
//        };
//        thread.start();

        showmoredetaisls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                //showCustomDialog2();
            }
        });
    }
    public void log() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage("Do you want to finish Trip?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                submit_btn.setVisibility(View.VISIBLE);
                stoptrack.setVisibility(View.GONE);
                markecomplete();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog1, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog1.cancel();
                submit_btn.setVisibility(View.GONE);
                stoptrack.setVisibility(View.VISIBLE);
            }
        });
        androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
    }
    private void markecomplete() {
        final ProgressDialog progressDialog = new ProgressDialog(PaidMapdrawlineActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        TripListApi.getRetrofitInstance(context).create(EndTripInterface.class).registration(siteid,"a").enqueue(new Callback<TripEndgetset>() {
            @Override
            public void onResponse(Call<TripEndgetset> call, Response<TripEndgetset> response) {
                if(response.body().getId().length()>0){
                    System.out.println("jkjkhkjhjhjgh");
                    progressDialog.dismiss();
                    Intent intent=new Intent(PaidMapdrawlineActivity.this,MyTripActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(PaidMapdrawlineActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(PaidMapdrawlineActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TripEndgetset> call, Throwable t) {

            }
        });
    }

    TextView vechilenumbers,cardate,sourcecar,destinationcar,lastupadet,vechilenumber2;
//    private void showCustomDialog2() {
//        ViewGroup viewGroup = findViewById(android.R.id.content);
//        View dialogView = LayoutInflater.from(this).inflate(R.layout.showdetailsvechicle, viewGroup, false);
//
//        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
//        builder.setView(dialogView);
//        android.app.AlertDialog alertDialog = builder.create();
//        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        alertDialog.setCancelable(false);
//        alertDialog.show();
//        alertDialog.getWindow().setGravity(Gravity.TOP);
//        vechilenumbers = (TextView) dialogView.findViewById(R.id.vechilenumber);
//       // vechilenumber2 = (TextView) dialogView.findViewById(R.id.vechilenumber1);
//        //cardate = (TextView) dialogView.findViewById(R.id.cardate);
////        sourcecar = (TextView) dialogView.findViewById(R.id.sourcecar);
////        destinationcar = (TextView) dialogView.findViewById(R.id.destinationcar);
//        lastupadet = (TextView) dialogView.findViewById(R.id.lastupadet);
////        ImageView removestaff = (ImageView) dialogView.findViewById(R.id.cancel_bottomsheet_item);
////        removestaff.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                alertDialog.dismiss();
////            }
////        });
//        vechilenumbers.setText(vecno);
//       // vechilenumber2.setText(vecno);
//       // sourcecar.setText(sourcecars);
//       // destinationcar.setText(destcar);
//        //lastupadet.setText("Current Loction:-"+lastupadte);
//        //cardate.setText(datecar);
//
//
//    }

    private void locat() {
        System.out.println("ghfghfghfhgfh"+vecuniqueid);
       DeviceLocationApi.getRetrofitInstance(context).create(DeviceLatestInterface.class).registration2(vecuniqueid).enqueue(new Callback<Livedevicegetset>() {
           @Override
           public void onResponse(Call<Livedevicegetset> call, Response<Livedevicegetset> response) {
               System.out.println("hjggjggjg"+new Gson().toJson(response.body()));
               ResultData data=response.body().getResultData();

               GetDeviceLatestLocation getDeviceLatestLocation=data.getGetDeviceLatestLocation();
               System.out.println("jhhjhhjhgj"+getDeviceLatestLocation);
               Double lat=getDeviceLatestLocation.getLatitude();
               Double longi=getDeviceLatestLocation.getLongitude();
               speed=getDeviceLatestLocation.getSpeed();
               System.out.println("juhjhjhghgg"+speed);
               Bikeloac(lat,longi);
           }

           @Override
           public void onFailure(Call<Livedevicegetset> call, Throwable t) {

           }
       });
    }
    LatLng firstselected,secondselected;
    List<LatLng> locationsecondarray = new ArrayList<>();
    private void Bikeloac(double lati,double lo) {

        locationsecondarray.add(new LatLng(lati,lo));
        latLng[0] = lati;
        latLng[1] = lo;
        LatlngBike=new LatLng(latLng[0],latLng[1]);
        System.out.println("jhjjgjh"+latLng[0]+"    "+latLng[1]);
        if(marker==null){
            marker = googleMapHomeFrag.addMarker(new MarkerOptions()
                    .position(new LatLng(latLng[0], latLng[1]))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.redcar)));
            googleMapHomeFrag.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng[0], latLng[1]), 12.0f));
        }

        if ((latLng[0] != -1 && latLng[0] != 0) && (latLng[1] != -1 && latLng[1] != 0)) {
            if (marker != null) {
                Location temp = new Location(LocationManager.GPS_PROVIDER);
                temp.setLatitude(lati);
                temp.setLongitude(lo);
                System.out.println("kjhhjjj"+temp.getBearing());
                moveVehicle(marker, temp);
                if(locationsecondarray.size() ==2){
                    System.out.println("kjhhjjj"+Float.parseFloat(String.valueOf( bearingBetweenLocations(locationsecondarray.get(0),locationsecondarray.get(1)))));
                    rotateMarker(marker,Float.parseFloat(String.valueOf( bearingBetweenLocations(locationsecondarray.get(0),locationsecondarray.get(1)))), start_rotation);
                }
                //rotateMarker(marker, temp.getBearing(), start_rotation);

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
        if (locationUtilObj != null) {
            locationUtilObj.checkLocationSettings();
            locationUtilObj.restart_location_update();
        }
    }


    private String downloadUrl5(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception on download", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
    private void VehicleRoute() {
        System.out.println("kjkhhjhghj");
        // Getting URL to the Google Directions API
        String url = getDirectionsUrl5(LatlngBike, mDestination);
        System.out.println("jkhjjhj"+url);
        DownloadTask5 downloadTask = new DownloadTask5();

        // Start downloading json data from Google Directions API
        downloadTask.execute(url);
    }
    private String getDirectionsUrl5(LatLng origin, LatLng dest) {

        String str_origin = "origin=" +origin.latitude+","+origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude+","+dest.longitude;
        System.out.println("hjjghgh" + str_origin + "  " + str_dest);
        // Key
        String key = "key=" + getString(R.string.google_maps_key);

        System.out.println("gyghhghhfhfh" + str_origin + "  " + str_dest);
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + key;

        // Double distance = SphericalUtil.computeDistanceBetween(str_origin, str_dest);
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;
        System.out.println("hghghh" + "url1" + "\n " + url);
        return url;
    }




    private class DownloadTask5 extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl5(url[0]);
                Log.d("DownloadTask","DownloadTask : " + data);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask5 parserTask = new ParserTask5();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }
    private class ParserTask5 extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;
            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser2 parser = new DirectionsJSONParser2();
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {

            ArrayList<LatLng> points1 = null;
            PolylineOptions lineOptions2 = null;
            LatLng position=null;
            for(int i=0;i<result.size();i++){
                System.out.println("hjkhjhjhj"+result.size());
                points1 = new ArrayList<LatLng>();
                lineOptions2 = new PolylineOptions();
                List<HashMap<String, String>> path = result.get(i);
                System.out.println("aditya"+result.get(i));
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);
                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    position = new LatLng(lat, lng);
                    points1.add(position);
                    System.out.println("kjjkljkl"+points1);
                }
                lineOptions2.addAll(points);
                lineOptions2.width(8);
                lineOptions2.color(Color.BLACK);

            }
            if(lineOptions2 != null) {
                if(mPolyline != null){
                    mPolyline.remove();
                }
                mPolyline = googleMapHomeFrag.addPolyline(lineOptions2);

            }else
                Toast.makeText(getApplicationContext(),"No route is found", Toast.LENGTH_LONG).show();
        }
    }

    private void initMap() {
        SupportMapFragment mSupportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapView=mSupportMapFragment.getView();
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
                    EravanIdDetailsbseApi.getRetrofitInstance(context).create(EravanDetailsIdbase.class).registration(siteid).enqueue(new Callback<EravanIdgetSet>() {
                        @Override
                        public void onResponse(Call<EravanIdgetSet> call, Response<EravanIdgetSet> response) {
                            System.out.println("dfdfgfhghhg"+new Gson().toJson(response.body()));
                            System.out.println("soutgbbhhh"+response.body().getSGeoLocation());
                            String avg= String.valueOf(response.body().getAvgSpeed());
                            String dist= String.valueOf(response.body().getDistanceCovered());
                            vecno= String.valueOf(response.body().getVehicleNo());
                            sourcecars= String.valueOf(response.body().getSourceName());
                            destcar= String.valueOf(response.body().getDestinationName());
                            lastupadte= String.valueOf(response.body().getLastUpdatedAt());
                            datecar= String.valueOf(response.body().getEravanaDate());
                            String vecsttus= String.valueOf(response.body().getVehicleStatus());
                            vecuniqueid= response.body().getDeviceId();
                            System.out.println("ythfhgfghfhfhfhf"+vecuniqueid);
                            String triptimes= String.valueOf(response.body().getTripTime());
                            vechilenumber.setText(vecuniqueid);
                            vechilenumber1.setText(vecno+"\n\n"+sourcecars+"-"+destcar);
                            if(vecsttus.equalsIgnoreCase("Active")){
                                carststus.setTextColor(getResources().getColor(R.color.active));
                            }else {
                                carststus.setTextColor(getResources().getColor(R.color.inactive));
                            }
                            carststus.setText(vecsttus);
                            caravg.setText(avg);
                            triptime.setText(triptimes);
                            cardistance.setText(dist);
                            String[] latlong =  response.body().getSGeoLocation().split(",");
                            System.out.println("yytytyu"+latlong);
                            double latitude = Double.parseDouble(latlong[0]);
                            double longitude = Double.parseDouble(latlong[1]);
                            locationorigin= new LatLng(latitude, longitude);
                            String[] latlong1 =  response.body().getDGeoLocation().split(",");
                            double latitude1 = Double.parseDouble(latlong1[0]);
                            double longitude1 = Double.parseDouble(latlong1[1]);
                            locationdest = new LatLng(latitude1, longitude1);

                            mDestination=locationdest;
                            mOrigin=locationorigin;
                            System.out.println("hjghjghgh"+locationorigin+"   "+locationdest);
                            getDirectionsUrl(locationorigin,locationdest);
                            MarkerOptions place2 = new MarkerOptions().position(locationorigin);
                            googleMapHomeFrag.addMarker(place2);
                            MarkerOptions place21 = new MarkerOptions().position(locationdest);
                            googleMapHomeFrag.addMarker(place21);
                            googleMapHomeFrag.moveCamera(CameraUpdateFactory.newLatLngZoom(locationorigin, 15.0f));
                            drawRoute();
                            drawRoute1();
                            drawRoute2();
                        }

                        @Override
                        public void onFailure(Call<EravanIdgetSet> call, Throwable t) {

                        }
                    });
                    findViewById(R.id.normalview).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            System.out.println("jkjkjhkhkk");
                            googleMapHomeFrag.setMapStyle(MapStyleOptions.loadRawResourceStyle(PaidMapdrawlineActivity.this,R.raw.map_night));
                        }
                    });
                    googleMapHomeFrag.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    googleMapHomeFrag.setMapStyle(MapStyleOptions.loadRawResourceStyle(PaidMapdrawlineActivity.this, R.raw.light_map));
                    findViewById(R.id.sattelutviews).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            googleMapHomeFrag.setMapStyle(MapStyleOptions.loadRawResourceStyle(PaidMapdrawlineActivity.this,R.raw.mapnew));
                        }
                    });
                    googleMapHomeFrag.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(@NonNull Marker marker) {
                            System.out.println("jkljlljj"+marker.getPosition());
                            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                            return false;
                        }
                    });
                    if (ActivityCompat.checkSelfPermission(PaidMapdrawlineActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(PaidMapdrawlineActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    googleMapHomeFrag.setMyLocationEnabled(true);
                    googleMapHomeFrag.getUiSettings().setMyLocationButtonEnabled(true);
                    View locationButton = ((View) mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
                    RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
                    rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
                    rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);rlp.setMargins(0,0,30,30);
                    if(driverLatLng!=null){
                        if (googleMapHomeFrag != null) {
                            googleMapHomeFrag.moveCamera(CameraUpdateFactory.newLatLngZoom(driverLatLng, 12.0f));
                            googleMapHomeFrag.getUiSettings().setZoomControlsEnabled(true);
                        }
                    }
                }
            }
        });
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

        }
    }

    public void moveVehicle(final Marker myMarker, final Location finalPosition) {

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
               // VehicleRoute();
                LatLng l5=  getDestinationPoint2(currentPosition,0,.2);
                System.out.println("jhhgjhgjgjh"+l5+"    "+points1+"   "+speed);
                isMarkerInsidePolygon(l5, points1);
                if(speed>=30){
                    Toast.makeText(PaidMapdrawlineActivity.this, "You Are Going OverSpeed", Toast.LENGTH_SHORT).show();
                }
                googleMapHomeFrag.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 18.0f));
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
        final long duration = 1000;
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
                    handler.postDelayed(this, 10);
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
    public void isMarkerInsidePolygon(LatLng marker, ArrayList<LatLng> polyPoints) {
        System.out.println("lklkljklkjk");
        Toast toast = null;
        double x = marker.latitude, y = marker.longitude;
        boolean inside = false;
        System.out.println("ljkkljljkkl"+marker);
        System.out.println("dsdff"+polyPoints);
        int i, j;
        for (i = 0, j = polyPoints.size() - 1; i < polyPoints.size(); j = i++) {
            double xi = polyPoints.get(i).latitude,
                    yi = polyPoints.get(i).longitude;
            double xj = polyPoints.get(j).latitude,
                    yj = polyPoints.get(j).longitude;
            System.out.println("lkljlkjljl"+xi+"  "+yi+"  "+xj+"  "+yj);
            boolean intersect = ((yi > y) != (yj > y))&& (x < (xj - xi) * (y - yi) / (yj - yi) + xi);
            System.out.println("hjgjg"+(xj - xi));
            if (intersect) {
                inside = !inside;
            }
        }
        if (!inside) {
           // showCustomDialog();
            //BoundaryRthCounter = BoundaryRthCounter + 1;
            toast= Toast.makeText(this, "You are going outside of cooridor", Toast.LENGTH_LONG);
            toast.show();
//            ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
//            toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
//            thread = new Thread() {
//                @Override
//                public void run() {
//                    try {
//                        while (!thread.isInterrupted()) {
//                            Thread.sleep(1000);
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 50);
//                                    toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 2000);
//                                }
//                            });
//                        }
//                    } catch (InterruptedException e) {
//                    }
//                }
//            };
//            thread.start();
        }
        else {
            if (null !=toast) {
                toast.cancel();
                //thread.interrupt();
            }
        }
    }

    private void showCustomDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.bbokdaterecipt, viewGroup, false);
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();
    }




    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception on download", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        String str_origin = "origin=" +origin.latitude+","+origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude+","+dest.longitude;
        System.out.println("hjjghgh" + str_origin + "  " + str_dest);
        // Key
        String key = "key=" + getString(R.string.google_maps_key);

        System.out.println("gyghhghhfhfh" + str_origin + "  " + str_dest);
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + key;

        // Double distance = SphericalUtil.computeDistanceBetween(str_origin, str_dest);
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;
        System.out.println("hghghh" + "url1" + "\n " + url);
        return url;
    }



    private void drawRoute() {
        System.out.println("kjkhhjhghj");
        // Getting URL to the Google Directions API
        String url = getDirectionsUrl(mOrigin, mDestination);
        System.out.println("jkhjjhj"+url);
        DownloadTask downloadTask = new DownloadTask();

        // Start downloading json data from Google Directions API
        downloadTask.execute(url);
    }
    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
                Log.d("DownloadTask","DownloadTask : " + data);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {

            ArrayList<LatLng> points1 = null;
            PolylineOptions lineOptions = null;
            PolylineOptions lineOptions1 = null;
            LatLng position=null;
            LatLng l=null;
            for(int i=0;i<result.size();i++){
                System.out.println("hjkhjhjhj"+result.size());
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();
                List<HashMap<String, String>> path = result.get(i);
                System.out.println("aditya"+result.get(i));
                String s= path.toString();


                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);
                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    position = new LatLng(lat, lng);
                    points.add(position);
                    //points1.add(l);
                    System.out.println("kjjkljkl"+points);
                }



                lineOptions.addAll(points);
                lineOptions.width(8);
                lineOptions.color(Color.BLUE);

//                lineOptions1.addAll(points1);
//                lineOptions1.width(18);
//                lineOptions1.color(Color.RED);
            }

            // Drawing polyline in the Google Map for the i-th route
            if(lineOptions != null) {
                if(mPolyline != null){
                    mPolyline.remove();
                }
                mPolyline = googleMapHomeFrag.addPolyline(lineOptions);

            }else
                Toast.makeText(getApplicationContext(),"No route is found", Toast.LENGTH_LONG).show();
        }
    }


    private void drawRoute1() {
        System.out.println("kjkhhjhghj");
        // Getting URL to the Google Directions API
        String url = getDirectionsUrl(mOrigin, mDestination);

        DownloadTask1 downloadTask = new DownloadTask1();

        // Start downloading json data from Google Directions API
        downloadTask.execute(url);
    }
    private class DownloadTask1 extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
                Log.d("DownloadTask","DownloadTask : " + data);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask1 parserTask = new ParserTask1();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }
    private class ParserTask1 extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            //ArrayList<LatLng> points = null;

            // PolylineOptions lineOptions = null;
            PolylineOptions lineOptions1 = null;
            LatLng position1=null;
            LatLng l=null;
            for(int i=0;i<result.size();i++){
                points1 = new ArrayList<LatLng>();
                lineOptions1 = new PolylineOptions();
                List<HashMap<String, String>> path = result.get(i);
                System.out.println("aditya"+result.get(i));
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);
                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    position1 = new LatLng(lat, lng);
                    l =  getDestinationPoint(position1,90,.2);
                    points1.add(l);
                    System.out.println("gfhhg"+l);
//                    mylom=28.49856602181475;
//                    mylat=77.41464287615413;




                }
                ArrayList<ArrayList<LatLng>> projPoint1 = new ArrayList<>();
                projPoint1.add(points1);
                List<PatternItem> dashedPattern = Arrays.asList(new Dash(20), new Gap(15));
                lineOptions1.addAll(points1);
                lineOptions1.width(8);
                lineOptions1.pattern(dashedPattern);
                lineOptions1.color(getResources().getColor(R.color.dashcolor));

            }

            // Drawing polyline in the Google Map for the i-th route
            if(lineOptions1 != null) {
                if(mPolyline1 != null){
                    mPolyline1.remove();
                }
                mPolyline1 = googleMapHomeFrag.addPolyline(lineOptions1);

            }else
                Toast.makeText(getApplicationContext(),"No route is found", Toast.LENGTH_LONG).show();
        }
    }


    private void drawRoute2() {
        System.out.println("kjkhhjhghj");
        // Getting URL to the Google Directions API
        String url = getDirectionsUrl(mOrigin, mDestination);

        DownloadTask2 downloadTask = new DownloadTask2();

        // Start downloading json data from Google Directions API
        downloadTask.execute(url);
    }
    private class DownloadTask2 extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
                Log.d("DownloadTask","DownloadTask : " + data);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask2 parserTask = new ParserTask2();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }
    private class ParserTask2 extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser2 parser = new DirectionsJSONParser2();

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            //ArrayList<LatLng> points = null;
            ArrayList<LatLng> points2 = null;
            // PolylineOptions lineOptions = null;
            PolylineOptions lineOptions2 = null;
            LatLng position2=null;
            LatLng l2=null;
            for(int i=0;i<result.size();i++){
                points2 = new ArrayList<LatLng>();
                lineOptions2 = new PolylineOptions();
                List<HashMap<String, String>> path = result.get(i);
                System.out.println("aditya"+result.get(i));
                String s= path.toString();


                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);
                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    position2 = new LatLng(lat, lng);
                    l2=  getDestinationPoint1(position2,270,.2);
                    points2.add(l2);
                    //points1.add(l);
                    //System.out.println("kjjkljkl"+position);
                    System.out.println("gfhhg"+l2);
                }


                List<PatternItem> dashedPattern = Arrays.asList(new Dash(20), new Gap(15));
                lineOptions2.addAll(points2);
                lineOptions2.width(8);
                lineOptions2.pattern(dashedPattern);
                lineOptions2.color(getResources().getColor(R.color.dashcolor));

//                lineOptions1.addAll(points1);
//                lineOptions1.width(18);
//                lineOptions1.color(Color.RED);
            }

            // Drawing polyline in the Google Map for the i-th route
            if(lineOptions2 != null) {
                if(mPolyline2 != null){
                    mPolyline2.remove();
                }
                mPolyline2 = googleMapHomeFrag.addPolyline(lineOptions2);
                double mylat=mOrigin.latitude;
                double mylom=mOrigin.longitude;

                LatLng s2 = new LatLng(mylat, mylom);
                LatLng l5=  getDestinationPoint2(s2,0,.5);
                points1.addAll(points2);
                System.out.println("fdsgfdgdg"+l5);
                System.out.println("dasdsff"+points2);
                Log.e("LOcationvalure",""+((l5.latitude)+l5.longitude));
                //Toast.makeText(getApplicationContext(), ""+((l5.latitude)+l5.longitude), Toast.LENGTH_LONG).show();
                //isMarkerInsidePolygon(l5, points1);

            }else
                Toast.makeText(getApplicationContext(),"No route is found", Toast.LENGTH_LONG).show();
        }
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
    private LatLng getDestinationPoint2(LatLng source, double brng, double dist) {
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




}