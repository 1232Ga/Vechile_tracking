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
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.bumptech.glide.Glide;
import com.example.vts.Activity.AddDevice.AddDeviceApi;
import com.example.vts.Activity.AddDevice.AddDevicegetset;
import com.example.vts.Activity.AddDriver.AddDriverInterface;
import com.example.vts.Activity.AddDriver.AddDriverParameter;
import com.example.vts.DriverPack.DriverApi;
import com.example.vts.DriverPack.DriverIDetilalnterface;
import com.example.vts.DriverPack.Drivergetset;
import com.example.vts.LoginPack.FileUtil;
import com.example.vts.R;
import com.example.vts.base.CommonUtils;
import com.google.gson.Gson;

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

public class EditDriverActivity extends AppCompatActivity {
String Driverid;
    Context context;
    TextView mobilenumber,address,vehcilenumber,ownername;;
    Button Login;
    SharedPreferences sharedPreferences;

    ImageView menuimage1;
    ImageView image2;
    String image = "null",tokens;
    private static final String IMAGE_DIRECTORY = "/demonuts";
    String encodedImage;
    Uri contentURI,contentURI1;
    File file1;
    LinearLayout ll_image;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_driver);
        Driverid=getIntent().getStringExtra("Driverid");
        System.out.println("hyghfgfdggdggg"+Driverid);
        context=EditDriverActivity.this;
        Login=findViewById(R.id.submit_btn);
        mobilenumber=findViewById(R.id.mobilenumber);
        address=findViewById(R.id.address);
        vehcilenumber=findViewById(R.id.vehcilenumber);
        ownername=findViewById(R.id.ownername);
        menuimage1=findViewById(R.id.menuimage1);
        ll_image=findViewById(R.id.ll_image);
        image2=findViewById(R.id.image2);
        sharedPreferences = getSharedPreferences(CommonUtils.MyPREFERENCES, Context.MODE_PRIVATE);
        tokens = sharedPreferences.getString(CommonUtils.shared_TOKENS, "");
        findViewById(R.id.cancelbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EditDriverActivity.this,DriverManagementActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        menuimage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAndRequestPermissions()){
                    showPictureDialog("Userimage");
                }

            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAndRequestPermissions1()){
                    showPictureDialog("documentimage");
                }

            }
        });
        DriverApi.getRetrofitInstance(context).create(DriverIDetilalnterface.class).registration2(Driverid).enqueue(new Callback<Drivergetset>() {
            @Override
            public void onResponse(Call<Drivergetset> call, Response<Drivergetset> response) {
                System.out.println("jkhkjhjkhjk"+new Gson().toJson(response.body()));
                ownername.setText(response.body().getDriverName());
                mobilenumber.setText(response.body().getPhoneNo());
                address.setText(response.body().getAddress());
                vehcilenumber.setText(response.body().getIdentityNo());
//                if(!response.body().getProofImage().equalsIgnoreCase("")){
//                    Glide.with(context)
//                            .load(response.body().getProofImage())
//                            .error(R.drawable.dumyimgae)
//                            .into(menuimage1);
//                }
            }

            @Override
            public void onFailure(Call<Drivergetset> call, Throwable t) {

            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category();

            }
        });
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
            ActivityCompat.requestPermissions(EditDriverActivity.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            System.out.println("lkdlkjf");
            showPictureDialog("Userimage");
            return false;
        }
        return true;
    }
    private boolean checkAndRequestPermissions1() {
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
            ActivityCompat.requestPermissions(EditDriverActivity.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            System.out.println("lkdlkjf");
            showPictureDialog("documentimage");
            return false;
        }
        return true;
    }
    private void showPictureDialog(String s) {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(EditDriverActivity.this);
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
                                EditDriverActivity.this.choosePhotoFromGallary(s);
                                break;
                            case 1:
                                EditDriverActivity.this.takePhotoFromCamera(s);
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    int MenuGal=12;
    int CatGal=13;
    int MenuCam=14;
    int CatCam=15;

    public void choosePhotoFromGallary(String s) {
        System.out.println("jjgjghjhg"+s);
        if(s.equalsIgnoreCase("Userimage"))
        {
            System.out.println("fddfzdfzdffd"+s);
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, MenuGal);
        }else{
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, CatGal);
        }
    }
    private void takePhotoFromCamera(String s) {
        if(s.equalsIgnoreCase("Userimage")){
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, MenuCam);
        }else{

            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CatCam);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("vbcbcbbcb"+requestCode+"  "+resultCode);
        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }
        if (requestCode == MenuGal) {
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
        else if (requestCode == MenuCam) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ll_image.setBackgroundResource(R.drawable.imageback);
            menuimage1.setImageBitmap(thumbnail);
            image="image";
            encodedImage=getStringImage(thumbnail);
            contentURI= getImageUri(context,thumbnail);
            saveImage(thumbnail);


        }

        if(requestCode==CatGal){
            if (data != null) {
                contentURI1 = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), contentURI1);
                    String path = saveImage(bitmap);
                    System.out.println("sdfdsgfdg"+bitmap);
                    image2.setImageBitmap(bitmap);
                    image="image";
                    encodedImage=getStringImage(bitmap);
                    contentURI1 = getImageUri(context, bitmap);
                    System.out.println("sdfdfdfdfg"+contentURI1);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                }


            }
        }
        else if(requestCode==CatCam){
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            image2.setImageBitmap(thumbnail);
            image="image";
            encodedImage=getStringImage(thumbnail);
            contentURI1= getImageUri(context,thumbnail);
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



    private void category() {
        final ProgressDialog progressDialog = new ProgressDialog(EditDriverActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        try {
            if(contentURI != null){
                file1= FileUtil.from(context, contentURI);
                System.out.println("imaggagag"+file1);
            }

        }
        catch (IOException ee)
        {ee.printStackTrace();}


        try {
            String DriverName=ownername.getText().toString();
            String AddressName=address.getText().toString();
            String phonenumbers=mobilenumber.getText().toString();
            String IdentityNo=vehcilenumber.getText().toString();
            String OrganizationId=sharedPreferences.getString(CommonUtils.shared_ORG_ID,"");

            JSONObject object1 = new JSONObject();
            object1.put("DriverName",DriverName);
            object1.put("Address",AddressName);
            object1.put("PhoneNo",phonenumbers);
            object1.put("IdentityNo",IdentityNo);
            object1.put("Driverid",Driverid);
            object1.put("IsActive","true");
            object1.put("IsDeleted","false");
            object1.put("OrganizationId",OrganizationId);
            AndroidNetworking.upload("http://vts.bluehawk.ai/services/api/DriverManagement/Save")
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
                                    Intent intent=new Intent(EditDriverActivity.this,DriverManagementActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    Toast.makeText(EditDriverActivity.this,SuccessMessage,Toast.LENGTH_LONG).show();

                                }
                                else if(IsSuccess.equalsIgnoreCase("false")){
                                    progressDialog.dismiss();
                                    Toast.makeText(EditDriverActivity.this, ErrorMessage, Toast.LENGTH_SHORT).show();
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(EditDriverActivity.this, e.toString(), Toast.LENGTH_SHORT).show();

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





//        AddDeviceApi.getRetrofitInstance(context).create(AddDriverInterface.class).registration2(prameter,"").enqueue(new Callback<AddDevicegetset>() {
//            @Override
//            public void onResponse(Call<AddDevicegetset> call, Response<AddDevicegetset> response) {
//                progressDialog.dismiss();
//                if(response.body().getSuccess()==false){
//                    Toast.makeText(EditDriverActivity.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
//                }else if( response.body().getSuccess()==true){
//                    Intent intent=new Intent(EditDriverActivity.this,DriverManagementActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
//                    Toast.makeText(EditDriverActivity.this, response.body().getSuccessMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AddDevicegetset> call, Throwable t) {
//
//            }
//        });
    }

    public void back(View view) {
        Intent intent=new Intent(EditDriverActivity.this,DriverManagementActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}