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
import com.firefreefear.tipsdiamondwinterland.model.WaeponModel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class DetailWaeponActivity extends AppCompatActivity {

    public static final String EXTRA_WAEPON_DATA = "extra_waepon_data";
    private ImageView img_waepon;
    private String url_detail;

    private AdView adView;
    private RelativeLayout banner_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_waepon);

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
                DetailWaeponActivity.this.banner_layout.setVisibility(View.VISIBLE);
            }
        });

        img_waepon = findViewById(R.id.image_waepon);

        getDataTips();
        setDataTips();
    }

    private void getDataTips() {

        WaeponModel waeponModel = getIntent().getParcelableExtra(EXTRA_WAEPON_DATA);
        url_detail = waeponModel.getUrl_detail();

    }

    private void setDataTips() {

        Glide.with(this).load(url_detail).transition(DrawableTransitionOptions.withCrossFade())
                .into(img_waepon);

    }
}
