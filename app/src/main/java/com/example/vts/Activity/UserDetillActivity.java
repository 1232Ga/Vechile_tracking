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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vts.Activity.Adapter.PermissionAdapter;
import com.example.vts.Activity.Adapter.PermissionGetset;
import com.example.vts.Activity.UserDelete.UserDeleteApi;
import com.example.vts.Activity.UserDelete.UserDeleteInterface;
import com.example.vts.Activity.UserDelete.UserDeletegetset;
import com.example.vts.R;
import com.example.vts.RoleListPack.RoleListgetset;
import com.example.vts.UserListPack.User;
import com.example.vts.UserListPack.UserDetailInterface;
import com.example.vts.UserListPack.UserListApi;
import com.example.vts.base.CommonUtils;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetillActivity extends AppCompatActivity {

    Context context;
    String Userid;
     ImageView submenu;
     TextView firstname,lastname,phonenumber,password,email,rolename;
    SharedPreferences sharedPreferences;
    RecyclerView permissions;
    PermissionGetset adaddpermissin;
    List<PermissionGetset> permissionGetsetList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detill);
        submenu = findViewById(R.id.submenu);
        context=UserDetillActivity.this;
        Userid=getIntent().getStringExtra("Userid");
        System.out.println("jhkhjhj"+Userid);

        context=UserDetillActivity.this;
        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        rolename=findViewById(R.id.rolename);
        email=findViewById(R.id.Emailname);
        phonenumber=findViewById(R.id.phonenumber);
        password=findViewById(R.id.password);
        permissions=findViewById(R.id.permissions);
        sharedPreferences = getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);
        UserListApi.getRetrofitInstance(context).create(UserDetailInterface.class).registration(Userid).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println("jkhjhhj"+response.body().getFirstName());
                //email.setText(response.body().getUserName());
                firstname.setText(response.body().getFirstName());
                lastname.setText(response.body().getLastName());
                email.setText(response.body().getEmail());
                phonenumber.setText(response.body().getPhoneNumber());
                password.setText(response.body().getPassword());
                rolename.setText(response.body().getRole());
                try {
                    System.out.println("gjhgggh"+response.body().getScope());
                    JSONArray array=new JSONArray(response.body().getScope());
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
                        GridLayoutManager gridLayoutManager=new GridLayoutManager(UserDetillActivity.this,2, GridLayoutManager.VERTICAL,false);
                       // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UserDetillActivity.this);
                        permissions.setLayoutManager(gridLayoutManager);
                        PermissionAdapter usersAdapter1   = new PermissionAdapter(UserDetillActivity.this,permissionGetsetList);
                        permissions.setAdapter(usersAdapter1);
                        System.out.println("gfgdfhgfhgfhf"+array);
                    }


                } catch (JSONException e) { e.printStackTrace();}
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        submenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(UserDetillActivity.this, submenu);
                popup.getMenuInflater().inflate(R.menu.menu_example, popup.getMenu());
                popup.setForceShowIcon(true);
                //   popup.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.deletesite) {
                            BackPressed(Userid);
                        } else if (item.getItemId() == R.id.editsite) {
                            Intent intent = new Intent(UserDetillActivity.this, EditUserActivity.class);
                            intent.putExtra("Userid",Userid);
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

    private void Delete(String id) {
        final ProgressDialog progressDialog = new ProgressDialog(UserDetillActivity.this);
        progressDialog.setCancelable(false); // set cancelable to User
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        System.out.println("hhjhjj"+id+"    ");
        UserDeleteApi.getRetrofitInstance(context).create(UserDeleteInterface.class).registration(id).enqueue(new Callback<UserDeletegetset>() {
            @Override
            public void onResponse(Call<UserDeletegetset> call, Response<UserDeletegetset> response) {
                System.out.println("hjgjhghghgg"+new Gson().toJson(response.body()));
                if(response.body().getIsSuccess().equalsIgnoreCase("true")){
                    System.out.println("jkjkhkjhjhjgh");
                    progressDialog.dismiss();
                    Intent intent=getIntent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(UserDetillActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(UserDetillActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserDeletegetset> call, Throwable t) {

            }
        });

    }
    public void back(View view) {
        onBackPressed();
    }
}