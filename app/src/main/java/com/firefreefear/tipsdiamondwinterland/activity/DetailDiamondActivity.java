package com.firefreefear.tipsdiamondwinterland.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firefreefear.tipsdiamondwinterland.R;
import com.firefreefear.tipsdiamondwinterland.model.Diamond;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class DetailDiamondActivity extends AppCompatActivity {

    public static final String EXTRA_DIAMOND_DATA = "extra_diamond_data";
    private TextView sub_tips, title, desc;
    private String subtips, tips, description;
    private AdView adView;
    private RelativeLayout banner_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_diamond);

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
                DetailDiamondActivity.this.banner_layout.setVisibility(View.VISIBLE);
            }
        });

        sub_tips = findViewById(R.id.sub_tips);
        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);

        getDataTips();
        setDataTips();
    }

    private void getDataTips() {
        Diamond diamond = getIntent().getParcelableExtra(EXTRA_DIAMOND_DATA);
        subtips = diamond.getImage_url();
        tips = diamond.getTitle();
        description = diamond.getDesc();
    }

    private void setDataTips() {
        sub_tips.setText(subtips);
        title.setText(tips);
        desc.setText(description);
    }
}
