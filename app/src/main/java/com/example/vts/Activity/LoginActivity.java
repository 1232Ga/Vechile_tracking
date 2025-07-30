package com.example.vts.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import com.example.vts.LiveTracking.GenerateAuthTokenAPI;
import com.example.vts.LiveTracking.GetAuthApi;
import com.example.vts.LiveTracking.GetAuthGetSet;
import com.example.vts.LiveTracking.ResultData;
import com.example.vts.LoginPack.Api;
import com.example.vts.LoginPack.Example;
import com.example.vts.LoginPack.Prameter;
import com.example.vts.R;
import com.example.vts.base.CommonUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.gson.JsonObject;


import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.vts.base.CommonUtils.shared_EmailId;
import static com.example.vts.base.CommonUtils.shared_ORG_ID;
import static com.example.vts.base.CommonUtils.shared_TOKENS;
import static com.example.vts.base.CommonUtils.shared_USER_ID;
import static com.example.vts.base.CommonUtils.shared_User_Name;


public class LoginActivity extends AppCompatActivity {
EditText username,password;
String Emailname,Password;
RequestQueue queue;
Button Login;
SharedPreferences sharedpreferences;
SharedPreferences.Editor prefEditor;

    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    private GoogleApiClient googleApiClient;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        Login=findViewById(R.id.submit_btn);
       sharedpreferences = getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);
        prefEditor = sharedpreferences.edit();
        queue= Volley.newRequestQueue(LoginActivity.this);

        googleApiClient = getAPIClientInstance();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }



        requestGPSSettings();
        GetAuthApi.getApiService().registration2().enqueue(new Callback<GetAuthGetSet>() {
            @Override
            public void onResponse(Call<GetAuthGetSet> call, Response<GetAuthGetSet> response) {
                System.out.println("jkhkhhjkhkhjk"+response.body().getResultData());
                ResultData data=response.body().getResultData();
                System.out.println("hjggjggjg"+data);
                GenerateAuthTokenAPI authTokenAPI=data.getGenerateAuthTokenAPI();
                System.out.println("kjkhjhjj"+authTokenAPI);
                String token=authTokenAPI.getToken();

                System.out.println("jkhkhjkhj"+token);
            }
            @Override
            public void onFailure(Call<GetAuthGetSet> call, Throwable t) {

            }
        });
        findViewById(R.id.forgetpasword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(LoginActivity.this,ForgetPasswordActivity.class);
//                startActivity(intent);
            }
        });




        if (isNetworkConnectionAvailable()) {
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
if(username.getText().toString().equalsIgnoreCase("")){
    Toast.makeText(LoginActivity.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
}else if(password.getText().toString().equalsIgnoreCase("")){
    Toast.makeText(LoginActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
}else {
Category();
}
            }
        });
        }else {
            this.registerReceiver(this.mConnReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
            Toast.makeText(LoginActivity.this, "Network Not Available", Toast.LENGTH_LONG).show();
        }
    }


    private GoogleApiClient getAPIClientInstance() {
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API).build();
        return mGoogleApiClient;
    }



    private void requestGPSSettings() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        locationRequest.setInterval(2000);
        locationRequest.setFastestInterval(500);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);
        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i("", "All location settings are satisfied.");
                      //  Toast.makeText(getApplication(), "GPS is already enable", Toast.LENGTH_SHORT).show();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i("", "Location settings are not satisfied. Show the user a dialog to" + "upgrade location settings ");
                        try {
                            status.startResolutionForResult(LoginActivity.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            Log.e("Applicationsett", e.toString());
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i("", "Location settings are inadequate, and cannot be fixed here. Dialog " + "not created.");
                        Toast.makeText(getApplication(), "Location settings are inadequate, and cannot be fixed here", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }















    private void Category() {
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        Prameter prameter= new Prameter();
        Emailname="admin@garudauav.com";
        Password="Garuda@123";
        System.out.println("jhkhkjhjkhkj"+Emailname+"  "+Password);
        prameter.Email=Emailname;
        prameter.Password=Password;
        System.out.println("jhkhkjhjkhkj"+ prameter.Email+"  "+prameter.Password);
        Api.getApiService().registration(prameter).enqueue(new Callback<Example>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.isSuccessful())
                {
                    System.out.println("jkhkjhj"+response.body());
                    if(response.body().getIsAuthenticated()==false){
                        progressDialog.dismiss();

                        Toast.makeText(LoginActivity.this, "InValid credentials", Toast.LENGTH_SHORT).show();
                    }else {
                        progressDialog.dismiss();

                        System.out.println("Aditya"+response.body().getToken());
                        try {
                            String Token=response.body().getToken();
                            System.out.println("jhkhkjhkh"+Token);
                           String jj= response.body().getToken();
                            java.util.Base64.Decoder decoder = java.util.Base64.getUrlDecoder();
                            String[] split_string = jj.split("\\.");
                            String payloadJson = new String(decoder.decode(split_string[1]));

                            System.out.println("JWTBody"+payloadJson);
                            JSONObject jsonObject = new JSONObject(payloadJson);;
                            String orgid=jsonObject.getString("OrganizationId");
                            String UserId=jsonObject.getString("UserId");
                            String EmailId=jsonObject.getString("EmailId");
                            String UserName=jsonObject.getString("UserName");
                            System.out.println("knkjhjkhk"+UserId);
                            Intent intent=new Intent(LoginActivity.this, DashboardActivity.class);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString(shared_USER_ID, UserId);
                            editor.putString(shared_TOKENS, Token);
                            editor.putString(shared_ORG_ID, orgid);
                            editor.putString(shared_EmailId, EmailId);
                            editor.putString(shared_User_Name, UserName);
                            System.out.println("jkjhkjhh"+Token);
                             editor.commit();
                            startActivity( intent);
                            Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        //Log.d("msg","m"+response.body().getLengthUnit());
                    }

                }else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "InValid credentials", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });




    }




    public boolean isNetworkConnectionAvailable(){
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();
        if(isConnected) {
            Log.d("Network", "Connected");
            return true;
        }
        else{
            checkNetworkConnection();
            Log.d("Network","Not Connected");
            return false;
        }
    }
    public void checkNetworkConnection(){
        android.app.AlertDialog.Builder builder =new android.app.AlertDialog.Builder(this);
        builder.setTitle("No internet Connection");
        builder.setMessage("Please turn on internet connection to continue");
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog1, int which) {
                dialog1.dismiss();
                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
            }
        });
        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private BroadcastReceiver mConnReceiver = new BroadcastReceiver()
    {
        public void onReceive(Context context, Intent intent)
        {
            boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
            String reason = intent.getStringExtra(ConnectivityManager.EXTRA_REASON);
            boolean isFailover = intent.getBooleanExtra(ConnectivityManager.EXTRA_IS_FAILOVER, false);

            NetworkInfo currentNetworkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            NetworkInfo otherNetworkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_OTHER_NETWORK_INFO);

            if (currentNetworkInfo.isConnected())
            {
                Log.d("=============", "Connected");
                finish();
                startActivity(getIntent());
                Toast.makeText(getApplicationContext(), "Connected",Toast.LENGTH_LONG).show();
            }
            else
            {
                Log.d("============", "Not Connected");
                Toast.makeText(getApplicationContext(), "Not Connected",
                        Toast.LENGTH_LONG).show();
            }
        }
    };


    @Override
    public void onBackPressed() {

        finishAffinity();
    }
    public void sigunpu(View view) {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }






}