package com.firefreefear.tips.activity;

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
import com.firefreefear.tips.R;
import com.firefreefear.tips.adapter.DiamondAdapter;
import com.firefreefear.tips.model.Diamond;
import com.firefreefear.tips.viewmodel.DiamondViewModel;

import java.util.ArrayList;

public class DiamondActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DiamondAdapter diamondAdapter;
    private DiamondViewModel diamondViewModel;
    private ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diamond);

        shimmerFrameLayout = findViewById(R.id.shimmer_effect_diamond);
        diamondAdapter = new DiamondAdapter();
        diamondAdapter.notifyDataSetChanged();

        recyclerView = findViewById(R.id.rv_diamond);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(diamondAdapter);

        diamondAdapter.setOnItemClickCallback(new DiamondAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Diamond data) {
                Toast.makeText(DiamondActivity.this, "Anda Memilih "+ data.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });


        diamondViewModel = ViewModelProviders.of(this).get(DiamondViewModel.class);
        diamondViewModel.getDiamond().observe(this, getDiamond);
        diamondViewModel.setDiamond(this);

    }

    private Observer<ArrayList<Diamond>> getDiamond = new Observer<ArrayList<Diamond>>() {
        @Override
        public void onChanged(ArrayList<Diamond> diamonds) {
            if (diamonds != null){
                diamondAdapter.setDiamond(diamonds);
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