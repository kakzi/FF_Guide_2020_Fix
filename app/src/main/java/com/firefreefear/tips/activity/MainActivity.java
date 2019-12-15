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
import com.firefreefear.tips.adapter.MenuAdapter;
import com.firefreefear.tips.model.MenuModel;
import com.firefreefear.tips.viewmodel.MenuViewModel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardedAd;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private MenuViewModel viewModel;
    private ShimmerFrameLayout shimmerFrameLayout;
    private AdView adView;
    private RelativeLayout banner_layout;
    private InterstitialAd interstitialAd;
    public static int nbShowInterstitial = 1;
    public static int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                MainActivity.this.banner_layout.setVisibility(View.VISIBLE);
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

        shimmerFrameLayout = findViewById(R.id.shimmer_effect);

        menuAdapter = new MenuAdapter();
        menuAdapter.notifyDataSetChanged();

        recyclerView = findViewById(R.id.rv_menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(menuAdapter);

        menuAdapter.setOnItemClickCallback(new MenuAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(MenuModel data) {
                try {
                    switch (data.getId()) {
                        case 1:
                            Intent intent = new Intent(MainActivity.this, TipsActivity.class);
                            startActivity(intent);
                            showInterstitialAds();
                            break;
                        case 2:
                            Toast.makeText(MainActivity.this, "Anda memilih Waepons", Toast.LENGTH_SHORT).show();
                            Intent intentWaepon = new Intent(MainActivity.this, WaeponActivity.class);
                            startActivity(intentWaepon);
                            showInterstitialAds();
                            break;
                        case 3:
                            Toast.makeText(MainActivity.this, "Anda memilih Character", Toast.LENGTH_SHORT).show();
                            Intent intentCharacter = new Intent(MainActivity.this, CharacterActivity.class);
                            startActivity(intentCharacter);
                            showInterstitialAds();
                            break;
                        case 4:
                            Toast.makeText(MainActivity.this, "Anda memilih vehicles", Toast.LENGTH_SHORT).show();
                            Intent intentVehicles = new Intent(MainActivity.this, VehiclesActivity.class);
                            startActivity(intentVehicles);
                            showInterstitialAds();
                            break;
                        case 5:
                            Toast.makeText(MainActivity.this, "Anda memilih Diamond", Toast.LENGTH_SHORT).show();
                            Intent intentDiamond = new Intent(MainActivity.this, DiamondActivity.class);
                            startActivity(intentDiamond);
                            showInterstitialAds();
                            break;
                        case 6:
                            Toast.makeText(MainActivity.this, "Anda memilih Wallpaper", Toast.LENGTH_SHORT).show();
                            Intent intentWallpaper = new Intent(MainActivity.this, WallpaperActivity.class);
                            startActivity(intentWallpaper);
                            showInterstitialAds();
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        viewModel = ViewModelProviders.of(this).get(MenuViewModel.class);
        viewModel.getMenu().observe(this, getMenu);
        viewModel.setMenu(this);
    }

    private void showInterstitialAds() {
        if (mCount == nbShowInterstitial) {
            if (interstitialAd.isLoaded())
                interstitialAd.show();
            mCount = 0;
        }
        ++ mCount;
    }

    private Observer<ArrayList<MenuModel>> getMenu = new Observer<ArrayList<MenuModel>>() {
        @Override
        public void onChanged(ArrayList<MenuModel> menus) {
            if (menus != null) {
                menuAdapter.setData(menus);
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

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
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
