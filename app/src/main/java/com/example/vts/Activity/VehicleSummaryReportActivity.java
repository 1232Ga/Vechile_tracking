package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vts.Activity.DashboardApi.Country;
import com.example.vts.Activity.PDFPack.PDFInterface;
import com.example.vts.Activity.PDFPack.PdfGetset;
import com.example.vts.Activity.PDFPack.PdfParam;
import com.example.vts.Activity.PDFPack.TripPdfPack;
import com.example.vts.Activity.VehicleSummaryReport.TripSummaryAdpater;
import com.example.vts.Activity.VehicleSummaryReport.TripSummaryReport;
import com.example.vts.Activity.VehicleSummaryReport.TripSummarygetset;
import com.example.vts.Activity.VehicleSummaryReport.VechicleSummaryAdpater;
import com.example.vts.Activity.VehicleSummaryReport.VechicleSummaryParam;
import com.example.vts.Activity.VehicleSummaryReport.VehicleSummaryGetset;
import com.example.vts.Activity.VehicleSummaryReport.VehicleSummaryInterface;
import com.example.vts.R;
import com.example.vts.RolePack.RoleParameter;
import com.example.vts.SitePackage.SiteAdapter;
import com.example.vts.TriplistPack.TripListApi;
import com.example.vts.Vechilelistpack.VechicleInterface;
import com.example.vts.Vechilelistpack.Vechiclegetset;
import com.example.vts.Vechilelistpack.VechiclilistApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleSummaryReportActivity extends AppCompatActivity {
LinearLayout ll_startvalue,ll_startvaluetrip,recyviell;
TextView setdatevlue,endsetdatevlue,NoTransaction,endsetdatevluetrip,setdatevluetrip,NoTransactiontrip;
RecyclerView catrecyer,tripcatrecyer;
Context context;
String outputDateStr1,outputDateStr11;
String DeviceId;
ProgressDialog progressDialogtrippdf,progressDialogvechiclepdf;
    Handler handler = new Handler();
    int status = 0;
RelativeLayout Rela_source;
    View chartview, mapsview;
    FloatingActionButton ll_pdf;
    Spinner packagelistspin,packagelistspintrip;
    LinearLayout ll_vehiclesummary,ll_Tripsummary,vehiclesummmaryreport,tripsummary;
    String Reportvalueclicmck="Vehiclesummary";
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;
    String vechiclesummaryreportres,Tripsummaryreportres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_summary_report);
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        ll_startvalue=findViewById(R.id.ll_startvalue);
        ll_startvaluetrip=findViewById(R.id.ll_startvaluetrip);
        recyviell=findViewById(R.id.recyviell);
        setdatevlue=findViewById(R.id.setdatevlue);
        catrecyer=findViewById(R.id.catrecyer);
        chartview = findViewById(R.id.chartview);
        mapsview = findViewById(R.id.mapsview);
        Rela_source = findViewById(R.id.Rela_source);
        tripcatrecyer=findViewById(R.id.tripcatrecyer);
        tripcatrecyer.setVisibility(View.GONE);
        vehiclesummmaryreport=findViewById(R.id.vehiclesummmaryreport);
        tripsummary=findViewById(R.id.tripsummary);
        packagelistspin=findViewById(R.id.packagelistspin);
        packagelistspintrip=findViewById(R.id.packagelistspintrip);
        endsetdatevlue=findViewById(R.id.endsetdatevlue);
        endsetdatevluetrip=findViewById(R.id.endsetdatevluetrip);
        setdatevluetrip=findViewById(R.id.setdatevluetrip);
        ll_vehiclesummary=findViewById(R.id.ll_vehiclesummary);
        ll_Tripsummary=findViewById(R.id.ll_Tripsummary);
        ll_Tripsummary.setVisibility(View.GONE);
        NoTransaction=findViewById(R.id.NoTransaction);
        NoTransactiontrip=findViewById(R.id.NoTransactiontrip);
        NoTransactiontrip.setVisibility(View.VISIBLE);
        ll_pdf=findViewById(R.id.ll_pdf);
        context=VehicleSummaryReportActivity.this;
        ll_vehiclesummary.setVisibility(View.VISIBLE);
        ll_Tripsummary.setVisibility(View.GONE);
        chartview.setVisibility(View.GONE);
        mapsview.setVisibility(View.VISIBLE);
        vehiclesummmaryreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reportvalueclicmck="Vehiclesummary";
                chartview.setVisibility(View.GONE);
                mapsview.setVisibility(View.VISIBLE);
                ll_vehiclesummary.setVisibility(View.VISIBLE);
                ll_Tripsummary.setVisibility(View.GONE);
            }
        });
        tripsummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reportvalueclicmck="Reportsummary";
                chartview.setVisibility(View.VISIBLE);
                mapsview.setVisibility(View.GONE);
                ll_vehiclesummary.setVisibility(View.GONE);
                ll_Tripsummary.setVisibility(View.VISIBLE);
            }
        });

        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        DateFormat inputFormat1 = new SimpleDateFormat("d/M/yyyy");
        DateFormat outputFormat1 = new SimpleDateFormat("dd/MM/yyyy");
        String ss=getDate(cal);
        String inputDateStr1=ss.trim();
        Date date1 = null;
        try { date1 = inputFormat1.parse(inputDateStr1);} catch (ParseException e) {e.printStackTrace(); }
        outputDateStr1= outputFormat1.format(date1);
        DateFormat outputFormat12 = new SimpleDateFormat("dd.MM.yy");
        String outputDateStr13= outputFormat12.format(date1);
        setdatevlue.setText(outputDateStr13);
        setdatevluetrip.setText(outputDateStr13);

        cal.add(Calendar.DATE, 15);
        DateFormat inputFormat11 = new SimpleDateFormat("d/M/yyyy");
        DateFormat outputFormat11 = new SimpleDateFormat("dd/MM/yyyy");
        String sss=getDate(cal);
        String inputDateStr11=sss.trim();
        Date date12 = null;
        try {
            date12 = inputFormat11.parse(inputDateStr11);
        } catch (ParseException e) {
            e.printStackTrace(); }
        outputDateStr11= outputFormat11.format(date12);
        DateFormat outputFormat14 = new SimpleDateFormat("dd.MM.yy");
        String outputDateStr15= outputFormat14.format(date12);
        endsetdatevlue.setText(outputDateStr15);
        endsetdatevluetrip.setText(outputDateStr15);
        VechiclilistApi.getRetrofitInstance(context).create(VechicleInterface.class).registration2().enqueue(new Callback<List<Vechiclegetset>>() {
            @Override
            public void onResponse(Call<List<Vechiclegetset>> call, Response<List<Vechiclegetset>> response) {
                ArrayList<Country> countryList = new ArrayList<>();
               // countryList.add(new Country("Choose Vehicle Number","Choose Vehicle Number"));
                for (Vechiclegetset data:response.body()) {
                    String id=data.getDeviceId();
                    String name=data.getVehicleNo();
                    countryList.add(new Country(data.getDeviceId(), data.getVehicleNo()));
                    ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(context, android.R.layout.simple_spinner_dropdown_item, countryList);
                    packagelistspin.setAdapter(adapter);
                    packagelistspintrip.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Vechiclegetset>> call, Throwable t) {

            }
        });
        packagelistspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Country country = (Country) parent.getSelectedItem();
                DeviceId=country.getId();
                category(DeviceId,outputDateStr1,outputDateStr11);
                System.out.println("gjghjgjgj"+DeviceId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        packagelistspintrip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Country country = (Country) parent.getSelectedItem();
                DeviceId=country.getId();
                System.out.println("gjghjgjgj"+DeviceId);
                categoryTrip(DeviceId,outputDateStr1,outputDateStr11);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        System.out.println("bhghgghfhh"+vechiclesummaryreportres);
       if(vechiclesummaryreportres==null){
           ll_pdf.setVisibility(View.GONE);
       }
        else if(vechiclesummaryreportres.equalsIgnoreCase("0")){
            ll_pdf.setVisibility(View.GONE);
        }else if(vechiclesummaryreportres.equalsIgnoreCase("1")){
            ll_pdf.setVisibility(View.VISIBLE);
        }


        if(Tripsummaryreportres==null){
            ll_pdf.setVisibility(View.GONE);
        }
        else if(Tripsummaryreportres.equalsIgnoreCase("0")){
            ll_pdf.setVisibility(View.GONE);
        }else if(Tripsummaryreportres.equalsIgnoreCase("1")){
            ll_pdf.setVisibility(View.VISIBLE);
        }

        ll_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Reportvalueclicmck.equalsIgnoreCase("Vehiclesummary")){
                    pdfprint(DeviceId,outputDateStr1,outputDateStr11);

                }else if(Reportvalueclicmck.equalsIgnoreCase("Reportsummary")){

                    Trippdfprint(DeviceId,outputDateStr1,outputDateStr11);
                }

            }
        });

        System.out.println("gjghjgjgj"+DeviceId);
        ll_startvalue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(VehicleSummaryReportActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        Calendar myCalendar = Calendar.getInstance();
                        myCalendar.set(Calendar.YEAR, selectedyear);
                        myCalendar.set(Calendar.MONTH, selectedmonth);
                        myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                        String myFormat = "dd/MM/yyyy"; //Change as you need
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                        System.out.println("jhughjgjhgh"+sdf.format(myCalendar.getTime()));
                        DateFormat inputFormat1 = new SimpleDateFormat("d/M/yyyy");//2021-02-23
                        DateFormat outputFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                        String ss=sdf.format(myCalendar.getTime());
                        String inputDateStr1=ss.trim();
                        Date date1 = null;
                        try {
                            date1 = inputFormat1.parse(inputDateStr1);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        outputDateStr1= outputFormat1.format(date1);
                        DateFormat outputFormat12 = new SimpleDateFormat("dd.MM.yy");
                        String outputDateStr13= outputFormat12.format(date1);
                        setdatevlue.setText(outputDateStr13);

                        myCalendar.add(Calendar.DATE, 15);

                        DateFormat inputFormat11 = new SimpleDateFormat("d/M/yyyy");//2021-02-23
                        DateFormat outputFormat11 = new SimpleDateFormat("dd/MM/yyyy");
                        String sss=getDate(myCalendar);
                        String inputDateStr11=sss.trim();
                        Date date12 = null;
                        try {
                            date12 = inputFormat11.parse(inputDateStr11);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                         outputDateStr11= outputFormat11.format(date12);
                        DateFormat outputFormat14 = new SimpleDateFormat("dd.MM.yy");
                        String outputDateStr15= outputFormat14.format(date12);
                        endsetdatevlue.setText(outputDateStr15);
                        category(DeviceId,outputDateStr1,outputDateStr11);
                        //dash(fromdatevlue.getText().toString(),todatevlue.getText().toString());

                    }
                }, mYear, mMonth, mDay);

                mDatePicker.show();
            }
        });
        ll_startvaluetrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(VehicleSummaryReportActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        Calendar myCalendar = Calendar.getInstance();
                        myCalendar.set(Calendar.YEAR, selectedyear);
                        myCalendar.set(Calendar.MONTH, selectedmonth);
                        myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                        String myFormat = "dd/MM/yyyy"; //Change as you need
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                        System.out.println("jhughjgjhgh"+sdf.format(myCalendar.getTime()));
                        DateFormat inputFormat1 = new SimpleDateFormat("d/M/yyyy");//2021-02-23
                        DateFormat outputFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                        String ss=sdf.format(myCalendar.getTime());
                        String inputDateStr1=ss.trim();
                        Date date1 = null;
                        try {
                            date1 = inputFormat1.parse(inputDateStr1);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        outputDateStr1= outputFormat1.format(date1);
                        DateFormat outputFormat12 = new SimpleDateFormat("dd.MM.yy");
                        String outputDateStr13= outputFormat12.format(date1);
                        setdatevluetrip.setText(outputDateStr13);
                        myCalendar.add(Calendar.DATE, 15);
                        DateFormat inputFormat11 = new SimpleDateFormat("d/M/yyyy");//2021-02-23
                        DateFormat outputFormat11 = new SimpleDateFormat("dd/MM/yyyy");
                        String sss=getDate(myCalendar);
                        String inputDateStr11=sss.trim();
                        Date date12 = null;
                        try {
                            date12 = inputFormat11.parse(inputDateStr11);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        outputDateStr11= outputFormat11.format(date12);
                        DateFormat outputFormat14 = new SimpleDateFormat("dd.MM.yy");
                        String outputDateStr15= outputFormat14.format(date12);
                        endsetdatevluetrip.setText(outputDateStr15);
                        categoryTrip(DeviceId,outputDateStr1,outputDateStr11);
                        //dash(fromdatevlue.getText().toString(),todatevlue.getText().toString());

                    }
                }, mYear, mMonth, mDay);

                mDatePicker.show();
            }
        });
        category(DeviceId,outputDateStr1,outputDateStr11);
        categoryTrip(DeviceId,outputDateStr1,outputDateStr11);
    }
    public boolean onTouchEvent(MotionEvent motionEvent) {
        mScaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }
    private  class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f,
                    Math.min(mScaleFactor, 10.0f));
            recyviell.setScaleX(mScaleFactor);
            recyviell.setScaleY(mScaleFactor);
            return true;
        }
    }
    DownloadZipFileTask downloadZipFileTask;
    DownloadZipFileTask1 downloadZipFileTask1;
    private void pdfprint(String deviceId, String outputDateStr1, String outputDateStr11) {
        progressDialogvechiclepdf = new ProgressDialog(VehicleSummaryReportActivity.this);
        progressDialogvechiclepdf.setIndeterminate(false);
        progressDialogvechiclepdf.setTitle("Downloading..");
        progressDialogvechiclepdf.setMessage("Please Wait...");
        progressDialogvechiclepdf.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialogvechiclepdf.setCancelable(false);
        progressDialogvechiclepdf.setMax(100);
        progressDialogvechiclepdf.show();
        PdfParam prameter= new PdfParam();
        prameter.category=0;
        prameter.clientLoginId=0;
        prameter.start_ts=outputDateStr1;
        prameter.end_ts=outputDateStr11;
        prameter.uniqueId=deviceId;
        prameter.frequency="";
        prameter.offset=0;
        prameter.previousDist=0;
        prameter.customReportName="";
        prameter.timezone="";
        prameter.reportType="";

        PDFInterface apiInterface = TripListApi.getRetrofitInstance(context).create(PDFInterface.class);
        Call<ResponseBody> call = apiInterface.downloadFileWithDynamicUrl(prameter);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println("ghhfhfhgfhf"+new Gson().toJson(response.body()));
                if (response.isSuccessful()){
                    status = 0;
                    downloadZipFileTask = new DownloadZipFileTask();
                    downloadZipFileTask.execute(response.body());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while(status < 100){
                                status +=1;
                                try{
                                    Thread.sleep(200);
                                }catch(InterruptedException e){
                                    e.printStackTrace();
                                }
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressDialogvechiclepdf.setProgress(status);
                                        if(status == 100){
                                            progressDialogvechiclepdf.dismiss();
                                            Toast.makeText(getApplicationContext(), "File downloaded Suceessfully", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }
                    }).start();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void Trippdfprint(String deviceId, String outputDateStr1, String outputDateStr11) {

        progressDialogtrippdf = new ProgressDialog(VehicleSummaryReportActivity.this);
        progressDialogtrippdf.setIndeterminate(false);
        progressDialogtrippdf.setTitle("Downloading..");
        progressDialogtrippdf.setMessage("Please Wait...");
        progressDialogtrippdf.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialogtrippdf.setCancelable(false);
        progressDialogtrippdf.setMax(100);
        progressDialogtrippdf.show();

        PdfParam prameter= new PdfParam();
        prameter.category=0;
        prameter.clientLoginId=0;
        prameter.start_ts=outputDateStr1;
        prameter.end_ts=outputDateStr11;
        prameter.uniqueId=deviceId;
        prameter.frequency="";
        prameter.offset=0;
        prameter.previousDist=0;
        prameter.customReportName="";
        prameter.timezone="";
        prameter.reportType="";
        TripPdfPack apiInterface = TripListApi.getRetrofitInstance(context).create(TripPdfPack.class);
        Call<ResponseBody> call = apiInterface.downloadFileWithDynamicUrl(prameter);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    status = 0;
                    downloadZipFileTask1 = new DownloadZipFileTask1();
                    downloadZipFileTask1.execute(response.body());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while(status < 100){
                                status +=1;
                                try{
                                    Thread.sleep(200);
                                }catch(InterruptedException e){
                                    e.printStackTrace();
                                }
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressDialogtrippdf.setProgress(status);
                                        if(status == 100){
                                            progressDialogtrippdf.dismiss();
                                            Toast.makeText(getApplicationContext(), "File downloaded Suceessfully", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }
                    }).start();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    private class DownloadZipFileTask1 extends AsyncTask<ResponseBody, Pair<Integer, Long>, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(ResponseBody... urls) {
            saveToDisk1(urls[0], "TripsReport.pdf");
            return null;
        }

        protected void onProgressUpdate(Pair<Integer, Long>... progress) {

            Log.d("API123", progress[0].second + " ");

            if (progress[0].first == 100)



            if (progress[0].second > 0) {
                int currentProgress = (int) ((double) progress[0].first / (double) progress[0].second * 100);

            }

            if (progress[0].first == -1) {
                Toast.makeText(getApplicationContext(), "Download failed", Toast.LENGTH_SHORT).show();
            }

        }

        public void doProgress(Pair<Integer, Long> progressDetails) {
            publishProgress(progressDetails);
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }
    private void saveToDisk1(ResponseBody body, String filename) {
        try {

            File destinationFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename);
            System.out.println("lkhjgjgjgjgjh"+destinationFile);
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(destinationFile);
                byte data[] = new byte[4096];
                int count;
                int progress = 0;
                long fileSize = body.contentLength();
                while ((count = inputStream.read(data)) != -1) {
                    outputStream.write(data, 0, count);
                    progress += count;
                    Pair<Integer, Long> pairs = new Pair<>(progress, fileSize);
                    downloadZipFileTask1.doProgress(pairs);

                }

                outputStream.flush();
                Pair<Integer, Long> pairs = new Pair<>(100, 100L);
                downloadZipFileTask1.doProgress(pairs);
                return;
            } catch (IOException e) {
                e.printStackTrace();
                Pair<Integer, Long> pairs = new Pair<>(-1, Long.valueOf(-1));
                downloadZipFileTask1.doProgress(pairs);

                return;
            } finally {
                if (inputStream != null) inputStream.close();
                if (outputStream != null) outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    private class DownloadZipFileTask extends AsyncTask<ResponseBody, Pair<Integer, Long>, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(ResponseBody... urls) {
            saveToDisk(urls[0], "VehicleReport.pdf");
            return null;
        }

        protected void onProgressUpdate(Pair<Integer, Long>... progress) {

            Log.d("API123", progress[0].second + " ");

            if (progress[0].first == 100)

            if (progress[0].second > 0) {
                int currentProgress = (int) ((double) progress[0].first / (double) progress[0].second * 100);

            }

            if (progress[0].first == -1) {
                Toast.makeText(getApplicationContext(), "Download failed", Toast.LENGTH_SHORT).show();
            }

        }

        public void doProgress(Pair<Integer, Long> progressDetails) {
            publishProgress(progressDetails);
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }
    private void saveToDisk(ResponseBody body, String filename) {
        try {

            File destinationFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename);
            System.out.println("lkhjgjgjgjgjh"+destinationFile);
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(destinationFile);
                byte data[] = new byte[4096];
                int count;
                int progress = 0;
                long fileSize = body.contentLength();
                while ((count = inputStream.read(data)) != -1) {
                    outputStream.write(data, 0, count);
                    progress += count;
                    Pair<Integer, Long> pairs = new Pair<>(progress, fileSize);
                    downloadZipFileTask.doProgress(pairs);

                }

                outputStream.flush();
                Pair<Integer, Long> pairs = new Pair<>(100, 100L);
                downloadZipFileTask.doProgress(pairs);
                return;
            } catch (IOException e) {
                e.printStackTrace();
                Pair<Integer, Long> pairs = new Pair<>(-1, Long.valueOf(-1));
                downloadZipFileTask.doProgress(pairs);

                return;
            } finally {
                if (inputStream != null) inputStream.close();
                if (outputStream != null) outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {

            if (requestCode == 101)
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }


    private void category(String Uniquid,String Startdate,String EndDate) {
        final ProgressDialog progressDialog = new ProgressDialog(VehicleSummaryReportActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        VechicleSummaryParam prameter= new VechicleSummaryParam();
        prameter.category="0";
        prameter.clientLoginId="0";
        prameter.start_ts=Startdate;
        prameter.end_ts=EndDate;
        prameter.uniqueId=Uniquid;
        TripListApi.getRetrofitInstance(context).create(VehicleSummaryInterface.class).registration(prameter).enqueue(new Callback<List<VehicleSummaryGetset>>() {
            @Override
            public void onResponse(Call<List<VehicleSummaryGetset>> call, Response<List<VehicleSummaryGetset>> response) {
                System.out.println("hjghjgjhghghmjg"+new Gson().toJson(response.body()));

                if(response.body()==null){
                    progressDialog.dismiss();
                    vechiclesummaryreportres="0";
                    NoTransaction.setVisibility(View.VISIBLE);
                    catrecyer.setVisibility(View.GONE);
                    ll_pdf.setVisibility(View.GONE);

                }else {
                    progressDialog.dismiss();
                    vechiclesummaryreportres="1";
                    NoTransaction.setVisibility(View.GONE);
                    catrecyer.setVisibility(View.VISIBLE);
                    ll_pdf.setVisibility(View.VISIBLE);
                    System.out.println("kjjhkhkjh"+new Gson().toJson(response.body()));
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(VehicleSummaryReportActivity.this);
                    catrecyer.setLayoutManager(linearLayoutManager);
                    VechicleSummaryAdpater  usersAdapter = new VechicleSummaryAdpater(VehicleSummaryReportActivity.this, response.body());
                    catrecyer.setAdapter(usersAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<VehicleSummaryGetset>> call, Throwable t) {

            }
        });
    }
    private void categoryTrip(String Uniquid,String Startdate,String EndDate) {
        final ProgressDialog progressDialog = new ProgressDialog(VehicleSummaryReportActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        PdfParam prameter= new PdfParam();
        prameter.category=0;
        prameter.clientLoginId=0;
        prameter.start_ts=Startdate;
        prameter.end_ts=EndDate;
        prameter.uniqueId=Uniquid;
        prameter.frequency="";
        prameter.offset=0;
        prameter.previousDist=0;
        prameter.customReportName="";
        prameter.timezone="";
        prameter.reportType="";
      TripListApi.getRetrofitInstance(context).create(TripSummaryReport.class).registration(prameter).enqueue(new Callback<List<TripSummarygetset>>() {
          @Override
          public void onResponse(Call<List<TripSummarygetset>> call, Response<List<TripSummarygetset>> response) {
              System.out.println("jhjhgjhgjghjg"+new Gson().toJson(response.body()));
              if(response.body().isEmpty()){
                  progressDialog.dismiss();
                  Tripsummaryreportres="0";
                  NoTransactiontrip.setVisibility(View.VISIBLE);
                  tripcatrecyer.setVisibility(View.GONE);
                  ll_pdf.setVisibility(View.GONE);

              }else {
                  progressDialog.dismiss();
                  Tripsummaryreportres="1";
                  NoTransactiontrip.setVisibility(View.GONE);
                  tripcatrecyer.setVisibility(View.VISIBLE);
                  ll_pdf.setVisibility(View.VISIBLE);
                  System.out.println("kjjhkhkjh"+new Gson().toJson(response.body()));
                  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(VehicleSummaryReportActivity.this);
                  tripcatrecyer.setLayoutManager(linearLayoutManager);
                  TripSummaryAdpater  usersAdapter = new TripSummaryAdpater(VehicleSummaryReportActivity.this, response.body());
                  tripcatrecyer.setAdapter(usersAdapter);
              }
          }

          @Override
          public void onFailure(Call<List<TripSummarygetset>> call, Throwable t) {

          }
      });    }
    public static String getDate(Calendar cal){
        return "" + cal.get(Calendar.DATE) +"/" +(cal.get(Calendar.MONTH)+1) + "/" + cal.get(Calendar.YEAR);
    }
    public void back(View view) {
        Intent intent=new Intent(VehicleSummaryReportActivity.this,DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}