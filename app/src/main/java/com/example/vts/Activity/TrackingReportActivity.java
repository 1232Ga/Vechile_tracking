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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vts.Activity.DashboardApi.Country;
import com.example.vts.Activity.KmReport.KmParamter;
import com.example.vts.Activity.KmReport.KmreportVechileListAdapter;
import com.example.vts.Activity.TrackingRepor.CategoryOneField;
import com.example.vts.Activity.TrackingRepor.GetReportPagination;
import com.example.vts.Activity.TrackingRepor.ResultData;
import com.example.vts.Activity.TrackingRepor.TrackingParameter;
import com.example.vts.Activity.TrackingRepor.TrackingReportApi;
import com.example.vts.Activity.TrackingRepor.TrackingReportInterface;
import com.example.vts.Activity.TrackingRepor.Trackinggetset;
import com.example.vts.Activity.TrackingRepor.TrackingreportVechileListAdapter;
import com.example.vts.R;
import com.example.vts.Vechilelistpack.VechicleInterface;
import com.example.vts.Vechilelistpack.Vechiclegetset;
import com.example.vts.Vechilelistpack.VechiclilistApi;
import com.gkemon.XMLtoPDF.PdfGenerator;
import com.gkemon.XMLtoPDF.PdfGeneratorListener;
import com.gkemon.XMLtoPDF.model.FailureResponse;
import com.gkemon.XMLtoPDF.model.SuccessResponse;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackingReportActivity extends AppCompatActivity {
    RecyclerView rolesrecycleview;
    Context context;
    TextView todatevlue,fromdatevlue;
    Button searchreport;
    Spinner packagelistspin,offesetspin;
    String DeviceId;
    String offset;
    TextView textdefaultactivity,Reoprtheadingname;
    LinearLayout linear;
    Bitmap bitmap;
    ImageView printpdf;
    List<Map<String, Object>> membershipTitle1 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_report);
        context=TrackingReportActivity.this;
        fromdatevlue=findViewById(R.id.fromdatevlue);
        todatevlue=findViewById(R.id.todatevlue);
        rolesrecycleview=findViewById(R.id.rolesrecycleview);
        searchreport=findViewById(R.id.searchreport);
        packagelistspin=findViewById(R.id.packagelistspin);
        offesetspin=findViewById(R.id.offesetspin);
        textdefaultactivity=findViewById(R.id.textdefaultactivity);
        printpdf=findViewById(R.id.printpdf);
        Reoprtheadingname=findViewById(R.id.Reoprtheadingname);
        Reoprtheadingname.setVisibility(View.VISIBLE);
        Reoprtheadingname.setText("Tracking Report");
        linear=findViewById(R.id.linear);
        VechiclilistApi.getRetrofitInstance(context).create(VechicleInterface.class).registration2().enqueue(new Callback<List<Vechiclegetset>>() {
            @Override
            public void onResponse(Call<List<Vechiclegetset>> call, Response<List<Vechiclegetset>> response) {
                ArrayList<Country> countryList = new ArrayList<>();
                countryList.add(new Country("Choose Vehicle Number","Choose Vehicle Number"));
                for (Vechiclegetset data:response.body()) {
                    String id=data.getDeviceId();
                    String name=data.getVehicleNo();
                    countryList.add(new Country(data.getDeviceId(), data.getVehicleNo()));
                    ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(context, android.R.layout.simple_spinner_dropdown_item, countryList);
                    packagelistspin.setAdapter(adapter);
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        rolesrecycleview.setVisibility(View.GONE);
        textdefaultactivity.setVisibility(View.VISIBLE);
        fromdatevlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepick(fromdatevlue);
            }
        });
        todatevlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepick1(todatevlue);
            }
        });
        offesetspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        offset=parent.getItemAtPosition(position).toString();
            System.out.println("uyyutyytyu"+offset);
        }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});

        searchreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f=fromdatevlue.getText().toString();
                String t=todatevlue.getText().toString();
                if(f.equalsIgnoreCase("")){
                    Toast.makeText(TrackingReportActivity.this, "Please Select Date", Toast.LENGTH_SHORT).show();
                }else if(DeviceId.equalsIgnoreCase("Choose Vehicle Number")){
                    Toast.makeText(TrackingReportActivity.this, "Choose Vehicle Number", Toast.LENGTH_SHORT).show();
                }
                else if(offset.equalsIgnoreCase("Interval")){
                    Toast.makeText(TrackingReportActivity.this, "Choose Interval", Toast.LENGTH_SHORT).show();
                }else {
                    report(f,t);
                }

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
                .setContext(TrackingReportActivity.this)
                .fromViewSource()
                .fromView(imageiveiww)
                .setFileName("Tracking Report")
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
    private void report(String f, String t) {
        final ProgressDialog progressDialog = new ProgressDialog(TrackingReportActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        TrackingParameter kmParamter=new TrackingParameter();
        kmParamter.clientLoginId="7878";
        kmParamter.category="1";
        kmParamter.reportType="";
        kmParamter.customReportName="Tracking Report";
        kmParamter.timezone="Asia/Calcutta";
        kmParamter.previousDist="0";
        kmParamter.uniqueId=DeviceId;
        kmParamter.start_ts=f;
        kmParamter.end_ts=t;
        kmParamter.offset=offset;
        TrackingReportApi.getRetrofitInstance(context).create(TrackingReportInterface.class).registration(kmParamter).enqueue(new Callback<Trackinggetset>() {
            @Override
            public void onResponse(Call<Trackinggetset> call, Response<Trackinggetset> response) {
                progressDialog.dismiss();
                System.out.println("hkjhhjhk"+new Gson().toJson(response.body()));
                ResultData data=response.body().getResultData();
                GetReportPagination getReportPagination=data.getGetReportPagination();
                List<CategoryOneField>categoryOneFields=getReportPagination.getCategoryOneFields();

                System.out.println("hjhgjhghjh"+categoryOneFields.size());
                if(categoryOneFields.size()==0){
                    rolesrecycleview.setVisibility(View.GONE);
                    textdefaultactivity.setVisibility(View.VISIBLE);
                }else {
                    rolesrecycleview.setVisibility(View.VISIBLE);
                    textdefaultactivity.setVisibility(View.GONE);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TrackingReportActivity.this);
                    rolesrecycleview.setLayoutManager(linearLayoutManager);
                    TrackingreportVechileListAdapter usersAdapter   = new TrackingreportVechileListAdapter(TrackingReportActivity.this, categoryOneFields);
                    rolesrecycleview.setAdapter(usersAdapter);
                }

            }

            @Override
            public void onFailure(Call<Trackinggetset> call, Throwable t) {

            }
        });
    }

    DatePickerDialog datePickerDialog;
    private void datepick (TextView setdate){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        System.out.println("hytdsstdratsta"+mYear+"hffdz"+mMonth+"gytfdsdf"+mDay);
        datePickerDialog = new DatePickerDialog(TrackingReportActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String s=((monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);
                DateFormat inputFormat1 = new SimpleDateFormat("M-dd-yyyy");//2021-02-23
                DateFormat outputFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                String inputDateStr1=s.trim();
                Date date1 = null;
                try {
                    date1 = inputFormat1.parse(inputDateStr1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String outputDateStr1= outputFormat1.format(date1);
                System.out.println("hgtyrdrfaghbhjhfdrtg"+outputDateStr1);
                String dateset=outputDateStr1;
                setdate.setText(dateset);

            }
        }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
    public long milliseconds(String date) {
        //String date_ = date;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            Date mDate = sdf.parse(date);
            long timeInMilliseconds = mDate.getTime();
            System.out.println("Date in milli :: " + timeInMilliseconds);
            return timeInMilliseconds;
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 0;
    }
    private void datepick1 (TextView setdate){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        System.out.println("hytdsstdratsta"+mYear+"hffdz"+mMonth+"gytfdsdf"+mDay);
        datePickerDialog = new DatePickerDialog(TrackingReportActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String s=((monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);
                DateFormat inputFormat1 = new SimpleDateFormat("M-dd-yyyy");//2021-02-23
                DateFormat outputFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                String inputDateStr1=s.trim();
                Date date1 = null;
                try {
                    date1 = inputFormat1.parse(inputDateStr1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String outputDateStr1= outputFormat1.format(date1);
                System.out.println("hgtyrdrfaghbhjhfdrtg"+outputDateStr1);
                String dateset=outputDateStr1;
                setdate.setText(dateset);
                if(fromdatevlue.getText().toString().equalsIgnoreCase("")){
                    fromdatevlue.setText(dateset);
                }
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.getDatePicker().setMinDate(milliseconds(fromdatevlue.getText().toString()));
        datePickerDialog.show();

    }

    public void back(View view) {
        Intent intent=new Intent(TrackingReportActivity.this,AllReportsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}