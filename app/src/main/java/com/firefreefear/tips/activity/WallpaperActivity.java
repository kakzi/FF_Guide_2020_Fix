package com.firefreefear.tips.activity;

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
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firefreefear.tips.R;
import com.firefreefear.tips.adapter.WallpaperAdapter;
import com.firefreefear.tips.model.WallpaperModel;
import com.firefreefear.tips.viewmodel.WallpaperViewModel;

import java.util.ArrayList;

public class WallpaperActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WallpaperAdapter wallpaperAdapter;
    private WallpaperViewModel wallpaperViewModel;
    private ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);

        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        recyclerView = findViewById(R.id.rv_wallpaper);
        wallpaperAdapter = new WallpaperAdapter();
        wallpaperAdapter.notifyDataSetChanged();

        wallpaperAdapter.setOnItemClickCallback(new WallpaperAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(WallpaperModel data) {
                Toast.makeText(WallpaperActivity.this, "Anda memilih "+ data.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(WallpaperActivity.this, DetailWallpaperActivity.class);
                intent.putExtra(DetailWallpaperActivity.EXTRA_WALLPAPER, data);
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(wallpaperAdapter);

        wallpaperViewModel = ViewModelProviders.of(this).get(WallpaperViewModel.class);
        wallpaperViewModel.getWallpaper().observe(this, getWallpaper);
        wallpaperViewModel.setWallpaper(this);

    }

    private Observer<ArrayList<WallpaperModel>> getWallpaper = new Observer<ArrayList<WallpaperModel>>() {
        @Override
        public void onChanged(ArrayList<WallpaperModel> wallpaper) {
            if (wallpaper != null) {
                wallpaperAdapter.setWallpaper(wallpaper);
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
