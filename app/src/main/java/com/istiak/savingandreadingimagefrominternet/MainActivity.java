package com.istiak.savingandreadingimagefrominternet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static java.lang.System.out;

public class MainActivity extends AppCompatActivity {
    File direct;
    ImageView imageView;
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        saveImage();
        reload();

    }


    private void saveImage(){
        direct = new File(Environment.getExternalStorageDirectory()
                + "/Istiak");

        if (!direct.exists()) {
            direct.mkdirs();
        }

        DownloadManager mgr = (DownloadManager) getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);

        Uri downloadUri = Uri.parse("https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png");
        DownloadManager.Request request = new DownloadManager.Request(
                downloadUri);

        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI
                        | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false).setTitle("Demo")
                .setDescription("Something useful. No, really.")
                .setDestinationInExternalPublicDir("/Istiak", "fileName.jpg");

        Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
        mgr.enqueue(request);
    }



    private void reload(){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File file = new File(direct, "fileName" + ".jpg");
        imageView.setImageDrawable(Drawable.createFromPath(file.toString()));
    }
}
