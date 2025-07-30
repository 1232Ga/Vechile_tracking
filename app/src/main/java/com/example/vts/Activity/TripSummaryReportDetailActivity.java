package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.vts.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class TripSummaryReportDetailActivity extends AppCompatActivity {
    TextView vechiname,vechinunber,vechicletype,vechiclemodel,RFID,Tripnames,Deviceid,startaddress,Endaddress,Totaldistance,Netweight,Driverid,Avgspped,Drivername,StartDate,EndDate;
    Context context;
    GraphView graphView;
    String Vechiclename,Vechiclenumber,Vechicletype,Vechiclemodel,RFIDs,unique_id,
            startaddresss,Endaddresss,totaldist,averagespeed,startdatetime,enddatetime,
            tripname,Netweights,DriverIdentity,DriverName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_summary_report_detail);
        context=TripSummaryReportDetailActivity.this;
        graphView = findViewById(R.id.idGraphView);
        Vechiclename=getIntent().getStringExtra("Vechiclename");
        Vechiclenumber=getIntent().getStringExtra("Vechiclenumber");
        Vechicletype=getIntent().getStringExtra("Vechicletype");
        Vechiclemodel=getIntent().getStringExtra("Vechiclemodel");
        RFIDs=getIntent().getStringExtra("RFID");
        unique_id=getIntent().getStringExtra("unique_id");
        startaddresss=getIntent().getStringExtra("startaddress");
        Endaddresss=getIntent().getStringExtra("Endaddress");
        totaldist=getIntent().getStringExtra("totaldist");
        averagespeed=getIntent().getStringExtra("averagespeed");
        startdatetime=getIntent().getStringExtra("startdatetime");
        enddatetime=getIntent().getStringExtra("enddatetime");
        tripname=getIntent().getStringExtra("tripname");
        Netweights=getIntent().getStringExtra("Netweight");
        DriverIdentity=getIntent().getStringExtra("DriverIdentity");
        DriverName=getIntent().getStringExtra("DriverName");
        System.out.println("hjjhjhjhjgjgj"+averagespeed+"   "+totaldist);
        vechiname=findViewById(R.id.vechiname);
        vechinunber=findViewById(R.id.vechinunber);
        vechicletype=findViewById(R.id.vechicletype);
        vechiclemodel=findViewById(R.id.vechiclemodel);
        Netweight=findViewById(R.id.Netweight);
        RFID=findViewById(R.id.Rfid);
        Deviceid=findViewById(R.id.Deviceid);
        startaddress=findViewById(R.id.startaddress);
        Endaddress=findViewById(R.id.Endaddress);
        Totaldistance=findViewById(R.id.Totaldistance);
        Driverid=findViewById(R.id.Driverid);
        Drivername=findViewById(R.id.Drivername);
        Avgspped=findViewById(R.id.Avgspped);
        Tripnames=findViewById(R.id.Tripnames);
        StartDate=findViewById(R.id.StartDate);
        EndDate=findViewById(R.id.EndDate);
        vechiname.setText(Vechiclename);
        vechinunber.setText(Vechiclenumber);
        vechicletype.setText(Vechicletype);
        vechiclemodel.setText(Vechiclemodel);
        RFID.setText(RFIDs);
        Deviceid.setText(unique_id);
        startaddress.setText(startaddresss);
        Endaddress.setText(Endaddresss);
        Totaldistance.setText(totaldist);
        Avgspped.setText(averagespeed);
        StartDate.setText(startdatetime);
        EndDate.setText(enddatetime);
        Tripnames.setText(tripname);
        Netweight.setText(Netweights);
        Driverid.setText(DriverIdentity);
        Drivername.setText(DriverName);

        float spp= Float.parseFloat(averagespeed);
        float di= Float.parseFloat(totaldist);
        System.out.println("fgffgfgf"+di+"    "+spp);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0,2),
                new DataPoint(spp, di),
        });
        graphView.addSeries(series);
    }
    public void back(View view) {
        Intent intent=new Intent(TripSummaryReportDetailActivity.this,VehicleSummaryReportActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}