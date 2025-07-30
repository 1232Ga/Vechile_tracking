package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vts.Activity.KmReport.GetKmSummary;
import com.example.vts.Activity.KmReport.KmParamter;
import com.example.vts.Activity.KmReport.KmReportApi;
import com.example.vts.Activity.KmReport.KmReportInterface;
import com.example.vts.Activity.KmReport.Kmgetset;
import com.example.vts.Activity.KmReport.KmreportVechileListAdapter;
import com.example.vts.Activity.KmReport.ResultData;
import com.example.vts.R;
import com.example.vts.SitePackage.SiteAdapter;
import com.gkemon.XMLtoPDF.PdfGenerator;
import com.gkemon.XMLtoPDF.PdfGeneratorListener;
import com.gkemon.XMLtoPDF.model.FailureResponse;
import com.gkemon.XMLtoPDF.model.SuccessResponse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KmReportActivity extends AppCompatActivity {
RecyclerView rolesrecycleview;
Context context;
TextView todatevlue,fromdatevlue,Reoprtheadingname;
Button searchreport;
ImageView printpdf;
Spinner packagelistspin;
String itemss;
    TextView textdefaultactivity;
    LinearLayout linear;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_km_report);
        context=KmReportActivity.this;
        fromdatevlue=findViewById(R.id.fromdatevlue);
        todatevlue=findViewById(R.id.todatevlue);
        searchreport=findViewById(R.id.searchreport);
        printpdf=findViewById(R.id.printpdf);
        Reoprtheadingname=findViewById(R.id.Reoprtheadingname);
        packagelistspin=findViewById(R.id.packagelistspin);
        textdefaultactivity=findViewById(R.id.textdefaultactivity);
        rolesrecycleview=findViewById(R.id.rolesrecycleview);
        packagelistspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemss= parent.getItemAtPosition(position).toString();
                System.out.println("vbvvbcvxvc"+itemss);
                Calendar cal = Calendar.getInstance();
                cal.setTimeZone(TimeZone.getTimeZone("GMT"));

                DateFormat inputFormat1 = new SimpleDateFormat("d/M/yyyy");//2021-02-23
                DateFormat outputFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                String ss=getDate(cal);
                String inputDateStr1=ss.trim();
                Date date1 = null;
                try {
                    date1 = inputFormat1.parse(inputDateStr1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String outputDateStr1= outputFormat1.format(date1);
                fromdatevlue.setText(outputDateStr1);

                cal.add(Calendar.DATE, 30);
                DateFormat inputFormat11 = new SimpleDateFormat("d/M/yyyy");//2021-02-23
                DateFormat outputFormat11 = new SimpleDateFormat("dd/MM/yyyy");
                String sss=getDate(cal);
                String inputDateStr11=sss.trim();
                Date date12 = null;
                try {
                    date12 = inputFormat11.parse(inputDateStr11);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String outputDateStr11= outputFormat11.format(date12);

                if(itemss.equalsIgnoreCase("MONTHLY")){
                    todatevlue.setText(outputDateStr11);
                }else if(itemss.equalsIgnoreCase("DAILY")){
                    todatevlue.setText(outputDateStr1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        rolesrecycleview.setVisibility(View.GONE);
        textdefaultactivity.setVisibility(View.VISIBLE);
        linear=findViewById(R.id.linear);
        Reoprtheadingname.setVisibility(View.VISIBLE);
        Reoprtheadingname.setText("KiloMeter Report");
        fromdatevlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(KmReportActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                        String outputDateStr1= outputFormat1.format(date1);

                        fromdatevlue.setText(outputDateStr1);
                        Reoprtheadingname.setVisibility(View.VISIBLE);
                        Reoprtheadingname.setText("KiloMeter Report");
                        myCalendar.add(Calendar.DATE, 30);

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
                        String outputDateStr11= outputFormat11.format(date12);
                        if(itemss.equalsIgnoreCase("MONTHLY")){
                            todatevlue.setText(outputDateStr11);
                        }else if(itemss.equalsIgnoreCase("DAILY")){
                            todatevlue.setText(outputDateStr1);
                        }

                        //todatevlue.setText(outputDateStr11);

                        //dash(fromdatevlue.getText().toString(),todatevlue.getText().toString());

                    }
                }, mYear, mMonth, mDay);

                mDatePicker.show();
            }
        });

        printpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("size", "" + linear.getWidth() + " " + linear.getWidth());
                bitmap = LoadBitmap(linear, linear.getWidth(), linear.getHeight());
                System.out.println("jhghjhgjgj" + bitmap);

                StoreText();
                //createPdf();
            }
        });

        searchreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f=fromdatevlue.getText().toString();
                String t=todatevlue.getText().toString();
                if(f.equalsIgnoreCase("")){
                    Toast.makeText(KmReportActivity.this, "Please Select Date", Toast.LENGTH_SHORT).show();
                }else {
                    report(f,t);
                }

            }
        });

    }
    private void StoreText() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.storetextlist, viewGroup, false);
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setView(dialogView);
        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(true);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.TOP;
        wlp.y=90;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
        window.setAttributes(wlp);
        ImageView back_button =dialogView.findViewById(R.id.back_button);
        ImageView imageiveiww =dialogView.findViewById(R.id.imageiveiww);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        imageiveiww.setImageBitmap(bitmap);


        PdfGenerator.getBuilder()
                .setContext(KmReportActivity.this)
                .fromViewSource()
                .fromView(imageiveiww)
                .setFileName("KM Report")
                .build(new PdfGeneratorListener() {
                    @Override
                    public void onFailure(FailureResponse failureResponse) {
                        super.onFailure(failureResponse);
                    }

                    @Override
                    public void showLog(String log) {
                        super.showLog(log);
                    }

                    @Override
                    public void onStartPDFGeneration() {
                        /*When PDF generation begins to start*/
                    }

                    @Override
                    public void onFinishPDFGeneration() {
                        /*When PDF generation is finished*/
                    }

                    @Override
                    public void onSuccess(SuccessResponse response) {
                        super.onSuccess(response);
                    }
                });


    }

    private Bitmap LoadBitmap(View v, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

    private void report(String fromdate,String Todate) {

        final ProgressDialog progressDialog = new ProgressDialog(KmReportActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        KmParamter kmParamter=new KmParamter();
        kmParamter.frequency=itemss;
        kmParamter.start_ts=fromdate;
        kmParamter.end_ts=Todate;
        KmReportApi.getRetrofitInstance(context).create(KmReportInterface.class).registration(kmParamter).enqueue(new Callback<Kmgetset>() {
            @Override
            public void onResponse(Call<Kmgetset> call, Response<Kmgetset> response) {
                progressDialog.dismiss();
                System.out.println("hgjghghh"+response.body().getResultData());
                ResultData data=response.body().getResultData();
                if(data.getGetKmSummary().size()==0){
                    rolesrecycleview.setVisibility(View.GONE);
                    textdefaultactivity.setVisibility(View.VISIBLE);
                }else {
                    rolesrecycleview.setVisibility(View.VISIBLE);
                    textdefaultactivity.setVisibility(View.GONE);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(KmReportActivity.this);
                    rolesrecycleview.setLayoutManager(linearLayoutManager);
                    KmreportVechileListAdapter usersAdapter   = new KmreportVechileListAdapter(KmReportActivity.this, data.getGetKmSummary());
                    rolesrecycleview.setAdapter(usersAdapter);
                }


            }

            @Override
            public void onFailure(Call<Kmgetset> call, Throwable t) {

            }
        });

    }

    public static String getDate(Calendar cal){
        return "" + cal.get(Calendar.DATE) +"/" +(cal.get(Calendar.MONTH)+1) + "/" + cal.get(Calendar.YEAR);
    }
    public void back(View view) {
        Intent intent=new Intent(KmReportActivity.this,AllReportsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}