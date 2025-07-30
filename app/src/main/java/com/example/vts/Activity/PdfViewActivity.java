package com.example.vts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.widget.CheckBox;

import com.example.vts.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfViewActivity extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener {
    PDFView pdfView;
    Bitmap bmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);














































































































































        Bundle extras = getIntent().getExtras();
        bmp= (Bitmap) extras.getParcelable("imagebitmap");
        System.out.println("yyghgghg"+bmp);
        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.PageInfo pi = new PdfDocument.PageInfo.Builder(bmp.getWidth(), bmp.getHeight(), 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pi);
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#FFFFFF"));
        canvas.drawPaint(paint);

        bmp = Bitmap.createScaledBitmap(bmp, bmp.getWidth(), bmp.getHeight(), true);
        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bmp, 0, 0, null);
        pdfDocument.finishPage(page);


        File root = new File(Environment.getExternalStorageDirectory(), "PDF FOlder");
        if (!root.exists()) {
            root.mkdir();
        }
        File file = new File(root, "data.pdf");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            pdfDocument.writeTo(fileOutputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfDocument.close();

        pdfView = (PDFView) findViewById(R.id.mypdf);
        File root1 = new File(Environment.getExternalStorageDirectory(), "PDF FOlder");
        File file1 = new File(root1, "data.pdf");
        pdfView.fromFile(file1).defaultPage(0).enableSwipe(true).swipeHorizontal(false).onPageChange(this).enableAnnotationRendering(true).onLoad(this).scrollHandle(new DefaultScrollHandle(this)).load();
    }

    @Override
    public void loadComplete(int nbPages) {

    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }
}