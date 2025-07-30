package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vts.Activity.Adapter.PermissionAdapter;
import com.example.vts.Activity.Adapter.PermissionGetset;
import com.example.vts.R;
import com.example.vts.UserListPack.User;
import com.example.vts.UserListPack.UserDetailInterface;
import com.example.vts.UserListPack.UserListApi;
import com.example.vts.base.CommonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity {
  TextView username;
  EditText ownername,mobilenumber,email,orgname,addressss,websiteurl;
    SharedPreferences sharedpreferences;
    String Userid;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        sharedpreferences = getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);
        Userid=sharedpreferences.getString(CommonUtils.shared_USER_ID, "");
        username=findViewById(R.id.username);
        ownername=findViewById(R.id.ownername);
        context=UserProfileActivity.this;
        mobilenumber=findViewById(R.id.mobilenumber);
        email=findViewById(R.id.email);
        orgname=findViewById(R.id.orgname);
        addressss=findViewById(R.id.addressss);
        websiteurl=findViewById(R.id.websiteurl);
        UserListApi.getRetrofitInstance(context).create(UserDetailInterface.class).registration(Userid).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println("jkhjhhj"+response.body().getFirstName());
                //email.setText(response.body().getUserName());
                username.setText(response.body().getFullName());
                ownername.setText(response.body().getFullName());
                email.setText(response.body().getEmail());
                mobilenumber.setText(response.body().getPhoneNumber());
                orgname.setText(response.body().getOrganizationName());
                websiteurl.setText(response.body().getRole());
                addressss.setText(response.body().getPassword());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
    public void back(View view) {
        Intent intent=new Intent(UserProfileActivity.this,DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}