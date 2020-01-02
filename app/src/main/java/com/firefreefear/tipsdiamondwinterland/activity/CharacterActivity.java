package com.firefreefear.tipsdiamondwinterland.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.firefreefear.tipsdiamondwinterland.R;
import com.firefreefear.tipsdiamondwinterland.adapter.CharacterAdapter;
import com.firefreefear.tipsdiamondwinterland.model.Character;
import com.firefreefear.tipsdiamondwinterland.viewmodel.CharacterViewModel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class CharacterActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;
    private CharacterViewModel characterViewModel;
    private ShimmerFrameLayout shimmerFrameLayout;
    private AdView adView;
    private RelativeLayout banner_layout;
    private InterstitialAd interstitialAd;
    public static int nbShowInterstitial = 2;
    public static int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

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
                CharacterActivity.this.banner_layout.setVisibility(View.VISIBLE);
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

        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        recyclerView = findViewById(R.id.rv_character);
        characterAdapter = new CharacterAdapter();
        characterAdapter.notifyDataSetChanged();

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(characterAdapter);

        characterAdapter.setOnItemClickCallback(new CharacterAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Character data) {
                try {
                    Intent intent = new Intent(CharacterActivity.this, DetailCharacterActivity.class);
                    intent.putExtra(DetailCharacterActivity.EXTRA_CHAR_DATA, data);
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

        characterViewModel = ViewModelProviders.of(this).get(CharacterViewModel.class);
        characterViewModel.getCharacter().observe(this, getCharacter);
        characterViewModel.setCharacter(this);

    }

    private Observer<ArrayList<Character>> getCharacter = new Observer<ArrayList<Character>>() {
        @Override
        public void onChanged(ArrayList<Character> character) {
            if (character != null) {
                characterAdapter.setCharacter(character);
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


    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
    }

}
