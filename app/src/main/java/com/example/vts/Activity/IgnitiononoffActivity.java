package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vts.Activity.DashboardApi.Country;
import com.example.vts.Activity.IdleReport.IdleApi;
import com.example.vts.Activity.IdleReport.IdleInterface;
import com.example.vts.Activity.IdleReport.IdleParameter;
import com.example.vts.Activity.IdleReport.Idlegetset;
import com.example.vts.Activity.IdleReport.IdlereportVechileListAdapter;
import com.example.vts.Activity.Ignitiononoff.CategoryTwoField;
import com.example.vts.Activity.Ignitiononoff.GetReportPagination;
import com.example.vts.Activity.Ignitiononoff.IgnitionApi;
import com.example.vts.Activity.Ignitiononoff.IgnitionInteface;
import com.example.vts.Activity.Ignitiononoff.IgnitionParameter;
import com.example.vts.Activity.Ignitiononoff.Ignitiongetset;
import com.example.vts.Activity.Ignitiononoff.IgnitionreportVechileListAdapter;
import com.example.vts.Activity.Ignitiononoff.ResultData;
import com.example.vts.R;
import com.example.vts.Vechilelistpack.VechicleInterface;
import com.example.vts.Vechilelistpack.Vechiclegetset;
import com.example.vts.Vechilelistpack.VechiclilistApi;
import com.gkemon.XMLtoPDF.PdfGenerator;
import com.gkemon.XMLtoPDF.PdfGeneratorListener;
import com.gkemon.XMLtoPDF.model.FailureResponse;
import com.gkemon.XMLtoPDF.model.SuccessResponse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IgnitiononoffActivity extends AppCompatActivity {
    RecyclerView rolesrecycleview;
    Context context;
    TextView todatevlue,fromdatevlue;
    Button searchreport;
    Spinner packagelistspin;
    String DeviceId;
    TextView textdefaultactivity,
            Reoprtheadingname;
    LinearLayout linear;
    Bitmap bitmap;
    ImageView printpdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ignitiononoff);
        context=IgnitiononoffActivity.this;
        fromdatevlue=findViewById(R.id.fromdatevlue);
        todatevlue=findViewById(R.id.todatevlue);
        rolesrecycleview=findViewById(R.id.rolesrecycleview);
        searchreport=findViewById(R.id.searchreport);
        packagelistspin=findViewById(R.id.packagelistspin);
        textdefaultactivity=findViewById(R.id.textdefaultactivity);

        printpdf=findViewById(R.id.printpdf);
        Reoprtheadingname=findViewById(R.id.Reoprtheadingname);
        Reoprtheadingname.setVisibility(View.VISIBLE);
        Reoprtheadingname.setText("Ignition ON/OFF Report");
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
        searchreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f=fromdatevlue.getText().toString();
                String t=todatevlue.getText().toString();
                if(f.equalsIgnoreCase("")){
                    Toast.makeText(IgnitiononoffActivity.this, "Please Select Date", Toast.LENGTH_SHORT).show();
                }else if(DeviceId.equalsIgnoreCase("Choose Vehicle Number")){
                    Toast.makeText(IgnitiononoffActivity.this, "Choose Vehicle Number", Toast.LENGTH_SHORT).show();
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
                .setContext(IgnitiononoffActivity.this)
                .fromViewSource()
                .fromView(imageiveiww)
                .setFileName("Ignition Report")
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
        final ProgressDialog progressDialog = new ProgressDialog(IgnitiononoffActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        IgnitionParameter kmParamter=new IgnitionParameter();
        kmParamter.clientLoginId="7878";
        kmParamter.category="2";
        kmParamter.reportType="igOnOffReport";
        kmParamter.customReportName="Ignition ON/OFF Report";
        kmParamter.timezone="Asia/Calcutta";
        kmParamter.uniqueId=DeviceId;
        kmParamter.start_ts=f;
        kmParamter.end_ts=t;
        kmParamter.offset="0";
        kmParamter.previousDist="0.0";
        IgnitionApi.getRetrofitInstance(context).create(IgnitionInteface.class).registration(kmParamter).enqueue(new Callback<Ignitiongetset>() {
            @Override
            public void onResponse(Call<Ignitiongetset> call, Response<Ignitiongetset> response) {
                progressDialog.dismiss();
              ResultData data=response.body().getResultData();
                GetReportPagination getReportPagination=data.getGetReportPagination();
                List<CategoryTwoField>categoryTwoFields=getReportPagination.getCategoryTwoFields();
                if(categoryTwoFields.size()==0){
                    rolesrecycleview.setVisibility(View.GONE);
                    textdefaultactivity.setVisibility(View.VISIBLE);
                }else {
                    rolesrecycleview.setVisibility(View.VISIBLE);
                    textdefaultactivity.setVisibility(View.GONE);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(IgnitiononoffActivity.this);
                    rolesrecycleview.setLayoutManager(linearLayoutManager);
                    IgnitionreportVechileListAdapter usersAdapter   = new IgnitionreportVechileListAdapter(IgnitiononoffActivity.this, categoryTwoFields);
                    rolesrecycleview.setAdapter(usersAdapter);
                }
            }

            @Override
            public void onFailure(Call<Ignitiongetset> call, Throwable t) {

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
        datePickerDialog = new DatePickerDialog(IgnitiononoffActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        datePickerDialog = new DatePickerDialog(IgnitiononoffActivity.this, new DatePickerDialog.OnDateSetListener() {
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
    
}