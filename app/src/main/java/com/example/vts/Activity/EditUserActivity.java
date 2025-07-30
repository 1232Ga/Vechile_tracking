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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vts.Activity.Adapter.PermissionAdapter;
import com.example.vts.Activity.Adapter.PermissionGetset;
import com.example.vts.Activity.AddDevice.AddDeviceApi;
import com.example.vts.Activity.DashboardApi.Country;
import com.example.vts.R;
import com.example.vts.RoleListPack.RoleListApi;
import com.example.vts.RoleListPack.RoleListgetset;
import com.example.vts.RoleListPack.RolelistInterface;
import com.example.vts.UserListPack.User;
import com.example.vts.UserListPack.UserDetailInterface;
import com.example.vts.UserListPack.UserListApi;
import com.example.vts.UserRole.UserParameter;
import com.example.vts.UserRole.UserRoleInterface;
import com.example.vts.UserRole.Usergetset;
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

public class EditUserActivity extends AppCompatActivity {
String Userid;

    EditText firstname,lastname,phonenumber,password,CONFIRMpassword,email;
    Button Login,cancelbtn;
    Spinner packagelistspin;
    String s,Valuespin;
    Context context;
    Response<List<RoleListgetset>> rolelist_response;
    SharedPreferences sharedPreferences;
    int check=0;
    RecyclerView permissions;
    PermissionGetset adaddpermissin;
    List<PermissionGetset> permissionGetsetList=new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        Userid=getIntent().getStringExtra("Userid");
        System.out.println("jhkhjhj"+Userid);

        Login=findViewById(R.id.submit_btn);
        context=EditUserActivity.this;

        cancelbtn=findViewById(R.id.cancelbtn);
        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        email=findViewById(R.id.Emailname);
        phonenumber=findViewById(R.id.phonenumber);
        password=findViewById(R.id.password);
        CONFIRMpassword=findViewById(R.id.CONFIRMpassword);
        packagelistspin=findViewById(R.id.packagelistspin);
        permissions=findViewById(R.id.permissions);
        sharedPreferences = getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EditUserActivity.this,UserActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

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


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        RoleListApi.getRetrofitInstance(context).create(RolelistInterface.class).registration2().enqueue(new Callback<List<RoleListgetset>>() {
            @Override
            public void onResponse(Call<List<RoleListgetset>> call, Response<List<RoleListgetset>> response) {
                System.out.println("completeresponse__"+ new Gson().toJson(response.body()));
                rolelist_response = response;

                ArrayList<Country> countryList = new ArrayList<>();
                countryList.add(new Country("Choose Roles","Choose Roles"));
                for (RoleListgetset data:response.body()) {
                    String id=data.getUserRoleId();
                    String name=data.getUserRoleName();
                    System.out.println("hjhjgjgjhgj"+id);
                    countryList.add(new Country(data.getUserRoleId(), data.getUserRoleName()));
                    ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(context, R.layout.spinnerdropdownitem, countryList);
                    packagelistspin.setAdapter(adapter);



                }
            }
            @Override
            public void onFailure(Call<List<RoleListgetset>> call, Throwable t) {
                System.out.println("hjjgjhg__"+call.toString());
            }
        });


        permissions.setVisibility(View.VISIBLE);
        packagelistspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(++check>1){
                    permissionGetsetList.clear();
                    //usersAdapter1.notifyDataSetChanged();
                    if(position==0){
                        GridLayoutManager gridLayoutManager=new GridLayoutManager(EditUserActivity.this,2, GridLayoutManager.VERTICAL,false);
                        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddUserActivity.this);
                        permissions.setLayoutManager(gridLayoutManager);
                        PermissionAdapter usersAdapter1   = new PermissionAdapter(EditUserActivity.this,jsoncreate());
                        permissions.setAdapter(usersAdapter1);
                    }else {

                        String s=parent.getSelectedItem().toString();
                        System.out.println("hjgjhjgj"+s);
                        Country country = (Country) parent.getSelectedItem();

                        Valuespin=country.getName();
                        for(RoleListgetset data:rolelist_response.body()){
                            System.out.println("jhhkhkhkhkj");
                            if(data.getUserRoleId()==country.getId()){
                                try {
                                    System.out.println("gjhgggh"+data.getScope());
                                    JSONArray array=new JSONArray(data.getScope());
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
                                            adaddpermissin=new PermissionGetset(display_nametop,
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
                                        GridLayoutManager gridLayoutManager=new GridLayoutManager(EditUserActivity.this,2, GridLayoutManager.VERTICAL,false);
                                        // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddUserActivity.this);
                                        permissions.setLayoutManager(gridLayoutManager);
                                        PermissionAdapter usersAdapter1   = new PermissionAdapter(EditUserActivity.this,permissionGetsetList);
                                        permissions.setAdapter(usersAdapter1);
                                        System.out.println("gfgdfhgfhgfhf"+array);
                                    }


                                } catch (JSONException e) { e.printStackTrace();}

                            }
                        }
                    }

                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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
                        System.out.println("ghhghghghhfhg"+array);
                        s= String.valueOf(array);



                    }catch (Exception e){}


                }

                System.out.println("hjgjhgjhghg"+s);
                if(phonenumber.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(EditUserActivity.this, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
                }else if(s==null){
                    Toast.makeText(EditUserActivity.this, "Please Choose Roles", Toast.LENGTH_SHORT).show();
                }else if(s.equalsIgnoreCase("Choose Roles")){
                    Toast.makeText(EditUserActivity.this, "Please Choose Roles", Toast.LENGTH_SHORT).show();
                }else {
                     category();
                }

            }
        });
    }


    public List<PermissionGetset> jsoncreate(){
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
        return permissionGetsetList;
    }


    private void category() {

        final ProgressDialog progressDialog = new ProgressDialog(EditUserActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
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
                System.out.println("ghhghghghhfhg"+array);
                s= String.valueOf(array);



            }catch (Exception e){}


        }

        System.out.println("hjgjhgjhghg"+s);

        UserParameter prameter= new UserParameter();
        prameter.FirstName=firstname.getText().toString();
        prameter.LastName=lastname.getText().toString();
        prameter.Email=email.getText().toString();
        prameter.UserName=email.getText().toString();
        prameter.PhoneNumber=phonenumber.getText().toString();
        prameter.Password=password.getText().toString();
        prameter.NewPassword=CONFIRMpassword.getText().toString();
        prameter.Role=Valuespin;
        prameter.Notes="";
        prameter.Scope=s;
        prameter.IsActive="true";
        prameter.IsDeleted="false";
        prameter.Id=Userid;
        prameter.OrganizationId=sharedPreferences.getString(CommonUtils.shared_ORG_ID,"");
        System.out.println("khjgjhgjgjg"+s);

        AddDeviceApi.getRetrofitInstance(context).create(UserRoleInterface.class).registration3(prameter).enqueue(new Callback<Usergetset>() {
            @Override
            public void onResponse(Call<Usergetset> call, Response<Usergetset> response) {
                progressDialog.dismiss();
                System.out.println("jkjkjkjh"+new Gson().toJson(response.body()));
                if(response.body().getSuccess()==false){
                    Toast.makeText(EditUserActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }else if( response.body().getSuccess()==true){
                    Intent intent=new Intent(EditUserActivity.this,UserActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(EditUserActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usergetset> call, Throwable t) {

            }
        });
    }
    public void back(View view) {
        Intent intent=new Intent(EditUserActivity.this,UserActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}