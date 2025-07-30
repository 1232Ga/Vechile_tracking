package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vts.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class VehicleSummaryReportDetailsActivity extends AppCompatActivity {
    TextView vechiname,vechinunber,vechicletype,vechiclemodel,Rfid,Deviceid,startaddress,Endaddress,Totaldistance,Totalrunningtime,Totalhalttime,Avgspped,Totalidletime,StartDate,EndDate;
    Context context;
    GraphView graphView;
    String Vechiclename,Vechiclenumber,Vechicletype,Vechiclemodel,RFID,unique_id,Startaddress,endaddress,totaldist,totalrunn,totalhalt,averagespeed,totalidle,startdatetime,enddatetime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_summary_report_details);
        context=VehicleSummaryReportDetailsActivity.this;
        Vechiclename=getIntent().getStringExtra("Vechiclename");
        Vechiclenumber=getIntent().getStringExtra("Vechiclenumber");
        Vechicletype=getIntent().getStringExtra("Vechicletype");
        Vechiclemodel=getIntent().getStringExtra("Vechiclemodel");
        RFID=getIntent().getStringExtra("RFID");
        unique_id=getIntent().getStringExtra("unique_id");
        Startaddress=getIntent().getStringExtra("startaddress");
        endaddress=getIntent().getStringExtra("Endaddress");
        totaldist=getIntent().getStringExtra("totaldist");
        totalrunn=getIntent().getStringExtra("totalrunn");
        totalhalt=getIntent().getStringExtra("totalhalt");
        averagespeed=getIntent().getStringExtra("averagespeed");
        totalidle=getIntent().getStringExtra("totalidle");
        startdatetime=getIntent().getStringExtra("startdatetime");
        enddatetime=getIntent().getStringExtra("enddatetime");
        vechiname=findViewById(R.id.vechiname);
        graphView = findViewById(R.id.idGraphView);
        vechinunber=findViewById(R.id.vechinunber);
        vechicletype=findViewById(R.id.vechicletype);
        vechiclemodel=findViewById(R.id.vechiclemodel);
        Rfid=findViewById(R.id.Rfid);

        Deviceid=findViewById(R.id.Deviceid);
        startaddress=findViewById(R.id.startaddress);
        Endaddress=findViewById(R.id.Endaddress);
        Totaldistance=findViewById(R.id.Totaldistance);
        Totalrunningtime=findViewById(R.id.Totalrunningtime);
        Totalhalttime=findViewById(R.id.Totalhalttime);
        Avgspped=findViewById(R.id.Avgspped);
        Totalidletime=findViewById(R.id.Totalidletime);
        StartDate=findViewById(R.id.StartDate);
        EndDate=findViewById(R.id.EndDate);
        vechiname.setText(Vechiclename);
        vechinunber.setText(Vechiclenumber);
        vechicletype.setText(Vechicletype);
        vechiclemodel.setText(Vechiclemodel);
        Rfid.setText(RFID);
        Deviceid.setText(unique_id);
        startaddress.setText(Startaddress);
        Endaddress.setText(endaddress);
        Totaldistance.setText(totaldist);
        Totalrunningtime.setText(totalrunn);
        Totalhalttime.setText(totalhalt);
        Avgspped.setText(averagespeed);
        Totalidletime.setText(totalidle);
        StartDate.setText(startdatetime);
        EndDate.setText(enddatetime);
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
        Intent intent=new Intent(VehicleSummaryReportDetailsActivity.this,VehicleSummaryReportActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}