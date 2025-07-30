package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vts.R;

import com.example.vts.UserListPack.User;
import com.example.vts.UserListPack.UserListApi;
import com.example.vts.UserListPack.UserListInterface;
import com.example.vts.UserListPack.UserListgetset;
import com.example.vts.UserListPack.UsersListAdapter;

import com.example.vts.base.CommonUtils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;



import java.util.ArrayList;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    RecyclerView userrecycleview;
    List<UserListgetset> userListResponseData;
    Context context;
    Button edit_item_button;
    ImageView cancel_bottomsheet_item;
    View bottomSheet;
    EditText edit_item_name,edit_item_description,edit_item_id,edit_item_status;
    private BottomSheetBehavior mBottomSheetBehavior;
    UsersListAdapter adapter;
    SharedPreferences sharedPreferences;
    TextView Nodata;
    EditText Search;
    private List<User> mExampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        userrecycleview=findViewById(R.id.usersrecycleview);
        context=UserActivity.this;
        Nodata=findViewById(R.id.Nodata);
        Nodata.setVisibility(View.GONE);
        bottomSheet= findViewById(R.id.Category_sheet);
        sharedPreferences = getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        edit_item_description=findViewById(R.id.edit_item_description);
        edit_item_name= findViewById(R.id.edit_item_name);
        edit_item_id= findViewById(R.id.edit_item_id);
        edit_item_button= findViewById(R.id.edit_item_button);
        edit_item_status= findViewById(R.id.edit_item_status);
        cancel_bottomsheet_item= findViewById(R.id.cancel_bottomsheet_item);
        Search=findViewById(R.id.Search);

        cancel_bottomsheet_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
        findViewById(R.id.addrole).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserActivity.this,AddUserActivity.class);
                startActivity(intent);
            }
        });
        UserListApi.getRetrofitInstance(context).create(UserListInterface.class).registration2().enqueue(new Callback<UserListgetset>() {
            @Override
            public void onResponse(Call<UserListgetset> call, Response<UserListgetset> response) {
                System.out.println("gfffgg"+new Gson().toJson(response.body()));
                mExampleList=response.body().getUsers();
                if(response.body().getUsers().isEmpty()){
                    Nodata.setVisibility(View.VISIBLE);
                    userrecycleview.setVisibility(View.GONE);
                }
                else {
                    Nodata.setVisibility(View.GONE);
                    userrecycleview.setVisibility(View.VISIBLE);
                    adapter = new UsersListAdapter(context,response.body().getUsers());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UserActivity.this);
                    userrecycleview.setLayoutManager(linearLayoutManager);
                    userrecycleview.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<UserListgetset> call, Throwable t) {

            }
        });

    }


    public void back(View view) {
        Intent intent=new Intent(UserActivity.this,DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}