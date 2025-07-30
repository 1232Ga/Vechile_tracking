package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.vts.LoginPack.Prameter;
import com.example.vts.R;
import com.example.vts.RegisterPack.RegisterApi;
import com.example.vts.RegisterPack.Registergetset;
import com.example.vts.RegisterPack.RegiterParamter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText orgnizationname,firstname,lastname,username,websiteurl,adddress,phonenumber,password,CONFIRMpassword;
    RequestQueue queue;
    Button Login;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String MobilePattern = "[0-9]{10}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        queue= Volley.newRequestQueue(RegisterActivity.this);
        username=findViewById(R.id.username);
        orgnizationname=findViewById(R.id.orgnizationname);
        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        websiteurl=findViewById(R.id.websiteurl);
        adddress=findViewById(R.id.adddress);
        phonenumber=findViewById(R.id.phonenumber);
        password=findViewById(R.id.password);
        CONFIRMpassword=findViewById(R.id.CONFIRMpassword);
        Login=findViewById(R.id.submit_btn);

        if (isNetworkConnectionAvailable()) {
            Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(orgnizationname.getText().toString().equalsIgnoreCase("")){
                        orgnizationname.setError("Enter Organization Name");
                        Toast.makeText(RegisterActivity.this, "Please Enter Organization Name", Toast.LENGTH_SHORT).show();
                    }else if(orgnizationname.getText().toString().length()<8){
                        Toast.makeText(RegisterActivity.this, "Please enter minimum 8 characters Organization Name", Toast.LENGTH_SHORT).show();
                    }
                    else if(firstname.getText().toString().equalsIgnoreCase("")){
                        firstname.setError("Enter First Name");
                        Toast.makeText(RegisterActivity.this, "Please Enter First Name", Toast.LENGTH_SHORT).show();
                    }
                    else if(firstname.getText().toString().length()<4){
                        Toast.makeText(RegisterActivity.this, "Please enter minimum 4 characters Name", Toast.LENGTH_SHORT).show();
                    }
                    else if(lastname.getText().toString().equalsIgnoreCase("")){
                        lastname.setError("Enter Last Name");
                        Toast.makeText(RegisterActivity.this, "Please Enter Last Name", Toast.LENGTH_SHORT).show();
                    }else if(username.getText().toString().equalsIgnoreCase("")){
                        username.setError("Enter User Name");
                        Toast.makeText(RegisterActivity.this, "Please Enter UserName", Toast.LENGTH_SHORT).show();
                    }else if(!username.getText().toString().trim().matches(emailPattern)){
                        username.setError("Enter Valid Email");
                        Toast.makeText(RegisterActivity.this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
                    }else if(adddress.getText().toString().equalsIgnoreCase("")){
                        adddress.setError("Enter Address Name");
                        Toast.makeText(RegisterActivity.this, "Please Enter Address", Toast.LENGTH_SHORT).show();
                    }else if(adddress.getText().toString().length()<8){
                        Toast.makeText(RegisterActivity.this, "Please enter minimum 8 characters address", Toast.LENGTH_SHORT).show();
                    }
                    else if(phonenumber.getText().toString().equalsIgnoreCase("")){
                        phonenumber.setError("Enter Phone Number");
                        Toast.makeText(RegisterActivity.this, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
                    }else if(!phonenumber.getText().toString().matches(MobilePattern)){
                        phonenumber.setError("Enter Valid 10 Digit Phone Number");
                        Toast.makeText(RegisterActivity.this, "Please enter valid 10 digit phone number", Toast.LENGTH_SHORT).show();
                    }
                    else if(password.getText().toString().equalsIgnoreCase("")){
                        password.setError("Enter Password");
                        Toast.makeText(RegisterActivity.this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
                    }
                    else if(password.getText().toString().length()<8 &&!isValidPassword(password.getText().toString())){
                        password.setError("Password must contain minimum 8 characters at least 1 Alphabet, 1 Number and 1 Special Character");
                        Toast.makeText(RegisterActivity.this, "Password must contain minimum 8 characters at least 1 Alphabet, 1 Number and 1 Special Character", Toast.LENGTH_SHORT).show();
                    }
                    else if(CONFIRMpassword.getText().toString().equalsIgnoreCase("")){
                        CONFIRMpassword.setError("Enter Confirm Password");
                        Toast.makeText(RegisterActivity.this, "Please Enter Your Confirm Password", Toast.LENGTH_SHORT).show();
                    }else if(!password.getText().toString().equals(CONFIRMpassword.getText().toString())){
                        Toast.makeText(RegisterActivity.this,"Password Not matching",Toast.LENGTH_SHORT).show();
                    }

                    else {
                        Category();
                    }
                }
            });
        }else {
            this.registerReceiver(this.mConnReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
            Toast.makeText(RegisterActivity.this, "Network Not Available", Toast.LENGTH_LONG).show();
        }
    }


    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }






    private void Category() {
        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        String phone=phonenumber.getText().toString();
        String org=orgnizationname.getText().toString();
        String f=firstname.getText().toString();
        String l=lastname.getText().toString();
        String e=username.getText().toString();
        String w=websiteurl.getText().toString();
        String a=adddress.getText().toString();
        String pa=password.getText().toString();;
        String cpa=CONFIRMpassword.getText().toString();;

        RegiterParamter prameter= new RegiterParamter();
        prameter.OrganizationName=org;
        prameter.FirstName=f;
        prameter.LastName=l;
        prameter.Email=e;
        prameter.Website=w;
        prameter.Address=a;
        prameter.PhoneNumber=phone;
        prameter.Password=pa;
        prameter.CurrentPassword=cpa;
        prameter.IsActive="false";
        prameter.IsDeleted="false";
        System.out.println("jhkhkjhjkhkj"+ prameter.OrganizationName+"  "+prameter.FirstName+" "+prameter.PhoneNumber);
        RegisterApi.getApiService().registration1(prameter).enqueue(new Callback<Registergetset>() {
          @Override
          public void onResponse(Call<Registergetset> call, Response<Registergetset> response) {
              progressDialog.dismiss();
                  System.out.println("kjhkjhhkjhkh"+response.body().getSuccess());
                  if(response.body().getSuccess()==false){
                      Toast.makeText(RegisterActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                  }else if( response.body().getSuccess()==true){
                      Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                      startActivity(intent);
                      Toast.makeText(RegisterActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                  }

          }

          @Override
          public void onFailure(Call<Registergetset> call, Throwable t) {
              System.out.println("rtytutjghjhjj"+t.getMessage());
          }
      });
    }


    public void loginpage(View view) {
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
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






}