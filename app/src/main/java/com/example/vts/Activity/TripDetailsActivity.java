package com.example.vts.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vts.Activity.EravanDetailsIdBase.EravanDetailsIdbase;
import com.example.vts.Activity.EravanDetailsIdBase.EravanIdDetailsbseApi;
import com.example.vts.Activity.EravanDetailsIdBase.EravanIdgetSet;
import com.example.vts.Activity.SiteDelete.SiteDeleteApi;
import com.example.vts.Activity.UserDelete.UserDeletegetset;
import com.example.vts.DirectionsJSONParser;
import com.example.vts.R;
import com.example.vts.TriplistPack.TripDeleteInteface;
import com.example.vts.base.CommonUtils;
import com.example.vts.util.GPSTracker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripDetailsActivity extends AppCompatActivity{
String Nonsite,Tripid;

    private GoogleMap googleMapHomeFrag;
    private Polyline mPolyline;
    EditText tripname,vehiclestatus,grossweight,tareweightvlue,Netweightvlue,sitename,vechicleno,devicename,sourcename,destinname;
    TextView graossdatevlue,taredatevalue,enddatevlue,startdatevlue;
    Context context;

    SharedPreferences sharedPreferences;
    View mapView;
    String status;
     LinearLayout ll_site;
    View ll_siteview;
    String ogid;
    ArrayList<LatLng> points = null;
    private LatLng mDestination;
    private LatLng mOrigin;
    ImageView submenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);
        Nonsite=getIntent().getStringExtra("Nonsite");
        Tripid=getIntent().getStringExtra("Tripid");
        System.out.println("hgjgjhghgh"+Nonsite+"     "+Tripid);
        ll_site=findViewById(R.id.ll_site);
        ll_siteview=findViewById(R.id.ll_siteview);

        if(Nonsite.equalsIgnoreCase("Nonsite")){
            ll_site.setVisibility(View.GONE);
            ll_siteview.setVisibility(View.GONE);
        }else {
            ll_site.setVisibility(View.VISIBLE);
            ll_siteview.setVisibility(View.VISIBLE);
        }
        submenu = findViewById(R.id.submenu);
        tripname=findViewById(R.id.tripname);
        initMap();
        vehiclestatus=findViewById(R.id.vehiclestatus);

        enddatevlue=findViewById(R.id.enddatevlue);
        sourcename=findViewById(R.id.sourcename);
        destinname=findViewById(R.id.destinname);
        sitename=findViewById(R.id.sitename);
        vechicleno=findViewById(R.id.vechicleno);
        devicename=findViewById(R.id.devicename);
        startdatevlue=findViewById(R.id.startdatevlue);
        context=TripDetailsActivity.this;
        sharedPreferences = getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);
        ogid=sharedPreferences.getString(CommonUtils.shared_ORG_ID,"");
        grossweight=findViewById(R.id.grossweight);
        tareweightvlue=findViewById(R.id.tareweightvlue);
        Netweightvlue=findViewById(R.id.Netweightvlue);
        graossdatevlue=findViewById(R.id.graossdatevlue);
        taredatevalue=findViewById(R.id.taredatevalue);
        submenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(TripDetailsActivity.this, submenu);
                popup.getMenuInflater().inflate(R.menu.menu_example, popup.getMenu());
                popup.setForceShowIcon(true);
                //   popup.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.deletesite) {
                            BackPressed(Tripid);
                            //Toast.makeText(getApplicationContext(), "Delete clicked", Toast.LENGTH_SHORT).show();
                        } else if (item.getItemId() == R.id.editsite) {
                            if(status.equalsIgnoreCase("Completed")){
                                Toast.makeText(TripDetailsActivity.this, "Trip Completed Can't edit", Toast.LENGTH_SHORT).show();
                            }else {
                                Intent intent = new Intent(TripDetailsActivity.this, EditTripActivity.class);
                                intent.putExtra("Siteid",Tripid);
                                intent.putExtra("Nonsite",Nonsite);
                                startActivity(intent);
                            }



//                            finish();
                            // Toast.makeText(getApplicationContext(), "Edit clicked", Toast.LENGTH_SHORT).show();
                        }

                        return true;
                    }
                });
                popup.show();
            }
        });

    }
    public void BackPressed(String id) {

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setMessage("Do you want to Delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Delete(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
    }

    private void Delete(String id) {
        final ProgressDialog progressDialog = new ProgressDialog(TripDetailsActivity.this);
        progressDialog.setCancelable(false); // set cancelable to User
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        SiteDeleteApi.getRetrofitInstance(context).create(TripDeleteInteface.class).registration(id).enqueue(new Callback<UserDeletegetset>() {
            @Override
            public void onResponse(Call<UserDeletegetset> call, Response<UserDeletegetset> response) {
                if(response.body().getIsSuccess().equalsIgnoreCase("true")){
                    progressDialog.dismiss();
                    Intent intent=new Intent(TripDetailsActivity.this,TripListActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(TripDetailsActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(TripDetailsActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserDeletegetset> call, Throwable t) {

            }
        });
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
                    googleMapHomeFrag.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                    EravanIdDetailsbseApi.getRetrofitInstance(context).create(EravanDetailsIdbase.class).registration(Tripid).enqueue(new Callback<EravanIdgetSet>() {
                        @Override
                        public void onResponse(Call<EravanIdgetSet> call, Response<EravanIdgetSet> response) {
                            System.out.println("dfdfgfhghhg"+new Gson().toJson(response.body()));
                            String vecsttus= String.valueOf(response.body().getVehicleStatus());
                            status=response.body().getStatus();
                            vechicleno.setText(response.body().getVehicleNo());
                            tripname.setText(response.body().getName());
                            grossweight.setText(response.body().getGrossWeight());
                            tareweightvlue.setText(response.body().getTareWeight());
                            Netweightvlue.setText(response.body().getNetWeight());
                            sitename.setText(response.body().getSiteId());
                            destinname.setText(response.body().getDestinationName());
                            sourcename.setText(response.body().getSourceName());
                            devicename.setText(response.body().getDeviceId());
                            graossdatevlue.setText(response.body().getGrossDate());
                            taredatevalue.setText(response.body().getTareDate());
                            enddatevlue.setText(response.body().getEravanaEndDate());
                            startdatevlue.setText(response.body().getEravanaDate());
                            if(vecsttus.equalsIgnoreCase("active")){
                                vehiclestatus.setTextColor(getResources().getColor(R.color.active));
                            }else {
                                vehiclestatus.setTextColor(getResources().getColor(R.color.inactive));
                            }
                            vehiclestatus.setText(vecsttus);

                            String[] latlong =  response.body().getSGeoLocation().split(",");
                            System.out.println("yytytyu"+latlong);
                            double latitude = Double.parseDouble(latlong[0]);
                            double longitude = Double.parseDouble(latlong[1]);
                            LatLng locationorigin= new LatLng(latitude, longitude);
                            mOrigin=locationorigin;
                            String[] latlong1 =  response.body().getDGeoLocation().split(",");
                            double latitude1 = Double.parseDouble(latlong1[0]);
                            double longitude1 = Double.parseDouble(latlong1[1]);
                            LatLng locationdest = new LatLng(latitude1, longitude1);
                            mDestination=locationdest;
                            getDirectionsUrl(locationorigin,locationdest);
                            MarkerOptions place2 = new MarkerOptions().position(locationorigin);
                            googleMapHomeFrag.addMarker(place2);
                            MarkerOptions place21 = new MarkerOptions().position(locationdest);
                            googleMapHomeFrag.addMarker(place21);
                            googleMapHomeFrag.moveCamera(CameraUpdateFactory.newLatLngZoom(locationorigin, 10));
                            drawRoute();

                        }

                        @Override
                        public void onFailure(Call<EravanIdgetSet> call, Throwable t) {

                        }
                    });



                }
            }
        });
    }
    public void back(View view) {
        Intent intent=new Intent(TripDetailsActivity.this,TripListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}