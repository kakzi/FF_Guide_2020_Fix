package com.firefreefear.tips.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firefreefear.tips.R;
import com.firefreefear.tips.adapter.TipsAdapter;
import com.firefreefear.tips.model.TipsModel;
import com.firefreefear.tips.viewmodel.TipsViewModel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class TipsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TipsAdapter tipsAdapter;
    private TipsViewModel tipsViewModel;
    private ShimmerFrameLayout shimmerFrameLayout;
    private AdView adView;
    private RelativeLayout banner_layout;
    private InterstitialAd interstitialAd;
    public static int nbShowInterstitial = 2;
    public static int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

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
                TipsActivity.this.banner_layout.setVisibility(View.VISIBLE);
            }
        });

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getResources().getString(R.string.interstitialAds));
        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });

        requestNewInterstitial();

        shimmerFrameLayout = findViewById(R.id.shimmer_effect_tips);
        tipsAdapter = new TipsAdapter();
        tipsAdapter.notifyDataSetChanged();

        recyclerView= findViewById(R.id.rv_tips);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(tipsAdapter);

        tipsAdapter.setOnItemClickCallback(new TipsAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TipsModel data) {
                try {
                    Intent intent = new Intent(TipsActivity.this, DetailTipsActivity.class);
                    intent.putExtra(DetailTipsActivity.EXTRA_TIPS_DATA, data);
                    startActivity(intent);
                    if (mCount == nbShowInterstitial) {
                        if (interstitialAd.isLoaded())
                            interstitialAd.show();
                        mCount = 0;
                    }
                    ++ mCount;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        tipsViewModel = ViewModelProviders.of(this).get(TipsViewModel.class);
        tipsViewModel.getTips().observe(this, getTips);
        tipsViewModel.setTips(this);
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
    }

    private Observer<ArrayList<TipsModel>> getTips = new Observer<ArrayList<TipsModel>>() {
        @Override
        public void onChanged(ArrayList<TipsModel> tips) {
            if (tips != null) {
                tipsAdapter.setData(tips);
            }
            shimmerFrameLayout.stopShimmer();
            shimmerFrameLayout.setVisibility(View.GONE);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameLayout.startShimmer();
    }

    @Override
    protected void onPause() {
        shimmerFrameLayout.stopShimmer();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Toast.makeText(this, "Anda Memilih Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rate:
                Toast.makeText(this, "Anda Memilih Rate", Toast.LENGTH_SHORT).show();
                break;
            case R.id.privacy:
                Toast.makeText(this, "Anda Memilih Privacy", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
