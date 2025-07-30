package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vts.Activity.DashboardApi.Country;
import com.example.vts.Activity.DashboardApi.DashBoardApi;
import com.example.vts.Activity.DashboardApi.Dashbaordgetset;
import com.example.vts.Activity.DashboardApi.DashboardInterface;
import com.example.vts.Activity.DashboardApi.DashboardParamter;
import com.example.vts.R;
import com.example.vts.Vechilelistpack.VechicleInterface;
import com.example.vts.Vechilelistpack.Vechiclegetset;
import com.example.vts.Vechilelistpack.VechiclilistApi;
import com.example.vts.VechoeownerPack.VechicleownerDashboardListAdapter;
import com.example.vts.util.GPSTracker;
import com.gkemon.XMLtoPDF.PdfGenerator;
import com.gkemon.XMLtoPDF.PdfGeneratorListener;
import com.gkemon.XMLtoPDF.model.FailureResponse;
import com.gkemon.XMLtoPDF.model.SuccessResponse;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import host.stjin.expandablecardview.ExpandableCardView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReoprttripActivity extends AppCompatActivity {
LinearLayout ll_pdfprint;
    LinearLayout linear,ll_daily,ll_montly,datechoose;
    Bitmap bitmap;
    TextView dailytext,monthly,setdatevlue;
    Spinner packagelistspin;
    Context context;
    String DeviceId,todaydatevalue;

    EditText searchvehicle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reoprttrip);
        context=ReoprttripActivity.this;
        ll_pdfprint=findViewById(R.id.ll_pdfprint);
        linear=findViewById(R.id.linear);
        ExpandableCardView card = findViewById(R.id.incidents);
        ExpandableCardView activity = findViewById(R.id.activity);
        ExpandableCardView tripdeatils = findViewById(R.id.tripdeatils);
        ExpandableCardView completedload = findViewById(R.id.completedload);
        ExpandableCardView speed = findViewById(R.id.speed);
        ExpandableCardView vehicledetails = findViewById(R.id.vehicledetails);
        ll_daily=findViewById(R.id.ll_daily);
        ll_montly=findViewById(R.id.ll_montly);
        dailytext=findViewById(R.id.dailytext);
        monthly=findViewById(R.id.monthly);
        datechoose=findViewById(R.id.datechoose);
        setdatevlue=findViewById(R.id.setdatevlue);
        packagelistspin=findViewById(R.id.packagelistspin);
        searchvehicle=findViewById(R.id.searchvehicle);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dateFormat1 = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        System.out.println("xcdffhhh"+dateFormat.format(date));
        todaydatevalue=dateFormat.format(date);
        setdatevlue.setText(dateFormat1.format(date));
        ll_pdfprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(ReoprttripActivity.this, ll_pdfprint);
                popup.getMenuInflater().inflate(R.menu.pdfprinyt, popup.getMenu());
                popup.setForceShowIcon(true);
                //   popup.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.deletesite) {
                            Log.d("size", "" + linear.getWidth() + " " + linear.getWidth());
                            bitmap = LoadBitmap(linear, linear.getWidth(), linear.getHeight());
