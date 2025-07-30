package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

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
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vts.Activity.AddDevice.AddDeviceApi;
import com.example.vts.Activity.AddDevice.AddDeviceParameter;
import com.example.vts.Activity.AddVechicle.AddVechicleApi;
import com.example.vts.Activity.AddVechicle.AddVechicleInterface;
import com.example.vts.Activity.AddVechicle.AddVechiclegetset;
import com.example.vts.Activity.AddVechicle.AddvechicleParameter;
import com.example.vts.Activity.DashboardApi.Country;
import com.example.vts.DriverPack.DriverApi;
import com.example.vts.DriverPack.DriverInterface;

import com.example.vts.DriverPack.Drivergetset;
import com.example.vts.GetVechicleDetailPAck.DeviceDetail;
import com.example.vts.GetVechicleDetailPAck.GetAllVechicleInterface;
import com.example.vts.GetVechicleDetailPAck.GetAllVechilegetset;
import com.example.vts.GetVechicleDetailPAck.GetAllVehicleApi;
import com.example.vts.GetVechicleDetailPAck.GetAllVehicleDetailsAPus;
import com.example.vts.GetVechicleDetailPAck.ResultData;
import com.example.vts.R;
import com.example.vts.RoleListPack.RoleListgetset;
import com.example.vts.SitePackage.SiteAdapter;
import com.example.vts.SitePackage.SiteGetset;
import com.example.vts.SitePackage.SiteInterface;
import com.example.vts.SitePackage.SiteListApi;
import com.example.vts.VechoeownerPack.VechicleInterface;
import com.example.vts.VechoeownerPack.VechicleownerListAdapter;
import com.example.vts.VechoeownerPack.Vechicleownergetset;
import com.example.vts.VechoeownerPack.VehicleOwnerApi;
import com.example.vts.base.CommonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddVehicleActivity extends AppCompatActivity {
    EditText vechiclename,vehiclenumber,vehicletype,vehiclemodel,Rfid;
    Spinner vechiclelistspin,Devicelistspin,Driverlistspin;
    Context context;
    List<Map<String, Object>> membershipTitle1 = new ArrayList<>();
    List<Map<String, Object>> membershipTitle2 = new ArrayList<>();
    List<Map<String, Object>> membershipTitle3 = new ArrayList<>();
    List<Map<String, Object>> membershipTitle4 = new ArrayList<>();
    String Ownerid,deviceid,driverid;
    Button Login,cancelbtn;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        vechiclelistspin=findViewById(R.id.vechiclelistspin);
        cancelbtn=findViewById(R.id.cancelbtn);
        Devicelistspin=findViewById(R.id.Devicelistspin);
       // vehiclmaxspeed=findViewById(R.id.vehiclmaxspeed);
        Driverlistspin=findViewById(R.id.Driverlistspin);
       // vehiclAVGspeed=findViewById(R.id.vehiclAVGspeed);
        vechiclename=findViewById(R.id.vechiclename);
        vehiclenumber=findViewById(R.id.vehiclenumber);
        vehicletype=findViewById(R.id.vehicletype);
        vehiclemodel=findViewById(R.id.vehiclemodel);
        Rfid=findViewById(R.id.Rfid);
        Login=findViewById(R.id.submit_btn);
        context=AddVehicleActivity.this;
        sharedPreferences = getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);
        vechiclelistspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Country country = (Country) parent.getSelectedItem();
                Ownerid=country.getId();


