package com.thaihuy.demoasynctask;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnTaiHinh;
    ImageView imgHinh;
    ProgressDialog dialog;
    String link = "http://thuthuatphanmem.vn/uploads/2017/11/05/hinh-nen-4k-dep-5_124943.jpg";
    String link1 = "https://fptshop.com.vn/landing-samsung-galaxy-a6-a6plus/Content/images/bnr.png";
    String link2 = "http://4.bp.blogspot.com/-8Nws-9ApyVY/VdvLpqK7eXI/AAAAAAAAABw/TF0k0EyaTr8/s1600/change-your-body-dong-luc-thuc-day-co-gang-cua-cac-gymer-chuyen-nghiep-2.jpg";
    String link3 = "https://specials-images.forbesimg.com/imageserve/5756d3a3a7ea43396db26ac5/320x486.jpg?fit=scale&background=000000";


    ArrayList<String> dsHinh;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnTaiHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyTaiHinh();
            }
        });
    }

    private void xuLyTaiHinh() {
        int n = random.nextInt(4);

        ImageTask task = new ImageTask();
        task.execute(dsHinh.get(n));

    }

    private void addControls() {
        btnTaiHinh = findViewById(R.id.btnTaiHinh);
        imgHinh = findViewById(R.id.imgHinh);
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("Thông báo");
        dialog.setMessage("Đang tải vui lòng chờ...");
        dialog.setCanceledOnTouchOutside(false);

        dsHinh = new ArrayList<>();
        dsHinh.add(link);
        dsHinh.add(link1);
        dsHinh.add(link2);
        dsHinh.add(link3);


    }

    class ImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgHinh.setImageBitmap(bitmap);
            dialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                String link = params[0];
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(link).getContent());
                return bitmap;

            } catch (Exception ex) {
                Log.e("Error", ex.toString());

            }
            return null;
        }
    }
}
