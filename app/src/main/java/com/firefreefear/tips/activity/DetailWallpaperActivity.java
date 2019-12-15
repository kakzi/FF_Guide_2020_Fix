package com.firefreefear.tips.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.firefreefear.tips.R;
import com.firefreefear.tips.model.WallpaperModel;

import java.io.IOException;

public class DetailWallpaperActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_WALLPAPER = "extra_wallpaper";
    private ImageView wallpaper;
    private Button btn_setWallpaper;

    String img_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wallpaper);

        wallpaper = findViewById(R.id.wallpaper);
        btn_setWallpaper = findViewById(R.id.setWallpaper);

        btn_setWallpaper.setOnClickListener(this);

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
        switch (id){
            case R.id.setWallpaper:{
                setWall();
                break;
            }
        }
    }

    private void setWall() {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        try {
            wallpaperManager.setBitmap(viewToBitmap(wallpaper, wallpaper.getWidth(),wallpaper.getHeight()));
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
}
