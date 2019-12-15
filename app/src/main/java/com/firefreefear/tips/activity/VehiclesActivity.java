package com.firefreefear.tips.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firefreefear.tips.R;
import com.firefreefear.tips.adapter.VehiclesAdapter;
import com.firefreefear.tips.model.Vehicles;
import com.firefreefear.tips.viewmodel.VehiclesViewModel;

import java.util.ArrayList;

public class VehiclesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VehiclesAdapter vehiclesAdapter;
    private VehiclesViewModel vehiclesViewModel;
    private ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles);

        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        vehiclesAdapter = new VehiclesAdapter();
        vehiclesAdapter.notifyDataSetChanged();

        recyclerView = findViewById(R.id.rv_vehicles);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(vehiclesAdapter);

        vehiclesAdapter.setOnItemClickCallback(new VehiclesAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Vehicles data) {
                Toast.makeText(VehiclesActivity.this, "Anda memilih "+ data.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        vehiclesViewModel = ViewModelProviders.of(this).get(VehiclesViewModel.class);
        vehiclesViewModel.getVehicles().observe(this, getVehicles);
        vehiclesViewModel.setVehicles(this);

    }

    private Observer<ArrayList<Vehicles>> getVehicles = new Observer<ArrayList<Vehicles>>() {
        @Override
        public void onChanged(ArrayList<Vehicles> vehicles) {
            if (vehicles != null){
                vehiclesAdapter.setVehicles(vehicles);
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
