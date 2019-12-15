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
import com.firefreefear.tips.adapter.CharacterAdapter;
import com.firefreefear.tips.model.Character;
import com.firefreefear.tips.viewmodel.CharacterViewModel;

import java.util.ArrayList;

public class CharacterActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;
    private CharacterViewModel characterViewModel;
    private ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

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
                Toast.makeText(CharacterActivity.this, "Anda memilih "+ data.getName(), Toast.LENGTH_SHORT).show();
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
