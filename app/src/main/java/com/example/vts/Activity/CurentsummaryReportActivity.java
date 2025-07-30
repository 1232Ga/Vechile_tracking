package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vts.Activity.ConsolidateReportpack.ConsolidateParamter;
import com.example.vts.Activity.CurrentSummaryReport.CategoryThreeFields;
import com.example.vts.Activity.CurrentSummaryReport.CurrentSummaryApi;
import com.example.vts.Activity.CurrentSummaryReport.CurrentSummaryInterface;
import com.example.vts.Activity.CurrentSummaryReport.CurrentSummarygetset;
import com.example.vts.Activity.CurrentSummaryReport.CurrentSummayParamter;
import com.example.vts.Activity.CurrentSummaryReport.GetReportPagination;
import com.example.vts.Activity.CurrentSummaryReport.ResultData;
import com.example.vts.Activity.DashboardApi.Country;
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
import java.util.Map;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurentsummaryReportActivity extends AppCompatActivity {
    Context context;
    TextView todatevlue,fromdatevlue;
    Button searchreport;
    Spinner packagelistspin;
    String DeviceId;
    TextView daystartaddress,daystarttime,currentaddrees,currestapeed,lasttimetrack,totaldist,Reoprtheadingname;
    String outputDateStr1;

    LinearLayout linear;
    Bitmap bitmap;
    ImageView printpdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curentsummary_report);
        context=CurentsummaryReportActivity.this;
        fromdatevlue=findViewById(R.id.fromdatevlue);
        todatevlue=findViewById(R.id.todatevlue);
        searchreport=findViewById(R.id.searchreport);
        daystartaddress=findViewById(R.id.daystartaddress);
        currestapeed=findViewById(R.id.currestapeed);
        totaldist=findViewById(R.id.totaldist);
        lasttimetrack=findViewById(R.id.lasttimetrack);
        currentaddrees=findViewById(R.id.currentaddrees);
        daystarttime=findViewById(R.id.daystarttime);
        packagelistspin=findViewById(R.id.packagelistspin);

        printpdf=findViewById(R.id.printpdf);
        Reoprtheadingname=findViewById(R.id.Reoprtheadingname);
        Reoprtheadingname.setVisibility(View.VISIBLE);
        Reoprtheadingname.setText("CurrentSummary Report");
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
        outputDateStr1 = outputFormat1.format(date1);

        searchreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fore=fromdatevlue.getText().toString();
                String toe=todatevlue.getText().toString();
                 if(DeviceId.equalsIgnoreCase("Choose Vehicle Number")){
                    Toast.makeText(CurentsummaryReportActivity.this, "Choose Vehicle Number", Toast.LENGTH_SHORT).show();
                }else {
                    report();
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
                .setContext(CurentsummaryReportActivity.this)
                .fromViewSource()
                .fromView(imageiveiww)
                .setFileName("CurrentSummary Report")
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
    public static String getDate(Calendar cal){
        return "" + cal.get(Calendar.DATE) +"/" +
                (cal.get(Calendar.MONTH)+1) + "/" + cal.get(Calendar.YEAR);
    }
    private void report() {
        final ProgressDialog progressDialog = new ProgressDialog(CurentsummaryReportActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        CurrentSummayParamter kmParamter=new CurrentSummayParamter();
        kmParamter.end_ts=outputDateStr1;
        kmParamter.start_ts=outputDateStr1;
        kmParamter.uniqueId=DeviceId;
        kmParamter.category="3";
        kmParamter.customReportName="Current Summary Report";
        kmParamter.reportType="null";
        kmParamter.clientLoginId="7868";
        kmParamter.offset="0";
        kmParamter.previousDist="0.0";
        kmParamter.timezone="Asia/Calcutta";

        System.out.println("fhgf"+outputDateStr1+"  "+DeviceId+"   "+kmParamter);
        CurrentSummaryApi.getRetrofitInstance(context).create(CurrentSummaryInterface.class).registration(kmParamter).enqueue(new Callback<CurrentSummarygetset>() {
            @Override
            public void onResponse(Call<CurrentSummarygetset> call, Response<CurrentSummarygetset> response) {
                progressDialog.dismiss();
                System.out.println("hgjghghh"+response.body().getResultData());
                ResultData data=response.body().getResultData();
                GetReportPagination reportPagination=data.getGetReportPagination();
                CategoryThreeFields categoryThreeFields=reportPagination.getCategoryThreeFields();
                daystartaddress.setText(categoryThreeFields.getDayStartAddress());
                currentaddrees.setText(categoryThreeFields.getCurrAddress());
                currestapeed.setText(categoryThreeFields.getCurrSpeed());
                totaldist.setText(categoryThreeFields.getDistCoverToday());
                long etMills = Long.parseLong(categoryThreeFields.getLastTrackTime());
                String date  = new SimpleDateFormat("HH:MM:SS").format(new Date(etMills*1000));
                System.out.println("iuuiuiiyiyi"+date);
                lasttimetrack.setText(date);
//                if(!(categoryThreeFields.getDayStartTime() ==null)){
//                    long etMills1 = Long.parseLong(categoryThreeFields.getDayStartTime());
//                    String date1  = new SimpleDateFormat("HH:MM:SS").format(new Date(etMills1*1000));
//                    System.out.println("iuuiuiiyiyi"+date1);
//                    daystarttime.setText(date1);
//
//                }else {
//                    daystarttime.setText("");
             //   }

            }

            @Override
            public void onFailure(Call<CurrentSummarygetset> call, Throwable t) {

            }
        });
    }

    public void back(View view) {
        Intent intent=new Intent(CurentsummaryReportActivity.this,AllReportsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}