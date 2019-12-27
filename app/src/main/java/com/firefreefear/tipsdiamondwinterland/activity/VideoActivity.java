package com.firefreefear.tipsdiamondwinterland.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firefreefear.tipsdiamondwinterland.R;
import com.firefreefear.tipsdiamondwinterland.adapter.VideosAdapter;
import com.firefreefear.tipsdiamondwinterland.model.VideoModel;
import com.firefreefear.tipsdiamondwinterland.viewmodel.VideoViewModel;

import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VideosAdapter videosAdapter;
    private VideoViewModel videoViewModel;
    private ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        shimmerFrameLayout = findViewById(R.id.shimmer_effect_layout);
        videosAdapter = new VideosAdapter();
        videosAdapter.notifyDataSetChanged();

        recyclerView = findViewById(R.id.rv_video);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(videosAdapter);

        videosAdapter.setOnItemClickCallback(new VideosAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(VideoModel data) {
                Toast.makeText(VideoActivity.this, "Anda memilih "+ data.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });


        videoViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);
        videoViewModel.getVidoes().observe(this, getVideos);
        videoViewModel.setVideos(this);

    }

    private Observer<ArrayList<VideoModel>> getVideos = new Observer<ArrayList<VideoModel>>() {
        @Override
        public void onChanged(ArrayList<VideoModel> videoModels) {
            if (videoModels != null){
                videosAdapter.setVideos(videoModels);
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
