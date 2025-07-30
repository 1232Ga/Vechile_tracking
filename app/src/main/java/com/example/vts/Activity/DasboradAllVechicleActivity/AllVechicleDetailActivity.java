package com.example.vts.Activity.DasboradAllVechicleActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vts.Activity.ConsolidateReportActivity;
import com.example.vts.Activity.DashboardActivity;
import com.example.vts.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllVechicleDetailActivity extends AppCompatActivity {
    TextView uniqueid,sourceaddress,destaddress,distance,totalrunning,totalhalt,totaldistance,vechiclenum,enddate,startdate;
    Context context;
    String fromdate,todate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_vechicle_detail);
        context= AllVechicleDetailActivity.this;
//        fromdatevlue=findViewById(R.id.fromdatevlue);
//        todatevlue=findViewById(R.id.todatevlue);
        fromdate=getIntent().getStringExtra("fromdate");
        todate=getIntent().getStringExtra("todate");
        uniqueid=findViewById(R.id.uniqueid);
        vechiclenum=findViewById(R.id.vechiclenum);
        sourceaddress=findViewById(R.id.sourceaddress);
        destaddress=findViewById(R.id.destaddress);
        totalrunning=findViewById(R.id.totalrunning);
        totaldistance=findViewById(R.id.totaldistance);
        totalhalt=findViewById(R.id.totalhalt);
        distance=findViewById(R.id.distance);
        startdate=findViewById(R.id.startdate);
        enddate=findViewById(R.id.enddate);


        DashboardAllvechicleApi.getRetrofitInstance(context).create(DashboardAllvechicleInterface.class).registration(7868,fromdate,todate).enqueue(new Callback<DashbaordAllgetset>() {
            @Override
            public void onResponse(Call<DashbaordAllgetset> call, Response<DashbaordAllgetset> response) {
                System.out.println("hjgjgjh"+new Gson().toJson(response.body()));
                com.example.vts.Activity.DasboradAllVechicleActivity.ResultData datum=response.body().getResultData();
                List<GetAllActivitiesByClientLoginId> getAllActivitiesByClientLoginIds=datum.getGetAllActivitiesByClientLoginId();
                System.out.println("gjhghghgh"+getAllActivitiesByClientLoginIds.size());
                if(getAllActivitiesByClientLoginIds.size()==0){
                    Toast.makeText(AllVechicleDetailActivity.this, "No Vehicle Found", Toast.LENGTH_SHORT).show();
                }else {
                    for (int i = 0; i <getAllActivitiesByClientLoginIds.size() ; i++) {
                        uniqueid.setText(getAllActivitiesByClientLoginIds.get(i).getUniqueId());
                        vechiclenum.setText(getAllActivitiesByClientLoginIds.get(i).getVehicleNumber());
                        sourceaddress.setText(getAllActivitiesByClientLoginIds.get(i).getStartAddress());
                        destaddress.setText(getAllActivitiesByClientLoginIds.get(i).getEndAddress());
                        distance.setText(getAllActivitiesByClientLoginIds.get(i).getTotalDist());
                        totaldistance.setText(getAllActivitiesByClientLoginIds.get(i).getTotalDist());
                        int hal=getAllActivitiesByClientLoginIds.get(i).getTotalHaltTime();
                        // String date  = new SimpleDateFormat("dd/MM/yyyy").format(new Date(etMills*1000));
                        String date1 = new SimpleDateFormat("hh:mm").format(new Date(hal*1000));
                        System.out.println("iuuiuiiyiyi"+date1);
                        totalhalt.setText(date1);

                        int ru=getAllActivitiesByClientLoginIds.get(i).getTotalRunningTime();
                        String date11 = new SimpleDateFormat("hh:mm").format(new Date(ru*1000));
                        System.out.println("iuuiuiiyiyi"+date1);
                        totalrunning.setText(date11);


                        long etMills = Long.parseLong(getAllActivitiesByClientLoginIds.get(i).getFromTs());
                        long etMills1 = Long.parseLong(getAllActivitiesByClientLoginIds.get(i).getToTs());

                        String date22  = new SimpleDateFormat("dd/MM/yyyy").format(new Date(etMills*1000));
                        System.out.println("iuuiuiiyiyi"+date22);
                        startdate.setText(date22);
                        String date33  = new SimpleDateFormat("dd/MM/yyyy").format(new Date(etMills1*1000));
                        System.out.println("iuuiuiiyiyi"+date33);
                        enddate.setText(date33);
                    }

                }
            }

            @Override
            public void onFailure(Call<DashbaordAllgetset> call, Throwable t) {

            }
        });

    }

    public void back(View view) {
        Intent intent=new Intent(AllVechicleDetailActivity.this, DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}