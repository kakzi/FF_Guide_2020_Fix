package com.firefreefear.tips.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.firefreefear.tips.R;
import com.firefreefear.tips.model.WallpaperModel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailWallpaperActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_WALLPAPER = "extra_wallpaper";
    private ImageView wallpaper;
    private Button btn_setWallpaper;

    String img_url;
    private AdView adView;
    private RelativeLayout banner_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wallpaper);

        wallpaper = findViewById(R.id.wallpaper);
        btn_setWallpaper = findViewById(R.id.setWallpaper);

        btn_setWallpaper.setOnClickListener(this);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        banner_layout = findViewById(R.id.layout_banner);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                DetailWallpaperActivity.this.banner_layout.setVisibility(View.VISIBLE);
            }
        });

        getData();
        setData();
    }

    private void getData() {
        WallpaperModel wallpaperModel = getIntent().getParcelableExtra(EXTRA_WALLPAPER);
        img_url = wallpaperModel.getImage_url();
    }

    private void setData() {
        Glide.with(this).load(img_url).transition(DrawableTransitionOptions.withCrossFade())
                .into(wallpaper);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.setWallpaper: {
                setWall();
                break;
            }
        }
    }

    private void setWall() {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        try {
            wallpaperManager.setBitmap(viewToBitmap(wallpaper, wallpaper.getWidth(), wallpaper.getHeight()));
            Toast.makeText(this, "Succes changes Wallpaper", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Bitmap viewToBitmap(View view, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_download, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.download:
                Toast.makeText(this, "Anda Memilih Download", Toast.LENGTH_SHORT).show();
                startSave();
                break;
            case R.id.share:
                Toast.makeText(this, "Anda Memilih Share", Toast.LENGTH_SHORT).show();
                startShare();
                break;
            case R.id.rate:
                Toast.makeText(this, "Anda Memilih Rate", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startShare() {

    }

    private void startSave() {

    }

}
