package com.firefreefear.tips.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.firefreefear.tips.R;
import com.firefreefear.tips.model.Vehicles;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class DetailVehicleActivity extends AppCompatActivity {

    public static final String EXTRA_VEHICLES_DATA = "extra_data_vehicles";
    private ImageView img_vehicle;
    private TextView name, desc;
    private String img_url, name_vehicle, desc_vehicle;

    private AdView adView;
    private RelativeLayout banner_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_vehicle);

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
                DetailVehicleActivity.this.banner_layout.setVisibility(View.VISIBLE);
            }
        });

        img_vehicle = findViewById(R.id.image_vehicles);
        name = findViewById(R.id.name_vehicles);
        desc = findViewById(R.id.desc);

        getDataTips();
        setDataTips();
    }

    private void getDataTips() {
        Vehicles vehicles = getIntent().getParcelableExtra(EXTRA_VEHICLES_DATA);
        img_url = vehicles.getImage_url();
        name_vehicle = vehicles.getName();
        desc_vehicle = vehicles.getDesc();
    }

    private void setDataTips() {
        Glide.with(this).load(img_url).transition(DrawableTransitionOptions.withCrossFade())
                .into(img_vehicle);
        name.setText(name_vehicle);
        desc.setText(desc_vehicle);
    }
}
