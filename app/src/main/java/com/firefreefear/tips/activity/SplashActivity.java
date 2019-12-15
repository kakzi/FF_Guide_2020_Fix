package com.firefreefear.tips.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

import com.firefreefear.tips.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class SplashActivity extends AppCompatActivity {

    private Button btn_start;
    private ProgressBar pb_splash;
    private final int SPLASH_DISPLAY_LENGTH = 7000;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btn_start = findViewById(R.id.btn_start);
        pb_splash = findViewById(R.id.pb_splash);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getResources().getString(R.string.interstitialAds));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                requestInterstitialAds();
            }
        });

        requestInterstitialAds();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pb_splash.setVisibility(View.GONE);
                btn_start.setVisibility(View.VISIBLE);
            }
        }, SPLASH_DISPLAY_LENGTH);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                if (interstitialAd.isLoaded())
                    interstitialAd.show();
                finish();
            }
        });

    }

    private void requestInterstitialAds() {
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
    }
}
