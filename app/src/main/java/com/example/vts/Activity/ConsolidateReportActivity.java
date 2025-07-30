package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
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

import com.example.vts.Activity.ConsolidateReportpack.ConsolidateInterface;
import com.example.vts.Activity.ConsolidateReportpack.ConsolidateParamter;
import com.example.vts.Activity.ConsolidateReportpack.ConsolidateReportApi;
import com.example.vts.Activity.ConsolidateReportpack.ConsolidateReportgetset;
import com.example.vts.Activity.ConsolidateReportpack.GetConsolidatedData;
import com.example.vts.Activity.ConsolidateReportpack.ResultData;
import com.example.vts.Activity.DashboardApi.Country;
import com.example.vts.R;
import com.example.vts.Vechilelistpack.VechicleInterface;
import com.example.vts.Vechilelistpack.Vechiclegetset;
import com.example.vts.Vechilelistpack.VechiclilistApi;

import com.gkemon.XMLtoPDF.PdfGenerator;
import com.gkemon.XMLtoPDF.PdfGeneratorListener;
import com.gkemon.XMLtoPDF.model.FailureResponse;
import com.gkemon.XMLtoPDF.model.SuccessResponse;
import com.vipul.hp_hp.library.Layout_to_Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.xml.transform.stream.StreamResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

public class ConsolidateReportActivity extends AppCompatActivity {
    RecyclerView rolesrecycleview;
    Context context;
    TextView todatevlue,fromdatevlue,Reoprtheadingname;
    Button searchreport;
    ImageView printpdf;
    Spinner packagelistspin;
    String DeviceId;
    List<Map<String, Object>> membershipTitle1 = new ArrayList<>();
    TextView uniqueid,sourceaddress,destaddress,distance,totalrunning,totalhalt,avspeed,totaldistance,currentaddress;

    Layout_to_Image layout_to_image;
    LinearLayout linear;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consolidate_report);
        context=ConsolidateReportActivity.this;
        fromdatevlue=findViewById(R.id.fromdatevlue);
        Reoprtheadingname=findViewById(R.id.Reoprtheadingname);
        todatevlue=findViewById(R.id.todatevlue);
        searchreport=findViewById(R.id.searchreport);
        uniqueid=findViewById(R.id.uniqueid);
        sourceaddress=findViewById(R.id.sourceaddress);
        destaddress=findViewById(R.id.destaddress);
        printpdf=findViewById(R.id.printpdf);
        totalrunning=findViewById(R.id.totalrunning);
        totaldistance=findViewById(R.id.totaldistance);
        currentaddress=findViewById(R.id.currentaddress);
        avspeed=findViewById(R.id.avspeed);
        totalhalt=findViewById(R.id.totalhalt);
        distance=findViewById(R.id.distance);
        packagelistspin=findViewById(R.id.packagelistspin);
        rolesrecycleview=findViewById(R.id.rolesrecycleview);
        linear=findViewById(R.id.linear);
        Reoprtheadingname.setVisibility(View.GONE);
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
                String fore=fromdatevlue.getText().toString();
                String toe=todatevlue.getText().toString();
                if(fore.equalsIgnoreCase("")){
                    Toast.makeText(ConsolidateReportActivity.this, "Choose Start Date", Toast.LENGTH_SHORT).show();
                }else if(toe.equalsIgnoreCase("")){
                    Toast.makeText(ConsolidateReportActivity.this, "Choose End Date", Toast.LENGTH_SHORT).show();
                }else if(DeviceId.equalsIgnoreCase("Choose Vehicle Number")){
                    Toast.makeText(ConsolidateReportActivity.this, "Choose Vehicle Number", Toast.LENGTH_SHORT).show();
                }else {
                    report(fore,toe);
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
                .setContext(ConsolidateReportActivity.this)
                .fromViewSource()
                .fromView(imageiveiww)
                .setFileName("Consolidated Report")
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
    private static final int PERMISSION_REQUEST_CODE = 85688;

    private void requestPermission() {
        ActivityCompat.requestPermissions(ConsolidateReportActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(ConsolidateReportActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }






    private Bitmap LoadBitmap(View v, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }








    private void report(String fore, String toe) {
        final ProgressDialog progressDialog = new ProgressDialog(ConsolidateReportActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        ConsolidateParamter kmParamter=new ConsolidateParamter();
        kmParamter.end_ts=toe;
        kmParamter.start_ts=fore;
        kmParamter.uniqueId=DeviceId;
        ConsolidateReportApi.getRetrofitInstance(context).create(ConsolidateInterface.class).registration(kmParamter).enqueue(new Callback<ConsolidateReportgetset>() {
            @Override
            public void onResponse(Call<ConsolidateReportgetset> call, Response<ConsolidateReportgetset> response) {
                progressDialog.dismiss();
                System.out.println("hgjghghh"+response.body().getResultData());
                ResultData data=response.body().getResultData();
                GetConsolidatedData getConsolidatedData=data.getGetConsolidatedData();
                uniqueid.setText(getConsolidatedData.getUniqueid());
                sourceaddress.setText(getConsolidatedData.getStartAddress());
                destaddress.setText(getConsolidatedData.getEndAddress());
                distance.setText(getConsolidatedData.getTotalDist());
                totaldistance.setText(getConsolidatedData.getTotalDist());
                currentaddress.setText(getConsolidatedData.getCurrAddress());
                avspeed.setText(getConsolidatedData.getAvgSpeed());
                int hal=getConsolidatedData.getTotalHaltTime();
                String date1 = new SimpleDateFormat("hh:mm").format(new Date(hal*1000));
                System.out.println("iuuiuiiyiyi"+date1);
                totalhalt.setText(date1);

                int ru=getConsolidatedData.getTotalRunningTime();
                String date11 = new SimpleDateFormat("hh:mm").format(new Date(ru*1000));
                System.out.println("iuuiuiiyiyi"+date1);
                totalrunning.setText(date11);


            }

            @Override
            public void onFailure(Call<ConsolidateReportgetset> call, Throwable t) {

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
        datePickerDialog = new DatePickerDialog(ConsolidateReportActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String s=((monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);
                DateFormat inputFormat1 = new SimpleDateFormat("M-dd-yyyy");//2021-02-23
                DateFormat outputFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                String inputDateStr1=s.trim();
                Date date1 = null;
                try {
                    date1 = inputFormat1.parse(inputDateStr1);
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                String outputDateStr1= outputFormat1.format(date1);
                System.out.println("hgtyrdrfaghbhjhfdrtg"+outputDateStr1);
                String dateset=outputDateStr1;
                setdate.setText(dateset);
                Reoprtheadingname.setVisibility(View.VISIBLE);
                Reoprtheadingname.setText("Consolidate Report");
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
        datePickerDialog = new DatePickerDialog(ConsolidateReportActivity.this, new DatePickerDialog.OnDateSetListener() {
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public void back(View view) {
        Intent intent=new Intent(ConsolidateReportActivity.this,AllReportsActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}