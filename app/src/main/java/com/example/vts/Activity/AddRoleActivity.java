package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.vts.Activity.Adapter.PermissionAdapter;
import com.example.vts.Activity.Adapter.PermissionGetset;
import com.example.vts.Activity.KmReport.KmreportVechileListAdapter;
import com.example.vts.Activity.RoleDetepack.SpacesItemDecoration;
import com.example.vts.LoginPack.Prameter;
import com.example.vts.R;
import com.example.vts.RolePack.RoleApi;
import com.example.vts.RolePack.RoleParameter;
import com.example.vts.RolePack.Rolegetset;
import com.example.vts.base.CommonUtils;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRoleActivity extends AppCompatActivity {
    EditText rolename,description;
    Button Login,cancelbtn;
    String s;
    PermissionGetset adaddpermissin;
    String des;
    RecyclerView permissions;

    SharedPreferences sharedPreferences;
    List<PermissionGetset> permissionGetsetList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_role);
        rolename=findViewById(R.id.rolename);
        description=findViewById(R.id.description);
        Login=findViewById(R.id.submit_btn);
        cancelbtn=findViewById(R.id.cancelbtn);
        permissions=findViewById(R.id.permissions);
        sharedPreferences = getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("hjhjhjh"+permissionGetsetList.get(0).isAdd());
                if(rolename.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(AddRoleActivity.this, "Please Enter Role Name", Toast.LENGTH_SHORT).show();
                }else {
                    category();
                }
            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
           Intent intent=new Intent(AddRoleActivity.this,RolesActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
           startActivity(intent);
          }
          });
          permissions.setVisibility(View.VISIBLE);
        adaddpermissin=new PermissionGetset("Role","scope.role.add","scope.role.view","scope.role.delete","9","10","11",false,false,false,"100");
         permissionGetsetList.add(adaddpermissin);
         adaddpermissin=new PermissionGetset("Site","scope.site.add","scope.site.view","scope.site.delete","12","13","14",false,false,false,"101");
         permissionGetsetList.add(adaddpermissin);
         adaddpermissin=new PermissionGetset("Users","scope.user.add","scope.user.view","scope.user.delete","15","16","17",false,false,false,"102");
         permissionGetsetList.add(adaddpermissin);
         adaddpermissin=new PermissionGetset("Device","scope.device.add","scope.device.view","scope.device.delete","18","19","20",false,false,false,"103");
         permissionGetsetList.add(adaddpermissin);
         adaddpermissin=new PermissionGetset("Driver","scope.driver.add","scope.driver.view","scope.driver.delete","21","22","23",false,false,false,"104");
         permissionGetsetList.add(adaddpermissin);
         adaddpermissin=new PermissionGetset("Vehicle","scope.vechicle.add","scope.vechicle.view","scope.vechicle.delete","24","25","26",false,false,false,"105");
         permissionGetsetList.add(adaddpermissin);
         adaddpermissin=new PermissionGetset("Trip","scope.trip.add","scope.trip.view","scope.trip.delete","27","28","29",false,false,false,"106");
         permissionGetsetList.add(adaddpermissin);
         adaddpermissin=new PermissionGetset("Reports","scope.report.add","scope.report.view","scope.report.delete","30","31","32",false,false,false,"107");
         permissionGetsetList.add(adaddpermissin);
         adaddpermissin=new PermissionGetset("Vehicle history","scope.vehiclehistory.add","scope.vehiclehistory.view","scope.vehiclehistory.delete","33","34","35",false,false,false,"108");
         permissionGetsetList.add(adaddpermissin);
         adaddpermissin=new PermissionGetset("Vehicle owner","scope.vechicleowner.add","scope.vechicleowner.view","scope.vechicleowner.delete","36","37","38",false,false,false,"109");
         permissionGetsetList.add(adaddpermissin);

        System.out.println("hkjhkhjhj"+permissionGetsetList.size());
       // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddRoleActivity.this);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(AddRoleActivity.this,2, GridLayoutManager.VERTICAL,false);
        permissions.setLayoutManager(gridLayoutManager);
        permissions.addItemDecoration(new SpacesItemDecoration(0));
        PermissionAdapter usersAdapter1= new PermissionAdapter(AddRoleActivity.this,permissionGetsetList);
        permissions.setAdapter(usersAdapter1);
    }



    private void category() {
        final ProgressDialog progressDialog = new ProgressDialog(AddRoleActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        System.out.println("kjjkhjkhjkhkl"+permissionGetsetList.size());
        JSONArray array = new JSONArray();
        for (int i = 0; i <permissionGetsetList.size() ; i++) {
            try {
                JSONArray jsonArray = new JSONArray();
                for (int j = 0; j < 3; j++) {
                    if (j == 0) {
                        JSONObject object1 = new JSONObject();
                        object1.put("id", permissionGetsetList.get(i).getAddid());
                        object1.put("parent_permission_id", permissionGetsetList.get(i).getPermissionid());
                        object1.put("display_name", "Add");
                        object1.put("scope_value", permissionGetsetList.get(i).getScopevalueadd());
                        if (permissionGetsetList.get(i).isAdd()==true) {
                            object1.put("isSelected", "true");
                        }
                        else {
                            object1.put("isSelected", "false");
                        }
                        System.out.println("gjhgjgg"+object1);
                        jsonArray.put(object1);
                    }
                    else if (j == 1) {
                        JSONObject object1 = new JSONObject();
                        object1.put("id", permissionGetsetList.get(i).getViewid());
                        object1.put("parent_permission_id", permissionGetsetList.get(i).getPermissionid());
                        object1.put("display_name", "View");
                        object1.put("scope_value", permissionGetsetList.get(i).getScopevalueview());
                        if (permissionGetsetList.get(i).isView()==true) {
                            object1.put("isSelected", "true");
                        }
                        else {
                            object1.put("isSelected", "false");
                        }
                        jsonArray.put(object1);
                    }
                    else if (j == 2) {
                        JSONObject object1 = new JSONObject();
                        object1.put("id", permissionGetsetList.get(i).getDeleteid());
                        object1.put("parent_permission_id", permissionGetsetList.get(i).getPermissionid());
                        object1.put("display_name", "Delete");
                        object1.put("scope_value", permissionGetsetList.get(i).getScopevaluedelete());
                        if (permissionGetsetList.get(i).isDelete()==true) {
                            object1.put("isSelected", "true");
                        }
                        else {
                            object1.put("isSelected", "false");
                        }
                        jsonArray.put(object1);
                    }
                }
                    JSONObject object = new JSONObject();
                    System.out.println("ghghjhjhkjhk"+ permissionGetsetList.get(i).getPermissionid());
                    System.out.println("fddfgfghfhgfh"+ permissionGetsetList.get(i).getTiltle());
                    object.put("id", permissionGetsetList.get(i).getPermissionid());
                    object.put("parent_permission_id", "0");
                    object.put("display_name", permissionGetsetList.get(i).getTiltle());
                    object.put("childdata", jsonArray);
                    array.put(object);
                    System.out.println("hgjgjgjgjgj"+array);
                    s= String.valueOf(array);
            }catch (Exception e){}
        }
        des=description.getText().toString();
        RoleParameter prameter= new RoleParameter();
        prameter.UserRoleName=rolename.getText().toString();
        prameter.Notes=des;
        prameter.IsActive="true";
        prameter.IsDeleted="false";
        prameter.Scope=s;
        prameter.OrganizationId=sharedPreferences.getString(CommonUtils.shared_ORG_ID,"");
        System.out.println("hjgjgjhgjgj"+s);
        RoleApi.getApiService().registration2(prameter).enqueue(new Callback<Rolegetset>() {
            @Override
            public void onResponse(Call<Rolegetset> call, Response<Rolegetset> response) {
                progressDialog.dismiss();
                System.out.println("jhhjjgjgj"+response.body().getSuccess());
                if(response.body().getSuccess()==false){
                    Toast.makeText(AddRoleActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }else if( response.body().getSuccess()==true){
                    Intent intent=new Intent(AddRoleActivity.this,RolesActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(AddRoleActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Rolegetset> call, Throwable t) {

            }
        });
    }
    public void back(View view) {
        Intent intent=new Intent(AddRoleActivity.this,RolesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}