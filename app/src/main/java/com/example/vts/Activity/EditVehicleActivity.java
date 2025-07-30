package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vts.Activity.AddDevice.AddDeviceApi;
import com.example.vts.Activity.AddVechicle.AddVechicleInterface;
import com.example.vts.Activity.AddVechicle.AddVechiclegetset;
import com.example.vts.Activity.AddVechicle.AddvechicleParameter;
import com.example.vts.Activity.DashboardApi.Country;
import com.example.vts.AllVechdetails.AllVechidetilaInterface;
import com.example.vts.AllVechdetails.AllVehicledaetialApi;
import com.example.vts.AllVechdetails.Allvechiclownergetset;
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
import com.example.vts.VechoeownerPack.VechicleInterface;
import com.example.vts.VechoeownerPack.Vechicleownergetset;
import com.example.vts.VechoeownerPack.VehicleOwnerApi;
import com.example.vts.base.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditVehicleActivity extends AppCompatActivity {
    String Vechileid;
    TextView vechiname,vechinunber,vechicletype,vechiclemodel,Rfid,driverphone;
    Context context;
    Button Login,cancelbtn;
    Spinner vechiclelistspin,Devicelistspin,Driverlistspin;
    String Ownerid,deviceid,driverid;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vehicle);
        Vechileid=getIntent().getStringExtra("Vechileid");
        System.out.println("jhkhjhj"+Vechileid);
        context=EditVehicleActivity.this;
        vechiclelistspin=findViewById(R.id.vechiclelistspin);
        Devicelistspin=findViewById(R.id.Devicelistspin);
        Driverlistspin=findViewById(R.id.Driverlistspin);
        cancelbtn=findViewById(R.id.cancelbtn);
        Login=findViewById(R.id.submit_btn);
        vechiname=findViewById(R.id.vechiname);
        vechinunber=findViewById(R.id.vechinunber);
        vechicletype=findViewById(R.id.vechicletype);
        vechiclemodel=findViewById(R.id.vechiclemodel);
        Rfid=findViewById(R.id.Rfid);
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

        driverphone=findViewById(R.id.driverphone);
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
                Intent intent=new Intent(EditVehicleActivity.this,VechicleListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        AllVehicledaetialApi.getRetrofitInstance(context).create(AllVechidetilaInterface.class).registration2(Vechileid).enqueue(new Callback<Allvechiclownergetset>() {
            @Override
            public void onResponse(Call<Allvechiclownergetset> call, Response<Allvechiclownergetset> response) {
                System.out.println("jkhkjhjhj"+response.body().getVehicleName());
                vechiname.setText(response.body().getVehicleName());
                vechinunber.setText(response.body().getVehicleNo());
                vechicletype.setText(response.body().getVehicleType());
                vechiclemodel.setText(response.body().getVehicleModel());
                Rfid.setText(response.body().getRFId());

            }

            @Override
            public void onFailure(Call<Allvechiclownergetset> call, Throwable t) {

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
        final ProgressDialog progressDialog = new ProgressDialog(EditVehicleActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        AddvechicleParameter prameter= new AddvechicleParameter();
        prameter.VehicleName=vechiname.getText().toString();
        prameter.VehicleNo=vechinunber.getText().toString();
        prameter.vehicleType=vechicletype.getText().toString();
        prameter.vehicleModel=vechiclemodel.getText().toString();
        prameter.RFId=Rfid.getText().toString();
        prameter.DeviceId=deviceid;
        prameter.VehicleId=Vechileid;
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
                    Toast.makeText(EditVehicleActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }else if( response.body().getSuccess()==true){
                    Intent intent=new Intent(EditVehicleActivity.this,VechicleListActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(EditVehicleActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddVechiclegetset> call, Throwable t) {

            }
        });
    }
    public void back(View view) {
        Intent intent=new Intent(EditVehicleActivity.this,VechicleListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}