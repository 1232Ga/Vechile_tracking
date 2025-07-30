package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vts.Activity.Adapter.PermissionAdapter;
import com.example.vts.Activity.Adapter.PermissionGetset;
import com.example.vts.Activity.AddDevice.AddDeviceApi;
import com.example.vts.R;
import com.example.vts.RoleListPack.RoleListApi;
import com.example.vts.RoleListPack.RoleListgetset;
import com.example.vts.RoleListPack.RolesDetailsInterface;
import com.example.vts.RolePack.RoleApi;
import com.example.vts.RolePack.RoleInterface;
import com.example.vts.RolePack.RoleParameter;
import com.example.vts.RolePack.Rolegetset;
import com.example.vts.base.CommonUtils;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditRoleActivity extends AppCompatActivity {
String Siteid;
    EditText rolename,description;
    PermissionGetset adaddpermissin;
    RecyclerView permissions;
    SharedPreferences sharedPreferences;
    List<PermissionGetset> permissionGetsetList=new ArrayList<>();
    Context context;
    String s;
    String des;
    Button Login,cancelbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_role);
        Siteid=getIntent().getStringExtra("Siteid");
        System.out.println("gfhfdhh"+Siteid);
        description=findViewById(R.id.description);
        rolename=findViewById(R.id.rolename);
        permissions=findViewById(R.id.permissions);
        context=EditRoleActivity.this;
        Login=findViewById(R.id.submit_btn);
        cancelbtn=findViewById(R.id.cancelbtn);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EditRoleActivity.this,RolesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        sharedPreferences = getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);
        permissions.setVisibility(View.VISIBLE);
        RoleListApi.getRetrofitInstance(context).create(RolesDetailsInterface.class).registration(Siteid).enqueue(new Callback<RoleListgetset>() {
            @Override
            public void onResponse(Call<RoleListgetset> call, Response<RoleListgetset> response) {
                System.out.println("fdgfgfhhgfh"+new Gson().toJson(response.body()));
                rolename.setText(response.body().getUserRoleName());
                description.setText(response.body().getNotes());
                RoleListgetset roleListgetset=response.body();
                System.out.println("jkhkjhjkghjk"+roleListgetset.getScope());
                try {
                    JSONArray array= new JSONArray(roleListgetset.getScope());
                    for (int i = 0; i <array.length() ; i++) {
                        JSONObject list = array.getJSONObject(i);
                        String idd=list.getString("id");
                        String display_nametop=list.getString("display_name");
                        JSONArray memberlist = list.getJSONArray("childdata");
                        System.out.println("ghghfhh"+memberlist.length());
                        for (int j = 0; j <memberlist.length(); j++) {
                            JSONObject list1 = memberlist.getJSONObject(j);
                            String childid = list1.getString("id");
                            System.out.println("fgffgfgf"+childid);
                            String parent_permission_id = list1.getString("parent_permission_id");
                            adaddpermissin=new
                                    PermissionGetset(display_nametop,
                                    memberlist.getJSONObject(0).getString("scope_value"),
                                    memberlist.getJSONObject(1).getString("scope_value"),
                                    memberlist.getJSONObject(2).getString("scope_value"),
                                    memberlist.getJSONObject(0).getString("id"),
                                    memberlist.getJSONObject(1).getString("id"),
                                    memberlist.getJSONObject(2).getString("id"),
                                    memberlist.getJSONObject(0).getBoolean("isSelected"),
                                    memberlist.getJSONObject(1).getBoolean("isSelected"),
                                    memberlist.getJSONObject(2).getBoolean("isSelected"),
                                    parent_permission_id);
                            System.out.println("ghffhgfh"+memberlist.getJSONObject(1).getBoolean("isSelected"));
                        }
                        permissionGetsetList.add(adaddpermissin);
                        GridLayoutManager gridLayoutManager=new GridLayoutManager(EditRoleActivity.this,2, GridLayoutManager.VERTICAL,false);
                        // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RoleDetailsActivity.this);
                        permissions.setLayoutManager(gridLayoutManager);
                        PermissionAdapter usersAdapter1   = new PermissionAdapter(EditRoleActivity.this,permissionGetsetList);
                        permissions.setAdapter(usersAdapter1);
                        System.out.println("gfgdfhgfhgfhf"+array);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<RoleListgetset> call, Throwable t) {

            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category();
            }
        });

    }
    private void category() {
        final ProgressDialog progressDialog = new ProgressDialog(EditRoleActivity.this);
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
                        } else {
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
                        } else {
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
                        } else {
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
        prameter.UserRoleId=Siteid;
        prameter.OrganizationId=sharedPreferences.getString(CommonUtils.shared_ORG_ID,"");
        System.out.println("hjgjgjhgjgj"+s);
        AddDeviceApi.getRetrofitInstance(context).create(RoleInterface.class).registration2(prameter).enqueue(new Callback<Rolegetset>() {
            @Override
            public void onResponse(Call<Rolegetset> call, Response<Rolegetset> response) {
                if(response.body().getSuccess()==false){
                    Toast.makeText(EditRoleActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }else if( response.body().getSuccess()==true){
                    Intent intent=new Intent(EditRoleActivity.this,RolesActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(EditRoleActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Rolegetset> call, Throwable t) {

            }
        });
    }
    public void back(View view) {
        Intent intent = new Intent(EditRoleActivity.this, RolesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}