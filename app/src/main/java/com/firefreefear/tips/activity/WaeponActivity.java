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
import com.firefreefear.tips.adapter.WaeponAdapter;
import com.firefreefear.tips.model.WaeponModel;
import com.firefreefear.tips.viewmodel.WaeponViewModel;

import java.util.ArrayList;

public class WaeponActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WaeponAdapter waeponAdapter;
    private WaeponViewModel waeponViewModel;
    private ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waepon);

        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        recyclerView = findViewById(R.id.rv_waepon);
        waeponAdapter = new WaeponAdapter();
        waeponAdapter.notifyDataSetChanged();

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(waeponAdapter);

        waeponAdapter.setOnItemClickCallback(new WaeponAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(WaeponModel data) {
                Toast.makeText(WaeponActivity.this, "Anda memilih " + data.getName_waepon(), Toast.LENGTH_SHORT).show();
            }
        });

        waeponViewModel = ViewModelProviders.of(this).get(WaeponViewModel.class);
        waeponViewModel.getWaepon().observe(this, getWaepon);
        waeponViewModel.setWaepon(this);
    }

    private Observer<ArrayList<WaeponModel>> getWaepon = new Observer<ArrayList<WaeponModel>>() {
        @Override
        public void onChanged(ArrayList<WaeponModel> waepon) {
            if (waepon != null) {
                waeponAdapter.setWaepon(waepon);
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
