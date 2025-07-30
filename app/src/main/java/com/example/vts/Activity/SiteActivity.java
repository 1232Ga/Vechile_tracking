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

import com.example.vts.Activity.SiteDelete.SiteDeleteApi;
import com.example.vts.Activity.SiteDelete.SiteDeleteInterface;
import com.example.vts.Activity.UserDelete.UserDeletegetset;
import com.example.vts.R;
import com.example.vts.RoleListPack.UsersAdapter;
import com.example.vts.SitePackage.SiteAdapter;
import com.example.vts.SitePackage.SiteGetset;
import com.example.vts.SitePackage.SiteInterface;
import com.example.vts.SitePackage.SiteListApi;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SiteActivity extends AppCompatActivity {
Context context;
    RecyclerView rolesrecycleview;
    SiteAdapter usersAdapter;
    EditText Search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);
        rolesrecycleview=findViewById(R.id.rolesrecycleview);
        context=SiteActivity.this;

        findViewById(R.id.addrole).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SiteActivity.this,AddSiteActivity.class);
                startActivity(intent);
            }
        });
        Search=findViewById(R.id.Search);
        Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                usersAdapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        SiteListApi.getRetrofitInstance(context).create(SiteInterface.class).registration2().enqueue(new Callback<List<SiteGetset>>() {
            @Override
            public void onResponse(Call<List<SiteGetset>> call, Response<List<SiteGetset>> response) {
                System.out.println("hggjhgghghg"+new Gson().toJson(response.body()));
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SiteActivity.this);
                rolesrecycleview.setLayoutManager(linearLayoutManager);
                usersAdapter = new SiteAdapter(SiteActivity.this, response.body()) {
                    @Override
                    public void delete(String id,int position) {
                        Delete(id,position);
                    }
                };
                rolesrecycleview.setAdapter(usersAdapter);

            }

            @Override
            public void onFailure(Call<List<SiteGetset>> call, Throwable t) {

            }
        });
    }
    private void Delete(String id,int position) {
        final ProgressDialog progressDialog = new ProgressDialog(SiteActivity.this);
        progressDialog.setCancelable(false); // set cancelable to User
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();

        SiteDeleteApi.getRetrofitInstance(context).create(SiteDeleteInterface.class).registration(id).enqueue(new Callback<UserDeletegetset>() {
            @Override
            public void onResponse(Call<UserDeletegetset> call, Response<UserDeletegetset> response) {
                if(response.body().getIsSuccess().equalsIgnoreCase("true")){
                    usersAdapter.removeAt(position);
                    progressDialog.dismiss();
                    Intent intent=getIntent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(SiteActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(SiteActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserDeletegetset> call, Throwable t) {

            }
        });
    }
    public void back(View view) {
        Intent intent=new Intent(SiteActivity.this,DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}