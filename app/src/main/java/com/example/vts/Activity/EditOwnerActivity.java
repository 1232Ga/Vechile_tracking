package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.vts.Activity.AddDevice.AddDeviceApi;
import com.example.vts.Activity.AddDevice.AddDevicegetset;
import com.example.vts.Activity.AddOwnerPack.AddOwnerParameter;
import com.example.vts.Activity.AddOwnerPack.AddownerInterface;
import com.example.vts.LoginPack.FileUtil;
import com.example.vts.R;
import com.example.vts.VechoeownerPack.Vechicleownergetset;
import com.example.vts.VechoeownerPack.VechileInterCaeDetails;
import com.example.vts.VechoeownerPack.VehicleOwnerApi;
import com.example.vts.base.CommonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditOwnerActivity extends AppCompatActivity {
String Vechileid;
    TextView transportname,Emaill,mobilenumber,address,vehcilenumber,ownername;;
    Context context;
    SharedPreferences sharedPreferences;
    LinearLayout ll_image;
    File file1;
    ImageView menuimage1;
    String image = "null",tokens;
    private static final String IMAGE_DIRECTORY = "/demonuts";
    String encodedImage;
    Uri contentURI;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 7;
    private int GALLERY = 1, CAMERA = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_owner);
        Vechileid=getIntent().getStringExtra("Vechileid");
        System.out.println("hjgjgjjhg"+Vechileid);
        transportname=findViewById(R.id.transportname);
        Emaill=findViewById(R.id.Emaill);
        menuimage1=findViewById(R.id.menuimage1);
        ll_image=findViewById(R.id.ll_image);
        mobilenumber=findViewById(R.id.mobilenumber);
        address=findViewById(R.id.address);
        vehcilenumber=findViewById(R.id.vehcilenumber);
        ownername=findViewById(R.id.ownername);

        sharedPreferences = getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);
        context=EditOwnerActivity.this;
        tokens = sharedPreferences.getString(CommonUtils.shared_TOKENS, "");
        findViewById(R.id.cancelbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EditOwnerActivity.this,VehicleOwnerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        menuimage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAndRequestPermissions()){
                    showPictureDialog();
                }

            }
        });
        VehicleOwnerApi.getRetrofitInstance(context).create(VechileInterCaeDetails.class).registration(Vechileid).enqueue(new Callback<Vechicleownergetset>() {
            @Override
            public void onResponse(Call<Vechicleownergetset> call, Response<Vechicleownergetset> response) {

                //vecholename.setText(response.body().getOwnerName());
                ownername.setText(response.body().getOwnerName());
                transportname.setText(response.body().getTransportName());
                Emaill.setText(response.body().getEmail());
                mobilenumber.setText(response.body().getMobile());
                address.setText(response.body().getAddress());
                vehcilenumber.setText(response.body().getTotalNoOfVehicle());

            }

            @Override
            public void onFailure(Call<Vechicleownergetset> call, Throwable t) {

            }
        });


        findViewById(R.id.submit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category();

            }
        });
    }
    private void category() {
        final ProgressDialog progressDialog = new ProgressDialog(EditOwnerActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
//        AddOwnerParameter prameter= new AddOwnerParameter();
//        prameter.TransportName=transportname.getText().toString();
//        prameter.OwnerName=ownername.getText().toString();
//        prameter.Address=address.getText().toString();
//        prameter.Mobile=mobilenumber.getText().toString();
//        prameter.Email=Emaill.getText().toString();
//        prameter.TotalNoOfVehicle=vehcilenumber.getText().toString();
//        prameter.Notes="";
//        prameter.IsActive="true";
//        prameter.IsDeleted="false";
//        prameter.VehicleOwnerId=Vechileid;
//        prameter.OrganizationId=sharedPreferences.getString(CommonUtils.shared_ORG_ID,"");


        try {
            if(contentURI != null){
                file1= FileUtil.from(context, contentURI);
                System.out.println("imaggagag"+file1);
            }

        }
        catch (IOException ee)
        {ee.printStackTrace();}


        try {
            String TransportName=transportname.getText().toString();
            String OwnerName=ownername.getText().toString();
            String Address=address.getText().toString();
            String Mobile=mobilenumber.getText().toString();
            String Email=Emaill.getText().toString();
            String TotalNoOfVehicle=vehcilenumber.getText().toString();
            String VehicleOwnerId=Vechileid;
            String OrganizationId=sharedPreferences.getString(CommonUtils.shared_ORG_ID,"");

            JSONObject object1 = new JSONObject();
            object1.put("TransportName",TransportName);
            object1.put("OwnerName",OwnerName);
            object1.put("Address",Address);
            object1.put("Mobile",Mobile);
            object1.put("Email",Email);
            object1.put("TotalNoOfVehicle",TotalNoOfVehicle);
            object1.put("VehicleOwnerId",VehicleOwnerId);
            object1.put("IsActive","true");
            object1.put("IsDeleted","false");
            object1.put("OrganizationId",OrganizationId);
            System.out.println("hghghfhfh"+object1);
            AndroidNetworking.upload("http://vts.bluehawk.ai/services/api/VehicleOwnerManagement")
                    .addHeaders("Authorization","Bearer "+tokens)
                    .addHeaders("Content-Type","application/json")
                    .addMultipartFile("file", file1)
                    .addMultipartParameter("DriverManagementItem", String.valueOf(object1))
                    .setTag("uploadTask")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsString(new StringRequestListener() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println("klsjdfjf" + response);
                            try {
                                JSONObject object = new JSONObject(response);
                                String IsSuccess = object.getString("IsSuccess");
                                String ErrorMessage = object.getString("ErrorMessage");
                                String SuccessMessage = object.getString("SuccessMessage");
                                String OperationData= object.getString("OperationData");
                                System.out.println("Aditya__"+OperationData);
                                if (IsSuccess.equalsIgnoreCase("true")) {
                                    progressDialog.dismiss();
                                    Intent intent=new Intent(EditOwnerActivity.this,VehicleOwnerActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    Toast.makeText(EditOwnerActivity.this,SuccessMessage,Toast.LENGTH_LONG).show();
                                }
                                else if(IsSuccess.equalsIgnoreCase("false")){
                                    progressDialog.dismiss();
                                    Toast.makeText(EditOwnerActivity.this, ErrorMessage, Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(EditOwnerActivity.this, e.toString(), Toast.LENGTH_SHORT).show();

                            }
                        }
                        @Override
                        public void onError(ANError anError) {
                            System.out.println("lksjdlfdf"+anError);
                        }
                    });


        } catch (JSONException e) {
            e.printStackTrace();
        }



//        AddDeviceApi.getRetrofitInstance(context).create(AddownerInterface.class).registration2(prameter).enqueue(new Callback<AddDevicegetset>() {
//            @Override
//            public void onResponse(Call<AddDevicegetset> call, Response<AddDevicegetset> response) {
//                progressDialog.dismiss();
//                if(response.body().getSuccess()==false){
//                    Toast.makeText(EditOwnerActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
//                }else if( response.body().getSuccess()==true){
//                    Intent intent=new Intent(EditOwnerActivity.this,VehicleOwnerActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
//                    Toast.makeText(EditOwnerActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AddDevicegetset> call, Throwable t) {
//
//            }
//        });

    }
    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(EditOwnerActivity.this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                EditOwnerActivity.this.choosePhotoFromGallary();
                                break;
                            case 1:
                                EditOwnerActivity.this.takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }
    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("vbcbcbbcb"+requestCode+"  "+resultCode);
        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                System.out.println("gytcgcgfcv");
                contentURI = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    System.out.println("ggcgfcxfxddf"+bitmap);
                    ll_image.setBackgroundResource(R.drawable.imageback);
                    menuimage1.setImageBitmap(bitmap);
                    image="image";
                    encodedImage=getStringImage(bitmap);
                    contentURI = getImageUri(context, bitmap);
                    System.out.println("responcedatagallery"+contentURI);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        }
        else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ll_image.setBackgroundResource(R.drawable.imageback);
            menuimage1.setImageBitmap(thumbnail);
            image="image";
            encodedImage=getStringImage(thumbnail);
            contentURI= getImageUri(context,thumbnail);
            saveImage(thumbnail);


        }



    }
    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(context, new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::---&gt;" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        Log.w("path of image from .", encodedImage+"");
        return encodedImage;
    }

    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "IMG_" + Calendar.getInstance().getTime(), null);
        return Uri.parse(path);
    }

    private boolean checkAndRequestPermissions() {
        int camera = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        int wtite = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int read = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (wtite != PackageManager.PERMISSION_GRANTED) { listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (camera != PackageManager.PERMISSION_GRANTED) { listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (read != PackageManager.PERMISSION_GRANTED) { listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(EditOwnerActivity.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            System.out.println("lkdlkjf");
            showPictureDialog();
            return false;
        }
        return true;
    }
    public void back(View view) {
        Intent intent=new Intent(EditOwnerActivity.this,VehicleOwnerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}