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
import com.firefreefear.tipsdiamondwinterland.model.Character;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class DetailCharacterActivity extends AppCompatActivity {

    public static final String EXTRA_CHAR_DATA = "extra_char_data";
    private ImageView img_char;
    private TextView name, desc;
    private String img_url, name_char, desc_char;

    private AdView adView;
    private RelativeLayout banner_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_character);

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
                DetailCharacterActivity.this.banner_layout.setVisibility(View.VISIBLE);
            }
        });

        img_char = findViewById(R.id.image_char);
        name = findViewById(R.id.name_char);
        desc = findViewById(R.id.desc);

        getDataTips();
        setDataTips();
    }

    private void setDataTips() {
        Glide.with(this).load(img_url).transition(DrawableTransitionOptions.withCrossFade())
                .into(img_char);
        name.setText(name_char);
        desc.setText(desc_char);
    }

    private void getDataTips() {

        Character character = getIntent().getParcelableExtra(EXTRA_CHAR_DATA);
        img_url = character.getImage_url();
        name_char = character.getName();
        desc_char = character.getDesc();

    }
}
