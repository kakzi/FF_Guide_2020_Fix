package com.firefreefear.tipsdiamondwinterland.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.firefreefear.tipsdiamondwinterland.R;
import com.firefreefear.tipsdiamondwinterland.model.TipsModel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class DetailTipsActivity extends AppCompatActivity {

    public static final String EXTRA_TIPS_DATA = "extra_tips_data";
    private ImageView img_tips;
    private TextView tittle, desc;
    private String image, title, description;

    private AdView adView;
    private RelativeLayout banner_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tips);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        banner_layout = findViewById(R.id.layout_banner);
        adView.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded(){
                super.onAdLoaded();
                DetailTipsActivity.this.banner_layout.setVisibility(View.VISIBLE);
            }
        });

        img_tips = findViewById(R.id.img_tips_detail);
        tittle = findViewById(R.id.tv_tittle_tips);
        desc = findViewById(R.id.desc);


        getDataTips();
        setDataTips();

    }

    private void getDataTips() {
        TipsModel tipsModel = getIntent().getParcelableExtra(EXTRA_TIPS_DATA);
        image = tipsModel.getImage_url();
        title = tipsModel.getTitle();
        description = tipsModel.getDesc();
    }

    private void setDataTips() {

        Glide.with(this).load(image).transition(DrawableTransitionOptions.withCrossFade()).into(img_tips);
        tittle.setText(title);
        desc.setText(description);

    }
    
}

