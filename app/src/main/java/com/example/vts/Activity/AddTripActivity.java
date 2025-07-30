package com.example.vts.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.vts.Activity.Adapter.PlacesAutoCompleteAdapter;
import com.example.vts.Activity.AddTripPack.AddTripApi;
import com.example.vts.Activity.AddTripPack.AddTripInterface;
import com.example.vts.Activity.AddTripPack.AddTripParameter;
import com.example.vts.Activity.AddTripPack.AddTripgetset;
import com.example.vts.Activity.AddVechicle.AddVechicleApi;
import com.example.vts.Activity.AddVechicle.AddVechiclegetset;
import com.example.vts.Activity.AddVechicle.AddvechicleParameter;
import com.example.vts.Activity.DashboardApi.Country;
import com.example.vts.DirectionsJSONParser;
import com.example.vts.GetVechicleDetailPAck.DeviceDetail;
import com.example.vts.GetVechicleDetailPAck.GetAllVechicleInterface;
import com.example.vts.GetVechicleDetailPAck.GetAllVechilegetset;
import com.example.vts.GetVechicleDetailPAck.GetAllVehicleApi;
import com.example.vts.GetVechicleDetailPAck.GetAllVehicleDetailsAPus;
import com.example.vts.GetVechicleDetailPAck.ResultData;
import com.example.vts.R;
import com.example.vts.SitePackage.SiteGetset;
import com.example.vts.SitePackage.SiteInterface;
import com.example.vts.SitePackage.SiteListApi;
import com.example.vts.Vechilelistpack.VechicleInterface;
import com.example.vts.Vechilelistpack.Vechiclegetset;
import com.example.vts.Vechilelistpack.VechiclilistApi;
import com.example.vts.base.CommonUtils;
import com.example.vts.util.GPSTracker;
import com.example.vts.util.GeoCodeLocation;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTripActivity extends AppCompatActivity  implements OnMapReadyCallback{
    private GoogleMap mMap;
    String NonSite;
    private Polyline mPolyline;
    EditText tripname,vehiclestatus,destigeolocation,sourcegeolocation,grossweight,tareweightvlue,Netweightvlue;
    TextView graossdatevlue,taredatevalue,enddatevlue,startdatevlue;
    Spinner packagelistspin,Sitelistspin,Devicelistspin;
    Context context;
    AutoCompleteTextView sourcename,destinamename;
    String VehicleId,SiteId,deviceid,ogid,sourcege,destge;
    Button Login,cancelbtn;
    SharedPreferences sharedPreferences;
    String locationAddress,loctionaddres1;
    double sourcelat,sourcelong,destlat,destlong;
    private LatLng mDestination;
    private LatLng mOrigin;
    LinearLayout ll_dest,ll_site;
    View ll_destview,ll_siteview;
    ArrayList<LatLng> points1 = null;
    ArrayList<LatLng> points = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ll_site=findViewById(R.id.ll_site);
        ll_siteview=findViewById(R.id.ll_siteview);
        NonSite=getIntent().getStringExtra("NonSite");
        if(NonSite.equalsIgnoreCase("NonSite")){
            ll_site.setVisibility(View.GONE);
            ll_siteview.setVisibility(View.GONE);
        }else {
            ll_site.setVisibility(View.VISIBLE);
            ll_siteview.setVisibility(View.VISIBLE);
        }
        tripname=findViewById(R.id.tripname);
        cancelbtn=findViewById(R.id.cancelbtn);
        vehiclestatus=findViewById(R.id.vehiclestatus);
        sourcename=findViewById(R.id.sourcename);
        enddatevlue=findViewById(R.id.enddatevlue);
        ll_dest=findViewById(R.id.ll_dest);
        ll_destview=findViewById(R.id.ll_destview);
        ll_dest.setVisibility(View.GONE);
        ll_destview.setVisibility(View.GONE);
        startdatevlue=findViewById(R.id.startdatevlue);
        context=AddTripActivity.this;
        Login=findViewById(R.id.submit_btn);
        sharedPreferences = getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);
        ogid=sharedPreferences.getString(CommonUtils.shared_ORG_ID,"");

        destigeolocation=findViewById(R.id.destigeolocation);
        destinamename=findViewById(R.id.destinamename);
        sourcegeolocation=findViewById(R.id.sourcegeolocation);
        grossweight=findViewById(R.id.grossweight);
        tareweightvlue=findViewById(R.id.tareweightvlue);
        Netweightvlue=findViewById(R.id.Netweightvlue);
        graossdatevlue=findViewById(R.id.graossdatevlue);
        taredatevalue=findViewById(R.id.taredatevalue);
        packagelistspin=findViewById(R.id.packagelistspin);
        Sitelistspin=findViewById(R.id.Sitelistspin);
        Devicelistspin=findViewById(R.id.Devicelistspin);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddTripActivity.this,TripListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        VechiclilistApi.getRetrofitInstance(context).create(VechicleInterface.class).registration2().enqueue(new Callback<List<Vechiclegetset>>() {
            @Override
            public void onResponse(Call<List<Vechiclegetset>> call, Response<List<Vechiclegetset>> response) {
                if(response.body().isEmpty()){
                    ArrayList<Country> countryList = new ArrayList<>();
                    countryList.add(new Country("Choose Vehicle Number","Choose Vehicle Number"));
                    ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(context, android.R.layout.simple_spinner_dropdown_item, countryList);
                    packagelistspin.setAdapter(adapter);
                }else {
                    ArrayList<Country> countryList = new ArrayList<>();
                    countryList.add(new Country("Choose Vehicle Number","Choose Vehicle Number"));
                    for (Vechiclegetset data:response.body()) {
                        String id=data.getDeviceId();
                        String name=data.getVehicleNo();
                        countryList.add(new Country(data.getVehicleId(), data.getVehicleNo()));
                        ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(context, android.R.layout.simple_spinner_dropdown_item, countryList);
                        packagelistspin.setAdapter(adapter);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Vechiclegetset>> call, Throwable t) {

            }
        });
        packagelistspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Country country = (Country) parent.getSelectedItem();
                VehicleId=country.getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SiteListApi.getRetrofitInstance(context).create(SiteInterface.class).registration2().enqueue(new Callback<List<SiteGetset>>() {
            @Override
            public void onResponse(Call<List<SiteGetset>> call, Response<List<SiteGetset>> response) {

                if(response.body().isEmpty()){
                    ArrayList<Country> countryList = new ArrayList<>();
                    countryList.add(new Country("Choose Site","Choose Site"));
                    ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(context, android.R.layout.simple_spinner_dropdown_item, countryList);
                    Sitelistspin.setAdapter(adapter);
                }else {
                    ArrayList<Country> countryList = new ArrayList<>();
                    countryList.add(new Country("Choose Site","Choose Site"));
                    for (SiteGetset data:response.body()){
                        countryList.add(new Country(data.getSiteId(), data.getName()));
                        ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(context, android.R.layout.simple_spinner_dropdown_item, countryList);
                        Sitelistspin.setAdapter(adapter);


                    }
                }


            }

            @Override
            public void onFailure(Call<List<SiteGetset>> call, Throwable t) {

            }
        });
        Sitelistspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Country country = (Country) parent.getSelectedItem();
                SiteId=country.getId();
                if(SiteId.equalsIgnoreCase("Choose Site")){
                    SiteId=null;
                }else {
                    SiteId=country.getId();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        GetAllVehicleApi.getRetrofitInstance(context).create(GetAllVechicleInterface.class).registration2().enqueue(new Callback<GetAllVechilegetset>() {
            @Override
            public void onResponse(Call<GetAllVechilegetset> call, Response<GetAllVechilegetset> response) {
                ArrayList<Country> countryList1 = new ArrayList<>();
                countryList1.add(new Country("Choose DeviceId","Choose Device ID"));
                ResultData data=response.body().getResultData();
                System.out.println("hjggjggjg"+data.getGetAllVehicleDetailsAPI());
                List<GetAllVehicleDetailsAPus>getAllVehicleDetailsAPuses=data.getGetAllVehicleDetailsAPI();
                System.out.println("hjhjhjg"+getAllVehicleDetailsAPuses);
                for (GetAllVehicleDetailsAPus data1:getAllVehicleDetailsAPuses){
                    DeviceDetail deviceDetail1=data1.getDeviceDetail();
                    String id=deviceDetail1.getUniqueDeviceId();
                    System.out.println("jkhkjhjjhkkj"+id);
                    countryList1.add(new Country(deviceDetail1.getUniqueDeviceId(), deviceDetail1.getUniqueDeviceId()));
                    ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(context, android.R.layout.simple_spinner_dropdown_item, countryList1);
                    Devicelistspin.setAdapter(adapter);
                }


            }

            @Override
            public void onFailure(Call<GetAllVechilegetset> call, Throwable t) {

            }
        });
        Devicelistspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Country country = (Country) parent.getSelectedItem();
                deviceid=country.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        graossdatevlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepick(graossdatevlue);
            }
        });
        startdatevlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startdatepick(startdatevlue);
            }
        });

        enddatevlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enddatepick(enddatevlue);
            }
        });
        taredatevalue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepick1(taredatevalue);
            }
        });

        sourcename.setAdapter(new PlacesAutoCompleteAdapter(AddTripActivity.this, R.layout.autocomplete_list_item));
        destinamename.setAdapter(new PlacesAutoCompleteAdapter(AddTripActivity.this, R.layout.autocomplete_list_item));
        final String googleurl2 = "https://maps.googleapis.com/maps/api/place/details/json?";
        sourcename.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             String description = (String) parent.getItemAtPosition(position);
             String[] arrOfStr = description.split("=", 2);
             sourcename.setText(arrOfStr[0].trim());
             System.out.println("lklksjdkljflsdf " + arrOfStr[1]);
             AndroidNetworking.post(googleurl2 + "placeid=" + arrOfStr[1] + "&key=" + getResources().getString(R.string.google_id))
                     .setTag("test")
                     .setPriority(Priority.MEDIUM)
                     .build()
                     .getAsJSONObject(new JSONObjectRequestListener() {
                         @Override
                         public void onResponse(JSONObject response) {
                             ll_dest.setVisibility(View.VISIBLE);
                             ll_destview.setVisibility(View.VISIBLE);
                             try {
                                 JSONObject jsonObject = response.getJSONObject("result");
                                 JSONObject jsonObject1 = jsonObject.getJSONObject("geometry");
                                 JSONObject jsonObject2 = jsonObject1.getJSONObject("location");
                                 String formatted_address = jsonObject.getString("formatted_address");
                                String latipi = jsonObject2.getString("lat");
                                 String longipi = jsonObject2.getString("lng");
                                 sourcegeolocation.setText(latipi+","+longipi);
                                 sourcelat= Double.parseDouble(latipi);
                                 sourcelong= Double.parseDouble(longipi);
                                 //sourcegeolocation.setText(latLng);
                                 String location = formatted_address;
                                 List<Address> addressList = null;
                                 if (location != null || location.equals("")) {
                                     Geocoder geocoder = new Geocoder(AddTripActivity.this,Locale.getDefault());
                                     try {
                                         addressList = geocoder.getFromLocationName(location, 1);
                                     } catch (IOException e) {
                                         e.printStackTrace();
                                     }
                                     String j=addressList.get(0).getAddressLine(0);
                                     System.out.println("hjjhgjgjh"+addressList);
                                     Address address = addressList.get(0);
                                     LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                                     mOrigin=latLng;
                                     mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                                     mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 9));

                                 }



                             } catch (JSONException e) {
                                 e.printStackTrace();
                             }
                             System.out.println("skldfljsdlf" + response);
                         }

                         @Override
                         public void onError(ANError error) {
                         }
                     });
         }
     });

        destinamename.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String description = (String) parent.getItemAtPosition(position);
                String[] arrOfStr = description.split("=", 2);
                destinamename.setText(arrOfStr[0].trim());
                System.out.println("lklksjdkljflsdf " + arrOfStr[1]);
                AndroidNetworking.post(googleurl2 + "placeid=" + arrOfStr[1] + "&key=" + getResources().getString(R.string.google_id))
                        .setTag("test")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject("result");
                                    JSONObject jsonObject1 = jsonObject.getJSONObject("geometry");
                                    JSONObject jsonObject2 = jsonObject1.getJSONObject("location");
                                    String formatted_address = jsonObject.getString("formatted_address");
                                   String latipi = jsonObject2.getString("lat");
                                    String  longipi = jsonObject2.getString("lng");
                                    destigeolocation.setText(latipi+","+longipi);
                                    //sourcegeolocation.setText(latLng);
                                    destlat= Double.parseDouble(latipi);
                                    destlong= Double.parseDouble(longipi);
                                    System.out.println("dfsfsdfsdfsdfsdfd" + longipi);

                                    String location = formatted_address;
                                    List<Address> addressList = null;
                                    if (location != null || location.equals("")) {
                                        Geocoder geocoder = new Geocoder(AddTripActivity.this,Locale.getDefault());
                                        try {
                                            addressList = geocoder.getFromLocationName(location, 1);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        String j=addressList.get(0).getAddressLine(0);
                                        System.out.println("hjjhgjgjh"+addressList);
                                        Address address = addressList.get(0);
                                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                                        mDestination=latLng;
                                        mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 9));
                                        drawRoute();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("skldfljsdlf" + response);
                            }

                            @Override
                            public void onError(ANError error) {
                            }
                        });
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f=startdatevlue.getText().toString();
                String t=enddatevlue.getText().toString();
                if(tripname.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(AddTripActivity.this, "Please Enter Trip Name", Toast.LENGTH_SHORT).show();
                }else if(f.equalsIgnoreCase("")){
                    Toast.makeText(AddTripActivity.this, "Please Select Start Date", Toast.LENGTH_SHORT).show();
                }
                else if(t.equalsIgnoreCase("")){
                    Toast.makeText(AddTripActivity.this, "Please Select End Date", Toast.LENGTH_SHORT).show();
                }
                else if(VehicleId.equalsIgnoreCase("Choose Vehicle")){
                    Toast.makeText(AddTripActivity.this, "Please Choose Vehicle", Toast.LENGTH_SHORT).show();
                }else if(deviceid.equalsIgnoreCase("Choose DeviceId")){
                    Toast.makeText(AddTripActivity.this, "Please Choose Device_ID", Toast.LENGTH_SHORT).show();
                }
                else {
                    System.out.println("juhhggjggh"+ogid+"    "+destge+"    "+sourcegeolocation.getText().toString()+"    "+locationAddress+"    "+loctionaddres1);
                    category(f,t);
                }
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
                mPolyline = mMap.addPolyline(lineOptions);

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


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        GPSTracker gpsTracker = new GPSTracker(AddTripActivity.this);
        double mylat=gpsTracker.getLatitude();
        double mylom=gpsTracker.getLongitude();
        System.out.println("hjhgjhgghhj"+sourcelat);
 if((sourcelat==0.0)&&(destlat==0.0)){
     LatLng coordinate1 = new LatLng(mylat, mylom);
     MarkerOptions markerOptions1=new MarkerOptions().position(coordinate1).draggable(true);
     CameraUpdate locations = CameraUpdateFactory.newLatLngZoom(coordinate1, 10);
     mMap.animateCamera(locations);
     mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinate1, 10));
     mMap.addMarker(markerOptions1);
 }else {
     LatLng coordinate1 = new LatLng(sourcelat, sourcelong);
     LatLng coordinate2 = new LatLng(destlat, destlong);
     MarkerOptions markerOptions1=new MarkerOptions().position(coordinate1).draggable(true);
     MarkerOptions markerOptions2=new MarkerOptions().position(coordinate2).draggable(true);
     CameraUpdate locations = CameraUpdateFactory.newLatLngZoom(coordinate1, 10);
     mMap.animateCamera(locations);
     mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinate1, 10));
     mMap.addMarker(markerOptions1);
     mMap.addMarker(markerOptions2);
 }


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;}
        mMap.setMyLocationEnabled(true);
    }

    private void category(String st,String end) {
        final ProgressDialog progressDialog = new ProgressDialog(AddTripActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        AddTripParameter prameter= new AddTripParameter();
        prameter.DeviceId=deviceid;
       prameter.Name=tripname.getText().toString();
       prameter.D_GeoLocation=destigeolocation.getText().toString();
       prameter.DestinationName=destinamename.getText().toString();
       prameter.SiteId=SiteId;
       prameter.VehicleId=VehicleId;
       prameter.S_GeoLocation=sourcegeolocation.getText().toString();
       prameter.GrossDate=graossdatevlue.getText().toString();
       prameter.SourceName=sourcename.getText().toString();
       prameter.GrossWeight=grossweight.getText().toString();
       prameter.TareDate=taredatevalue.getText().toString();
       prameter.TareWeight=tareweightvlue.getText().toString();
        prameter.IsActive="true";
        prameter.IsDeleted="false";
        prameter.VehicleStatus=vehiclestatus.getText().toString();
        prameter.OrganizationId=ogid;
        prameter.EravanaDate=st;
        prameter.EravanaEndDate=end;
        System.out.println("ghfhfhgfg"+SiteId+"  "+deviceid+"  "+VehicleId+"   "+sourcegeolocation.getText().toString()+"    "+destigeolocation.getText().toString()+"  "+sharedPreferences.getString(CommonUtils.shared_ORG_ID,""));
        AddTripApi.getRetrofitInstance(context).create(AddTripInterface.class).registration2(prameter).enqueue(new Callback<AddTripgetset>() {
            @Override
            public void onResponse(Call<AddTripgetset> call, Response<AddTripgetset> response) {
                progressDialog.dismiss();
                System.out.println("jhhjjgjgj"+response.body().getSuccess());
                if(response.body().getSuccess()==false){
                    Toast.makeText(AddTripActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }else if( response.body().getSuccess()==true){
                    Intent intent=new Intent(AddTripActivity.this,TripListActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(AddTripActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddTripgetset> call, Throwable t) {

            }
        });
    }
    DatePickerDialog datePickerDialog;

    private void startdatepick (TextView setdate){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        System.out.println("hytdsstdratsta"+mYear+"hffdz"+mMonth+"gytfdsdf"+mDay);
        datePickerDialog = new DatePickerDialog(AddTripActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String s=((monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);
                DateFormat inputFormat1 = new SimpleDateFormat("M-dd-yyyy");//2021-02-23
//                inputFormat1.setTimeZone(TimeZone.getTimeZone("UTC"));
                DateFormat outputFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                String inputDateStr1=s.trim();
                Date date1 = null;
                try {
                    date1 = inputFormat1.parse(inputDateStr1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String outputDateStr1= outputFormat1.format(date1);
                System.out.println("hgtyrdrfaghbhjhfdrtg"+outputDateStr1);
                String dateset=outputDateStr1;
                setdate.setText(dateset);

            }
        }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    public long milliseconds(String date) {
        //String date_ = date;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date mDate = sdf.parse(date);
            long timeInMilliseconds = mDate.getTime();
            System.out.println("Date in milli :: " + timeInMilliseconds);
            return timeInMilliseconds;
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 0;
    }


    private void enddatepick (TextView setdate){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        System.out.println("hytdsstdratsta"+mYear+"hffdz"+mMonth+"gytfdsdf"+mDay);
        datePickerDialog = new DatePickerDialog(AddTripActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String s=((monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);
                DateFormat inputFormat1 = new SimpleDateFormat("M-dd-yyyy");//2021-02-23
                DateFormat outputFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                String inputDateStr1=s.trim();
                Date date1 = null;
                try {
                    date1 = inputFormat1.parse(inputDateStr1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String outputDateStr1= outputFormat1.format(date1);
                System.out.println("hgtyrdrfaghbhjhfdrtg"+outputDateStr1);
                String dateset=outputDateStr1;
                setdate.setText(dateset);
                if(startdatevlue.getText().toString().equalsIgnoreCase("")){
                    startdatevlue.setText(dateset);
                }

            }
        }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
       // datePickerDialog.getDatePicker().setMaxDate(milliseconds(startdatevlue.getText().toString()));
        datePickerDialog.show();
    }


    private void datepick (TextView setdate){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        System.out.println("hytdsstdratsta"+mYear+"hffdz"+mMonth+"gytfdsdf"+mDay);
        datePickerDialog = new DatePickerDialog(AddTripActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String s=((monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);
                DateFormat inputFormat1 = new SimpleDateFormat("M-dd-yyyy");//2021-02-23
                DateFormat outputFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                String inputDateStr1=s.trim();
                Date date1 = null;
                try {
                    date1 = inputFormat1.parse(inputDateStr1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String outputDateStr1= outputFormat1.format(date1);
                System.out.println("hgtyrdrfaghbhjhfdrtg"+outputDateStr1);
                String dateset=outputDateStr1;
                setdate.setText(dateset);

            }
        }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
    private void datepick1 (TextView setdate){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        System.out.println("hytdsstdratsta"+mYear+"hffdz"+mMonth+"gytfdsdf"+mDay);
        datePickerDialog = new DatePickerDialog(AddTripActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String s=((monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);
                DateFormat inputFormat1 = new SimpleDateFormat("M-dd-yyyy");//2021-02-23
                DateFormat outputFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                String inputDateStr1=s.trim();
                Date date1 = null;
                try {
                    date1 = inputFormat1.parse(inputDateStr1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String outputDateStr1= outputFormat1.format(date1);
                System.out.println("hgtyrdrfaghbhjhfdrtg"+outputDateStr1);
                String dateset=outputDateStr1;
                setdate.setText(dateset);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
    public void back(View view) {
        Intent intent=new Intent(AddTripActivity.this,TripListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}