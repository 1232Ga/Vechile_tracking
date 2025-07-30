package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vts.Activity.Adapter.PermissionAdapter;
import com.example.vts.Activity.Adapter.PermissionGetset;
import com.example.vts.Activity.RoleDetepack.DeleteApi;
import com.example.vts.Activity.RoleDetepack.DeleteInterface;
import com.example.vts.Activity.RoleDetepack.Deletegetset;
import com.example.vts.R;
import com.example.vts.RoleListPack.RoleListApi;
import com.example.vts.RoleListPack.RoleListgetset;
import com.example.vts.RoleListPack.RolesDetailsInterface;
import com.example.vts.RolePack.RoleApi;
import com.example.vts.RolePack.RoleParameter;
import com.example.vts.RolePack.Rolegetset;
import com.example.vts.base.CommonUtils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoleDetailsActivity extends AppCompatActivity {
String Siteid;
    EditText rolename,description;
    PermissionGetset adaddpermissin;
    RecyclerView permissions;
    SharedPreferences sharedPreferences;
    List<PermissionGetset> permissionGetsetList=new ArrayList<>();
    Context context;
    ImageView submenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_details);
        Siteid=getIntent().getStringExtra("Siteid");
        System.out.println("gfhfdhh"+Siteid);
        submenu = findViewById(R.id.submenu);
        description=findViewById(R.id.description);
        rolename=findViewById(R.id.rolename);
        permissions=findViewById(R.id.permissions);
        context=RoleDetailsActivity.this;
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
                        GridLayoutManager gridLayoutManager=new GridLayoutManager(RoleDetailsActivity.this,2, GridLayoutManager.VERTICAL,false);
                       // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RoleDetailsActivity.this);
                        permissions.setLayoutManager(gridLayoutManager);
                        PermissionAdapter usersAdapter1   = new PermissionAdapter(RoleDetailsActivity.this,permissionGetsetList);
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


//        GridLayoutManager gridLayoutManager=new GridLayoutManager(RoleDetailsActivity.this,2, GridLayoutManager.VERTICAL,false);
//        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RoleDetailsActivity.this);
//        permissions.setLayoutManager(gridLayoutManager);
//        PermissionAdapter usersAdapter1   = new PermissionAdapter(RoleDetailsActivity.this,jsoncreate());
//        permissions.setAdapter(usersAdapter1);

        submenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(RoleDetailsActivity.this, submenu);
                popup.getMenuInflater().inflate(R.menu.menu_example, popup.getMenu());
                popup.setForceShowIcon(true);
                //   popup.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.deletesite) {
                            BackPressed(Siteid);
                            //Toast.makeText(getApplicationContext(), "Delete clicked", Toast.LENGTH_SHORT).show();
                       }
                        else if (item.getItemId() == R.id.editsite) {
                            Intent intent = new Intent(RoleDetailsActivity.this, EditRoleActivity.class);
                            intent.putExtra("Siteid",Siteid);
                            startActivity(intent);
                            finish();
                            // Toast.makeText(getApplicationContext(), "Edit clicked", Toast.LENGTH_SHORT).show();
                        }

                        return true;
                    }
                });
                popup.show();
            }
        });


        sharedPreferences = getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);

//        edit_item_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final ProgressDialog progressDialog = new ProgressDialog(RoleDetailsActivity.this);
//                progressDialog.setCancelable(false); // set cancelable to false
//                progressDialog.setMessage("Please Wait"); // set message
//                progressDialog.show();
//                RoleParameter prameter= new RoleParameter();
//                prameter.UserRoleName=edit_item_name.getText().toString();
//                prameter.Notes=edit_item_description.getText().toString();
//                prameter.IsActive="true";
//                prameter.IsDeleted="false";
//                prameter.UserRoleId=edit_item_id.getText().toString();
//                prameter.OrganizationId=sharedPreferences.getString(CommonUtils.shared_ORG_ID,"");
//                RoleApi.getApiService().registration2(prameter).enqueue(new Callback<Rolegetset>() {
//                    @Override
//                    public void onResponse(Call<Rolegetset> call, Response<Rolegetset> response) {
//                        progressDialog.dismiss();
//                        System.out.println("jhhjjgjgj"+response.body().getSuccess());
//                        if(response.body().getSuccess()==false){
//                            Toast.makeText(RoleDetailsActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
//                        }else if( response.body().getSuccess()==true){
//                            Intent intent=new Intent(RoleDetailsActivity.this,RolesActivity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(intent);
//                            Toast.makeText(RoleDetailsActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Rolegetset> call, Throwable t) {
//
//                    }
//                });
//            }
//        });
    }


    public void BackPressed(String id) {

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setMessage("Do you want to Delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Delete(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
    }



    private void Delete(String position) {
        final ProgressDialog progressDialog = new ProgressDialog(RoleDetailsActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        DeleteApi.getRetrofitInstance(context).create(DeleteInterface.class).registration(position).enqueue(new Callback<Deletegetset>() {
            @Override
            public void onResponse(Call<Deletegetset> call, Response<Deletegetset> response) {
                System.out.println("hjgjhghghgg"+new Gson().toJson(response.body()));
                if(response.body().getIsSuccess().equalsIgnoreCase("true")){
                    progressDialog.dismiss();
                    Intent intent=new Intent(RoleDetailsActivity.this,RolesActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(RoleDetailsActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(RoleDetailsActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Deletegetset> call, Throwable t) {

            }
        });

    }












    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void back(View view) {
        Intent intent = new Intent(RoleDetailsActivity.this, RolesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}