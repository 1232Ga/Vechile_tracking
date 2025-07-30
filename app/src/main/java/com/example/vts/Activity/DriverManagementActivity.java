package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vts.Activity.DeleteDriver.DriverDeleteApi;
import com.example.vts.Activity.DeleteDriver.DriverDeleteInterface;
import com.example.vts.Activity.UserDelete.UserDeletegetset;
import com.example.vts.DevicePack.DeviceListAdapter;
import com.example.vts.DriverPack.DriverApi;
import com.example.vts.DriverPack.DriverInterface;
import com.example.vts.DriverPack.DriverListAdapter;
import com.example.vts.DriverPack.Drivergetset;
import com.example.vts.R;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverManagementActivity extends AppCompatActivity {
    Context context;
    RecyclerView rolesrecycleview;
    DriverListAdapter usersAdapter;
    EditText Search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_management);
        rolesrecycleview=findViewById(R.id.rolesrecycleview);
        context=DriverManagementActivity.this;
        Search=findViewById(R.id.Search);
        findViewById(R.id.addrole).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DriverManagementActivity.this,AddDriverActivity.class);
                startActivity(intent);
            }
        });
//        Search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                usersAdapter.getFilter().filter(s);
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
        DriverApi.getRetrofitInstance(context).create(DriverInterface.class).registration2().enqueue(new Callback<List<Drivergetset>>() {
            @Override
            public void onResponse(Call<List<Drivergetset>> call, Response<List<Drivergetset>> response) {
                System.out.println("uugjgjghgjgj"+new Gson().toJson(response.body()));
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DriverManagementActivity.this);
                rolesrecycleview.setLayoutManager(linearLayoutManager);
                usersAdapter   = new DriverListAdapter(DriverManagementActivity.this, response.body()) {
                    @Override
                    public void delete(int id,String position) {
                    }
                };
                rolesrecycleview.setAdapter(usersAdapter);
            }

            @Override
            public void onFailure(Call<List<Drivergetset>> call, Throwable t) {

            }
        });
    }
    public void back(View view) {
        Intent intent=new Intent(DriverManagementActivity.this,DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}