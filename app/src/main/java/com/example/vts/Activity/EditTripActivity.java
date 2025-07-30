package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.example.vts.Activity.DashboardApi.Country;
import com.example.vts.Activity.EravanDetailsIdBase.EravanDetailsIdbase;
import com.example.vts.Activity.EravanDetailsIdBase.EravanIdDetailsbseApi;
import com.example.vts.Activity.EravanDetailsIdBase.EravanIdgetSet;
import com.example.vts.Activity.UserDelete.UserDeletegetset;
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
import com.example.vts.TriplistPack.EditParameter;
import com.example.vts.TriplistPack.EditTripInterface;
import com.example.vts.TriplistPack.TripListApi;
import com.example.vts.Vechilelistpack.VechicleInterface;
import com.example.vts.Vechilelistpack.Vechiclegetset;
import com.example.vts.Vechilelistpack.VechiclilistApi;
import com.example.vts.base.CommonUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditTripActivity extends AppCompatActivity {
String Siteid,Nonsite;
private GoogleMap googleMapHomeFrag;
    private Polyline mPolyline;
    EditText tripname,vehiclestatus,grossweight,tareweightvlue,Netweightvlue,destigeolocation,sourcegeolocation;
    TextView graossdatevlue,taredatevalue,enddatevlue,startdatevlue;
    Context context;
    Spinner packagelistspin,Sitelistspin,Devicelistspin;
    AutoCompleteTextView sourcename,destinamename;
    Button Login,cancelbtn;
    SharedPreferences sharedPreferences;
    View mapView;
    LinearLayout ll_site;
    View ll_siteview;
    String ogid;
    String f,t;
    String locationAddress,loctionaddres1;
    double sourcelat,sourcelong,destlat,destlong;
    LinearLayout ll_dest;
    View ll_destview;
    private LatLng mDestination;
    private LatLng mOrigin;
    ArrayList<LatLng> points = null;
    String VehicleId,SiteId,deviceid,sourcege,destge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);
        Siteid=getIntent().getStringExtra("Siteid");
        Nonsite=getIntent().getStringExtra("Nonsite");
        System.out.println("hjhjhj"+Siteid);
        System.out.println("hgjgjhghgh"+Nonsite+"     "+Siteid);
        ll_site=findViewById(R.id.ll_site);
        ll_siteview=findViewById(R.id.ll_siteview);
        ll_dest=findViewById(R.id.ll_dest);
        sourcename=findViewById(R.id.sourcename);
        ll_destview=findViewById(R.id.ll_destview);
        ll_dest.setVisibility(View.GONE);
        ll_destview.setVisibility(View.GONE);
        if(Nonsite.equalsIgnoreCase("Nonsite")){
            ll_site.setVisibility(View.GONE);
            ll_siteview.setVisibility(View.GONE);
        }else {
            ll_site.setVisibility(View.VISIBLE);
            ll_siteview.setVisibility(View.VISIBLE);
        }
        tripname=findViewById(R.id.tripname);
        initMap();
        vehiclestatus=findViewById(R.id.vehiclestatus);

        enddatevlue=findViewById(R.id.enddatevlue);
        startdatevlue=findViewById(R.id.startdatevlue);
        context=EditTripActivity.this;
        sharedPreferences = getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);
        ogid=sharedPreferences.getString(CommonUtils.shared_ORG_ID,"");
        grossweight=findViewById(R.id.grossweight);
        tareweightvlue=findViewById(R.id.tareweightvlue);
        Netweightvlue=findViewById(R.id.Netweightvlue);
        graossdatevlue=findViewById(R.id.graossdatevlue);
        taredatevalue=findViewById(R.id.taredatevalue);
        destigeolocation=findViewById(R.id.destigeolocation);
        destinamename=findViewById(R.id.destinamename);
        sourcegeolocation=findViewById(R.id.sourcegeolocation);
        Login=findViewById(R.id.submit_btn);
        packagelistspin=findViewById(R.id.packagelistspin);
        Sitelistspin=findViewById(R.id.Sitelistspin);
        Devicelistspin=findViewById(R.id.Devicelistspin);
        cancelbtn=findViewById(R.id.cancelbtn);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EditTripActivity.this,TripListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        VechiclilistApi.getRetrofitInstance(context).create(VechicleInterface.class).registration2().enqueue(new Callback<List<Vechiclegetset>>() {
            @Override
            public void onResponse(Call<List<Vechiclegetset>> call, Response<List<Vechiclegetset>> response) {
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
                ArrayList<Country> countryList = new ArrayList<>();
                countryList.add(new Country("Choose Site","Choose Site"));
                for (SiteGetset data:response.body()){
                    countryList.add(new Country(data.getSiteId(), data.getName()));
                    ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(context, android.R.layout.simple_spinner_dropdown_item, countryList);
                    Sitelistspin.setAdapter(adapter);


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
        sourcename.setAdapter(new PlacesAutoCompleteAdapter(EditTripActivity.this, R.layout.autocomplete_list_item));
        destinamename.setAdapter(new PlacesAutoCompleteAdapter(EditTripActivity.this, R.layout.autocomplete_list_item));
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
                                        Geocoder geocoder = new Geocoder(EditTripActivity.this, Locale.getDefault());
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
                                        googleMapHomeFrag.addMarker(new MarkerOptions().position(latLng).title(location));
                                        googleMapHomeFrag.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 9));

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
                                        Geocoder geocoder = new Geocoder(EditTripActivity.this,Locale.getDefault());
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
                                        googleMapHomeFrag.addMarker(new MarkerOptions().position(latLng).title(location));
                                        googleMapHomeFrag.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 9));
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

                category(f,t);
            }
        });

    }

    private void category(String f, String t) {
        final ProgressDialog progressDialog = new ProgressDialog(EditTripActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        EditParameter prameter= new EditParameter();
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
        prameter.EravanaId=Siteid;
        prameter.VehicleStatus=vehiclestatus.getText().toString();
        prameter.OrganizationId=ogid;
        prameter.EravanaDate=f;
        prameter.EravanaEndDate=t;
        System.out.println("ghfhfhgfg"+SiteId+"  "+deviceid+"  "+VehicleId+"   "+sourcegeolocation.getText().toString()+"    "+destigeolocation.getText().toString()+"  "+sharedPreferences.getString(CommonUtils.shared_ORG_ID,""));
        TripListApi.getRetrofitInstance(context).create(EditTripInterface.class).registration(prameter).enqueue(new Callback<UserDeletegetset>() {
            @Override
            public void onResponse(Call<UserDeletegetset> call, Response<UserDeletegetset> response) {
                if(response.body().getIsSuccess().equalsIgnoreCase("true")){
                    System.out.println("jkjkhkjhjhjgh");
                    progressDialog.dismiss();
                    Intent intent=new Intent(EditTripActivity.this,TripListActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(EditTripActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(EditTripActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
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


    DatePickerDialog datePickerDialog;

    private void startdatepick (TextView setdate){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        System.out.println("hytdsstdratsta"+mYear+"hffdz"+mMonth+"gytfdsdf"+mDay);
        datePickerDialog = new DatePickerDialog(EditTripActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                f=dateset;

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
        datePickerDialog = new DatePickerDialog(EditTripActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                t=dateset;
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
        datePickerDialog = new DatePickerDialog(EditTripActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        datePickerDialog = new DatePickerDialog(EditTripActivity.this, new DatePickerDialog.OnDateSetListener() {
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

                    EravanIdDetailsbseApi.getRetrofitInstance(context).create(EravanDetailsIdbase.class).registration(Siteid).enqueue(new Callback<EravanIdgetSet>() {
                        @Override
                        public void onResponse(Call<EravanIdgetSet> call, Response<EravanIdgetSet> response) {
                            System.out.println("dfdfgfhghhg"+new Gson().toJson(response.body()));
                            String vecsttus= String.valueOf(response.body().getVehicleStatus());
                            tripname.setText(response.body().getName());
                            grossweight.setText(response.body().getGrossWeight());
                            tareweightvlue.setText(response.body().getTareWeight());
                            Netweightvlue.setText(response.body().getNetWeight());
                            graossdatevlue.setText(response.body().getGrossDate());
                            destigeolocation.setText(response.body().getdGeoLocation());
                            sourcegeolocation.setText(response.body().getsGeoLocation());
                            taredatevalue.setText(response.body().getTareDate());
                            enddatevlue.setText(response.body().getEravanaEndDate());
                            f=response.body().getEravanaDate();
                            t=response.body().getEravanaEndDate();
                            sourcename.setText(response.body().getSourceName());
                            destinamename.setText(response.body().getDestinationName());
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
        Intent intent=new Intent(EditTripActivity.this,TripListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}