//                            System.out.println("jhghjhgjgj" + bitmap);
                            StoreText();
                        } else if (item.getItemId() == R.id.editsite) {
//                            Intent intent = new Intent(ReoprttripActivity.this, EditSiteActivity.class);
//                            intent.putExtra("Siteid",Siteid);
//                            startActivity(intent);
//                            finish();
                            // Toast.makeText(getApplicationContext(), "Edit clicked", Toast.LENGTH_SHORT).show();
                        }

                        return true;
                    }
                });
                popup.show();
            }
        });
        VechiclilistApi.getRetrofitInstance(context).create(VechicleInterface.class).registration2().enqueue(new Callback<List<Vechiclegetset>>() {
            @Override
            public void onResponse(Call<List<Vechiclegetset>> call, Response<List<Vechiclegetset>> response) {
                ArrayList<Country> countryList = new ArrayList<>();
                countryList.add(new Country("Choose Vehicle Number","Choose Vehicle No."));
                for (Vechiclegetset data:response.body()) {
                    String id=data.getDeviceId();
                    String name=data.getVehicleNo();
                    countryList.add(new Country(data.getDeviceId(), data.getVehicleNo()));

                    ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(context, R.layout.reportspinnerback, countryList);
                    adapter.setDropDownViewResource(R.layout.reportviewdropdown);
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
               String Devicename=country.getName();
                searchvehicle.setText(Devicename);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ll_daily.setBackgroundResource(R.drawable.dailyreportback);
        dailytext.setTextColor(getResources().getColor(R.color.white));
        monthly.setTextColor(getResources().getColor(R.color.black));
        ll_daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_daily.setBackgroundResource(R.drawable.dailyreportback);
                dailytext.setTextColor(getResources().getColor(R.color.white));
                monthly.setTextColor(getResources().getColor(R.color.black));
                ll_montly.setBackgroundResource(R.color.white);
            }
        });
        ll_montly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_montly.setBackgroundResource(R.drawable.montlyback);
                ll_daily.setBackgroundResource(R.color.white);
                dailytext.setTextColor(getResources().getColor(R.color.black));
                monthly.setTextColor(getResources().getColor(R.color.white));
            }
        });
        datechoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepick();
            }
        });
        card.setOnExpandedListener(new ExpandableCardView.OnExpandedListener() {
            @Override
            public void onExpandChanged(View v, boolean isExpanded) {
                Toast.makeText(context, isExpanded ? "Expanded!" : "Collapsed!", Toast.LENGTH_SHORT).show();
            }
        });
        activity.setOnExpandedListener(new ExpandableCardView.OnExpandedListener() {
            @Override
            public void onExpandChanged(View v, boolean isExpanded) {
                Toast.makeText(context, isExpanded ? "Expanded!" : "Collapsed!", Toast.LENGTH_SHORT).show();
            }
        });
        tripdeatils.setOnExpandedListener(new ExpandableCardView.OnExpandedListener() {
            @Override
            public void onExpandChanged(View v, boolean isExpanded) {
                Toast.makeText(context, isExpanded ? "Expanded!" : "Collapsed!", Toast.LENGTH_SHORT).show();
            }
        });
        completedload.setOnExpandedListener(new ExpandableCardView.OnExpandedListener() {
            @Override
            public void onExpandChanged(View v, boolean isExpanded) {
                Toast.makeText(context, isExpanded ? "Expanded!" : "Collapsed!", Toast.LENGTH_SHORT).show();
            }
        });
        speed.setOnExpandedListener(new ExpandableCardView.OnExpandedListener() {
            @Override
            public void onExpandChanged(View v, boolean isExpanded) {
                Toast.makeText(context, isExpanded ? "Expanded!" : "Collapsed!", Toast.LENGTH_SHORT).show();
            }
        });
        vehicledetails.setOnExpandedListener(new ExpandableCardView.OnExpandedListener() {
            @Override
            public void onExpandChanged(View v, boolean isExpanded) {
                Toast.makeText(context, isExpanded ? "Expanded!" : "Collapsed!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    DatePickerDialog datePickerDialog;
    private void datepick (){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        System.out.println("hytdsstdratsta"+mYear+"hffdz"+mMonth+"gytfdsdf"+mDay);
        datePickerDialog = new DatePickerDialog(ReoprttripActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String s=((monthOfYear + 1)+ "-" + dayOfMonth + "-" + year);
                DateFormat inputFormat1 = new SimpleDateFormat("M-dd-yyyy");//2021-02-23
                DateFormat outputFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                DateFormat dateFormat1 = new SimpleDateFormat("dd.MM.yyyy");
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
                todaydatevalue=dateset;
                setdatevlue.setText(dateFormat1.format(date1));


            }
        }, mYear, mMonth, mDay);
        // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
    public void back(View view) {
        Intent intent = new Intent(ReoprttripActivity.this, DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
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
                .setContext(ReoprttripActivity.this)
                .fromViewSource()
                .fromView(imageiveiww)
                .setFileName("Trip Report")
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

}