//                Map<String, Object> selectedItem1 = membershipTitle1.get(vechiclelistspin.getSelectedItemPosition());
//                String name1 = selectedItem1.get("name").toString();
//                 = selectedItem1.get("id").toString();
//                System.out.println("dkhkjhgjkdjkd" + name1 + Ownerid);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Driverlistspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Country country = (Country) parent.getSelectedItem();
                driverid=country.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        SiteListApi.getRetrofitInstance(context).create(SiteInterface.class).registration2().enqueue(new Callback<List<SiteGetset>>() {
//            @Override
//            public void onResponse(Call<List<SiteGetset>> call, Response<List<SiteGetset>> response) {
//                for (SiteGetset data:response.body()){
//                    String id=data.getSiteId();
//                    String name=data.getName();
//                    System.out.println("hjhjgjgjhgj"+id);
//                    Map<String, Object> item = new HashMap<>();
//                    item.put("id", id);
//                    item.put("name", name);
//                    membershipTitle4.add(item);
//                    SimpleAdapter arrayAdapter = new SimpleAdapter(AddVehicleActivity.this, membershipTitle4,
//                            R.layout.spinneritemback,
//                            new String[]{"name"}, new int[]{R.id.text1});
//                    arrayAdapter.setDropDownViewResource(R.layout.spinnershowitemdropdown);
//                    Sitelistspin.setAdapter(arrayAdapter);
//                    Map<String, Object> selectedItem1 = membershipTitle4.get(Sitelistspin.getSelectedItemPosition());
//                    String name1 = selectedItem1.get("name").toString();
//                    String id1 = selectedItem1.get("id").toString();
//                    System.out.println("dkhkjhgjkdjkd" + name1 + id1);
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<SiteGetset>> call, Throwable t) {
//
//            }
//        });

        Devicelistspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Country country = (Country) parent.getSelectedItem();
                deviceid=country.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        VehicleOwnerApi.getRetrofitInstance(context).create(VechicleInterface.class).registration2().enqueue(new Callback<List<Vechicleownergetset>>() {
            @Override
            public void onResponse(Call<List<Vechicleownergetset>> call, Response<List<Vechicleownergetset>> response) {
                ArrayList<Country> countryList = new ArrayList<>();
                countryList.add(new Country("Choose Vehicle Owner","Choose Vehicle Owner"));
                for (Vechicleownergetset data:response.body()) {
                    String id=data.getVehicleOwnerId();
                    String name=data.getOwnerName();
                    System.out.println("hjhjgjgjhgj"+id);
                    countryList.add(new Country(data.getVehicleOwnerId(), data.getOwnerName()));
                    ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(context, R.layout.spinnerdropdownitem, countryList);
                    vechiclelistspin.setAdapter(adapter);




//                    Map<String, Object> item = new HashMap<>();
//                    item.put("id", id);
//                    item.put("name", name);
//                    membershipTitle1.add(item);
//                    SimpleAdapter arrayAdapter = new SimpleAdapter(AddVehicleActivity.this, membershipTitle1,
//                            R.layout.spinneritemback,
//                            new String[]{"name"}, new int[]{R.id.text1});
//                    arrayAdapter.setDropDownViewResource(R.layout.spinnershowitemdropdown);
//                    vechiclelistspin.setAdapter(arrayAdapter);
//                    Map<String, Object> selectedItem1 = membershipTitle1.get(vechiclelistspin.getSelectedItemPosition());
//                    String name1 = selectedItem1.get("name").toString();
//                    String id1 = selectedItem1.get("id").toString();
//                    System.out.println("dkhkjhgjkdjkd" + name1 + id1);

//
                }

            }

            @Override
            public void onFailure(Call<List<Vechicleownergetset>> call, Throwable t) {

            }
        });
        GetAllVehicleApi.getRetrofitInstance(context).create(GetAllVechicleInterface.class).registration2().enqueue(new Callback<GetAllVechilegetset>() {
            @Override
            public void onResponse(Call<GetAllVechilegetset> call, Response<GetAllVechilegetset> response) {
                ArrayList<Country> countryList1 = new ArrayList<>();
                countryList1.add(new Country("Choose DeviceId","Choose Device ID"));
                ResultData data=response.body().getResultData();
                System.out.println("hjggjggjg"+data.getGetAllVehicleDetailsAPI());
                List<GetAllVehicleDetailsAPus>getAllVehicleDetailsAPuses=data.getGetAllVehicleDetailsAPI();
                System.out.println("hjhjhjg"+getAllVehicleDetailsAPuses);
                for (GetAllVehicleDetailsAPus data1:getAllVehicleDetailsAPuses){
                    DeviceDetail deviceDetail1=data1.getDeviceDetail();
                    String id=deviceDetail1.getUniqueDeviceId();
                    System.out.println("jkhkjhjjhkkj"+id);
                    countryList1.add(new Country(deviceDetail1.getUniqueDeviceId(), deviceDetail1.getUniqueDeviceId()));
                    ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(context, R.layout.spinnerdropdownitem, countryList1);
                    Devicelistspin.setAdapter(adapter);


//                    Map<String, Object> item = new HashMap<>();
//                    item.put("id", id);
//                    membershipTitle2.add(item);
//                    SimpleAdapter arrayAdapter = new SimpleAdapter(AddVehicleActivity.this, membershipTitle2,
//                            R.layout.spinneritemback,
//                            new String[]{"id"}, new int[]{R.id.text1});
//                    arrayAdapter.setDropDownViewResource(R.layout.spinnershowitemdropdown);
//                    Devicelistspin.setAdapter(arrayAdapter);
//                    Map<String, Object> selectedItem1 = membershipTitle2.get(Devicelistspin.getSelectedItemPosition());
//                    String id11 = selectedItem1.get("id").toString();
//                    System.out.println("dkhkjhgjkdjkd"  + id11);

                }


            }

            @Override
            public void onFailure(Call<GetAllVechilegetset> call, Throwable t) {

            }
        });
        DriverApi.getRetrofitInstance(context).create(DriverInterface.class).registration2().enqueue(new Callback<List<Drivergetset>>() {
            @Override
            public void onResponse(Call<List<Drivergetset>> call, Response<List<Drivergetset>> response) {
                ArrayList<Country> countryList2 = new ArrayList<>();
                countryList2.add(new Country("Choose Driver","Choose Driver"));

                for (Drivergetset data:response.body()) {
                    String id=data.getDriverId();
                    String name=data.getDriverName();
                    System.out.println("hjhjgjgjhgj"+id);
                    countryList2.add(new Country(data.getDriverId(), data.getDriverName()));
                    ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(context, R.layout.spinnerdropdownitem, countryList2);
                    Driverlistspin.setAdapter(adapter);





                }

            }

            @Override
            public void onFailure(Call<List<Drivergetset>> call, Throwable t) {

            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddVehicleActivity.this,VechicleListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vechiclename.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(AddVehicleActivity.this, "Please Enter Vehicle Name", Toast.LENGTH_SHORT).show();
                }else if(Ownerid.equalsIgnoreCase("Choose Vehicle Owner")){
                    Toast.makeText(AddVehicleActivity.this, "Please Choose Vehicle Owner", Toast.LENGTH_SHORT).show();
                }else if(deviceid.equalsIgnoreCase("Choose DeviceId")){
                    Toast.makeText(AddVehicleActivity.this, "Please Choose Device_ID", Toast.LENGTH_SHORT).show();
                }else if(driverid.equalsIgnoreCase("Choose Driver")){
                    Toast.makeText(AddVehicleActivity.this, "Please Choose Driver", Toast.LENGTH_SHORT).show();
                }
                else {
                    category();
                }
            }
        });
    }

    private void category() {
        final ProgressDialog progressDialog = new ProgressDialog(AddVehicleActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        AddvechicleParameter prameter= new AddvechicleParameter();
        prameter.VehicleName=vechiclename.getText().toString();
        prameter.VehicleNo=vehiclenumber.getText().toString();
        prameter.vehicleType=vehicletype.getText().toString();
        prameter.vehicleModel=vehiclemodel.getText().toString();
        prameter.RFId=Rfid.getText().toString();
        prameter.DeviceId=deviceid;
        prameter.VehicleOwnerId=Ownerid;
        prameter.DriverId=driverid;
        prameter.IsActive="true";
        prameter.IsDeleted="false";
        prameter.OrganizationId=sharedPreferences.getString(CommonUtils.shared_ORG_ID,"");
        AddDeviceApi.getRetrofitInstance(context).create(AddVechicleInterface.class).registration2(prameter).enqueue(new Callback<AddVechiclegetset>() {
            @Override
            public void onResponse(Call<AddVechiclegetset> call, Response<AddVechiclegetset> response) {
                progressDialog.dismiss();
                System.out.println("jhhjjgjgj"+response.body().getSuccess());
                if(response.body().getSuccess()==false){
                    Toast.makeText(AddVehicleActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }else if( response.body().getSuccess()==true){
                    Intent intent=new Intent(AddVehicleActivity.this,VechicleListActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(AddVehicleActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddVechiclegetset> call, Throwable t) {

            }
        });
    }

    public void back(View view) {
        Intent intent=new Intent(AddVehicleActivity.this,VechicleListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}