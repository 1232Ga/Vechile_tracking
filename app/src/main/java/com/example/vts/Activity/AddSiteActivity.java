package com.example.vts.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.vts.Activity.Adapter.PlacesAutoCompleteAdapter;
import com.example.vts.Activity.AddDevice.AddDeviceApi;

import com.example.vts.Activity.Addsitr.AddSiteInterface;
import com.example.vts.Activity.Addsitr.AddSiteParamter;
import com.example.vts.Activity.Addsitr.AddSitegetset;
import com.example.vts.R;
import com.example.vts.RolePack.RoleParameter;
import com.example.vts.base.CommonUtils;
import com.example.vts.util.GPSTracker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSiteActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    String kk;
    Button Login;
    Context context;
    SharedPreferences sharedPreferences;
    EditText latlong, address, country, stste, District, pincode, ststename,orgnizationname;

    private LinearLayout mBottomSheetLayout;
    private BottomSheetBehavior sheetBehavior;
    private ImageView header_Arrow_Image;
    AutoCompleteTextView sourcename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_site);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        address = findViewById(R.id.address);

        sourcename=findViewById(R.id.sourcename);
        context=AddSiteActivity.this;
        latlong = findViewById(R.id.latlong);
        mBottomSheetLayout = findViewById(R.id.bottom_sheet_layout);
        sheetBehavior = BottomSheetBehavior.from(mBottomSheetLayout);
        header_Arrow_Image = findViewById(R.id.bottom_sheet_arrow);

        country = findViewById(R.id.country);
        stste = findViewById(R.id.stste);
        orgnizationname = findViewById(R.id.orgnizationname);
        District = findViewById(R.id.District);
        pincode = findViewById(R.id.pincode);
        ststename = findViewById(R.id.ststename);
        Login=findViewById(R.id.submit_btn);
        sharedPreferences = getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(orgnizationname.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(AddSiteActivity.this, "Please Enter Site Name", Toast.LENGTH_SHORT).show();
                }else {
                    category();
                }
            }
        });
        header_Arrow_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

            }
        });
        sheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                header_Arrow_Image.setRotation(slideOffset * 180);
            }
        });
        sourcename.setAdapter(new PlacesAutoCompleteAdapter(AddSiteActivity.this, R.layout.autocomplete_list_item));
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
                                System.out.println("jhgjgjgghj"+response);
                                try {
                                    JSONObject jsonObject = response.getJSONObject("result");
                                    JSONObject jsonObject1 = jsonObject.getJSONObject("geometry");
                                    JSONArray memberlist = jsonObject.getJSONArray("address_components");
                                    String formatted_address = jsonObject.getString("formatted_address");
                                    JSONObject jsonObject2 = jsonObject1.getJSONObject("location");
//                                    for (int i = 0; i < memberlist.length(); i++) {
//                                        JSONObject list = memberlist.getJSONObject(i);
//                                        District.setText(memberlist.getJSONObject(0).getString("long_name"));
//                                        ststename.setText(memberlist.getJSONObject(0).getString("long_name"));
//                                        stste.setText(memberlist.getJSONObject(2).getString("long_name"));
//                                        country.setText(memberlist.getJSONObject(3).getString("long_name"));
//
//                                    }
//                                    latlong.setText(latipi+","+longipi);
//                                    address.setText(formatted_address);


                                    String location = formatted_address;
                                    List<Address> addressList = null;
                                    if (location != null || location.equals("")) {
                                        Geocoder geocoder = new Geocoder(AddSiteActivity.this,Locale.getDefault());
                                        try {
                                            addressList = geocoder.getFromLocationName(location, 1);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        String j=addressList.get(0).getAddressLine(0);
                                        System.out.println("hjjhgjgjh"+addressList);
                                        address.setText(j);
                                        country.setText(addressList.get(0).getCountryName());
                                        stste.setText(addressList.get(0).getLocality());
                                        District.setText(addressList.get(0).getLocality());
                                        pincode.setText(addressList.get(0).getPostalCode());
                                        ststename.setText(addressList.get(0).getAdminArea());
                                        Address address = addressList.get(0);
                                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                                        String kkkkk= String.valueOf(latLng);
                                        String jj=(kkkkk.replaceAll("lat/lng:",""));

                                        String data=jj;
                                        System.out.println("ghhggfhh"+data);
                                        String [] tempArray = data.substring(data.indexOf("(")+1, data.lastIndexOf(")")).split(",");
                                        double newlong= Double.parseDouble(tempArray[1]);
                                        double newlat = Double.parseDouble(tempArray[0]);
                                        latlong.setText(newlat+" ,"+newlong);
                                        mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                                        sourcename.setText("");
                                        //sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                                    }









                                   // sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);






//                                     , , pincode, ,orgnizationname
                                    //sourcegeolocation.setText(latLng);




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
    }

    private void category() {
        final ProgressDialog progressDialog = new ProgressDialog(AddSiteActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        AddSiteParamter prameter= new AddSiteParamter();
        prameter.Name=orgnizationname.getText().toString();
        prameter.Address=address.getText().toString();
        prameter.IsActive="true";
        prameter.IsDeleted="false";
        prameter.District=District.getText().toString();
        prameter.State=ststename.getText().toString();
        prameter.City=stste.getText().toString();
        prameter.PinCode=pincode.getText().toString();
        prameter.Country=country.getText().toString();
        prameter.GeoLocation=latlong.getText().toString();
        prameter.OrganizationId=sharedPreferences.getString(CommonUtils.shared_ORG_ID,"");
        System.out.println("ghghghfhggf"+latlong.getText().toString());
        AddDeviceApi.getRetrofitInstance(context).create(AddSiteInterface.class).registration2(prameter).enqueue(new Callback<AddSitegetset>() {
            @Override
            public void onResponse(Call<AddSitegetset> call, Response<AddSitegetset> response) {
                progressDialog.dismiss();
                if(response.body().getSuccess()==false){
                    Toast.makeText(AddSiteActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }else if( response.body().getSuccess()==true){
                    Intent intent=new Intent(AddSiteActivity.this,SiteActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(AddSiteActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddSitegetset> call, Throwable t) {

            }
        });
    }


    public void back(View view) {
        Intent intent = new Intent(AddSiteActivity.this, SiteActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

       GPSTracker  gpsTracker = new GPSTracker(AddSiteActivity.this);
        double mylat=gpsTracker.getLatitude();
        double mylom=gpsTracker.getLongitude();
        System.out.println("hjhgjhgghhj");
        LatLng coordinate = new LatLng(mylat, mylom);
        MarkerOptions markerOptions=new MarkerOptions().position(coordinate).draggable(true);
        CameraUpdate locations = CameraUpdateFactory.newLatLngZoom(coordinate, 15);
        mMap.animateCamera(locations);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinate, 15));
         String  s= String.valueOf(coordinate);
        String jj=(s.replaceAll("lat/lng:",""));
          kk=(jj.replaceAll("()",""));
        mMap.addMarker(markerOptions);
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(@NonNull Marker marker) {
                System.out.println("fdgfgfhh"+marker.getPosition());
            }

            @Override
            public void onMarkerDragEnd(@NonNull Marker marker) {
              LatLng latLng=marker.getPosition();
                String k= String.valueOf(marker.getPosition());

                Geocoder geocoder = new Geocoder(AddSiteActivity.this, Locale.getDefault());
                try {
                    List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    String j=addresses.get(0).getAddressLine(0);
                    System.out.println("hjjhgjgjh"+addresses);
                    address.setText(j);
                    country.setText(addresses.get(0).getCountryName());
                    stste.setText(addresses.get(0).getLocality());
                    District.setText(addresses.get(0).getLocality());
                    pincode.setText(addresses.get(0).getPostalCode());
                    ststename.setText(addresses.get(0).getAdminArea());
                    LatLng l=new LatLng(addresses.get(0).getLatitude(),addresses.get(0).getLongitude());
                    String kkkkk= String.valueOf(l);

                    String data=(kkkkk.replaceAll("lat/lng:",""));
                    String [] tempArray = data.substring(data.indexOf("(")+1, data.lastIndexOf(")")).split(",");
                    System.out.println("ghhggfhh"+tempArray);
                    double newlong= Double.parseDouble(tempArray[0]);
                    double newlat = Double.parseDouble(tempArray[1]);
                    latlong.setText(newlat+","+newlong);
                    System.out.println("hjjhgjhgjh"+addresses);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onMarkerDragStart(@NonNull Marker marker) {
                System.out.println("fdfdgfgf"+marker.getPosition());
                String k= String.valueOf(marker.getPosition());
                String jj=(s.replaceAll("lat/lng:",""));
                kk=(jj.replaceAll("()",""));
                String [] tempArray = kk.substring(kk.indexOf("(")+1, kk.lastIndexOf(")")).split(",");
                System.out.println("ghhggfhh"+tempArray);
                double newlong= Double.parseDouble(tempArray[0]);
                double newlat = Double.parseDouble(tempArray[1]);
                latlong.setText(newlat+","+newlong);
            }
        });
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;}
        mMap.setMyLocationEnabled(true);
    }
}