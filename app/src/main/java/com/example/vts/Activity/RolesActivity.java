package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vts.Activity.RoleDetepack.DeleteApi;
import com.example.vts.Activity.RoleDetepack.DeleteInterface;
import com.example.vts.Activity.RoleDetepack.DeleteRoleParam;
import com.example.vts.Activity.RoleDetepack.Deletegetset;
import com.example.vts.LoginPack.ApiInterface;
import com.example.vts.LoginPack.Prameter;
import com.example.vts.R;
import com.example.vts.RoleListPack.RoleListApi;
import com.example.vts.RoleListPack.RoleListgetset;
import com.example.vts.RoleListPack.RolelistInterface;

import com.example.vts.RoleListPack.UsersAdapter;
import com.example.vts.RolePack.RoleApi;
import com.example.vts.RolePack.RoleParameter;
import com.example.vts.RolePack.Rolegetset;
import com.example.vts.base.CommonUtils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RolesActivity extends AppCompatActivity {
    RecyclerView rolesrecycleview;
    Button edit_item_button;
    ImageView cancel_bottomsheet_item;
    View bottomSheet;
    Context context;
    RequestQueue queue;
    EditText Search;
    EditText edit_item_name,edit_item_description,edit_item_id;
     BottomSheetBehavior mBottomSheetBehavior;
    UsersAdapter usersAdapter;
    SharedPreferences sharedPreferences;
    TextView Nodata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles);
        rolesrecycleview=findViewById(R.id.rolesrecycleview);
        Nodata=findViewById(R.id.Nodata);
        Nodata.setVisibility(View.GONE);
        Search=findViewById(R.id.Search);
        context=RolesActivity.this;
        bottomSheet= findViewById(R.id.Category_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        edit_item_description=findViewById(R.id.edit_item_description);
        edit_item_name= findViewById(R.id.edit_item_name);
        edit_item_id= findViewById(R.id.edit_item_id);
        edit_item_button= findViewById(R.id.edit_item_button);
        cancel_bottomsheet_item= findViewById(R.id.cancel_bottomsheet_item);
        sharedPreferences = getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);
        cancel_bottomsheet_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
        findViewById(R.id.addrole).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RolesActivity.this,AddRoleActivity.class);
                startActivity(intent);
            }
        });
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
        edit_item_button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final ProgressDialog progressDialog = new ProgressDialog(RolesActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        RoleParameter prameter= new RoleParameter();
        prameter.UserRoleName=edit_item_name.getText().toString();
        prameter.Notes=edit_item_description.getText().toString();
        prameter.IsActive="true";
        prameter.IsDeleted="false";
        prameter.UserRoleId=edit_item_id.getText().toString();
        prameter.OrganizationId=sharedPreferences.getString(CommonUtils.shared_ORG_ID,"");
        RoleApi.getApiService().registration2(prameter).enqueue(new Callback<Rolegetset>() {
            @Override
            public void onResponse(Call<Rolegetset> call, Response<Rolegetset> response) {
                progressDialog.dismiss();
                System.out.println("jhhjjgjgj"+response.body().getSuccess());
                if(response.body().getSuccess()==false){
                    Toast.makeText(RolesActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }else if( response.body().getSuccess()==true){
                    Intent intent=new Intent(RolesActivity.this,RolesActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(RolesActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Rolegetset> call, Throwable t) {

            }
        });
    }
});

        RoleListApi.getRetrofitInstance(context).create(RolelistInterface.class).registration2().enqueue(new Callback<List<RoleListgetset>>() {
            @Override
            public void onResponse(Call<List<RoleListgetset>> call, Response<List<RoleListgetset>> response) {

                System.out.println("kjkljjjk"+new Gson().toJson(response.body()));
                if(response.body().isEmpty()){
                    Nodata.setVisibility(View.VISIBLE);
                    rolesrecycleview.setVisibility(View.GONE);
                }else{
                    Nodata.setVisibility(View.GONE);
                    rolesrecycleview.setVisibility(View.VISIBLE);
                    for(RoleListgetset data:response.body()){
                        System.out.println("jhhjgj"+data.getIsDeleted());
                        boolean jj=data.getIsDeleted();
                        if(jj==true){
                            System.out.println("hjhjhjghjhgjghj");
                            rolesrecycleview.setVisibility(View.GONE);
                        }else {
                            System.out.println("hghgjhgjhghj");
                            rolesrecycleview.setVisibility(View.VISIBLE);

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RolesActivity.this);
                            rolesrecycleview.setLayoutManager(linearLayoutManager);
                            usersAdapter   = new UsersAdapter(RolesActivity.this, response.body()) {
                                @Override
                                public void delete(int id,String position) {
                                    Delete(id,position);
                                }

                                @Override
                                public void edit(String id, String rolename, String orgname) {
                                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                                    edit_item_name.setText(rolename);
                                    edit_item_description.setText(orgname);
                                    edit_item_id.setText(id);
                                }
                            };
                            rolesrecycleview.setAdapter(usersAdapter);

                        }


                        String kkk=data.getUserRoleName();
                        if(kkk==null){
                            rolesrecycleview.setVisibility(View.GONE);
                            System.out.println("jhhjhgj");
                        }
                        else {
                            rolesrecycleview.setVisibility(View.VISIBLE);
                            System.out.println("hhjgggjhgj");
                            rolesrecycleview.setVisibility(View.VISIBLE);
                            rolesrecycleview.setVisibility(View.VISIBLE);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RolesActivity.this);
                            rolesrecycleview.setLayoutManager(linearLayoutManager);
                            usersAdapter   = new UsersAdapter(RolesActivity.this, response.body()) {
                                @Override
                                public void delete(int id,String position) {
                                    Delete(id,position);
                                }

                                @Override
                                public void edit(String id, String rolename, String orgname) {
                                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                                    edit_item_name.setText(rolename);
                                    edit_item_description.setText(orgname);
                                    edit_item_id.setText(id);
                                }
                            };
                            rolesrecycleview.setAdapter(usersAdapter);
                        }

                    }
                }







            }
            @Override
            public void onFailure(Call<List<RoleListgetset>> call, Throwable t) {
                System.out.println("hjjgjhg__"+call.toString());
            }
        });
    }



    private void Delete(int id,String position) {
        final ProgressDialog progressDialog = new ProgressDialog(RolesActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        DeleteApi.getRetrofitInstance(context).create(DeleteInterface.class).registration(position).enqueue(new Callback<Deletegetset>() {
            @Override
            public void onResponse(Call<Deletegetset> call, Response<Deletegetset> response) {
                System.out.println("hjgjhghghgg"+new Gson().toJson(response.body()));
                if(response.body().getIsSuccess().equalsIgnoreCase("true")){
                    usersAdapter.removeAt(id);
                    progressDialog.dismiss();
                    Intent intent=new Intent(RolesActivity.this,RolesActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(RolesActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(RolesActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Deletegetset> call, Throwable t) {

            }
        });

    }


    public void back(View view) {
        Intent intent=new Intent(RolesActivity.this,DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
