package com.example.pacmanracinggame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.button.MaterialButton;

public class RecordsActivity extends AppCompatActivity {
    private MapsFragment mapsFragment;
    private MaterialButton records_BTN_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        Fragment_top10 fragment_top10 = new Fragment_top10(new Location() {
            @Override
            public void loc(double lat, double lng) {
                if (mapsFragment != null) {
                    mapsFragment.moveLoc(lat, lng);
                }
            }
        });

        mapsFragment = new MapsFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.records_LAY_top10, fragment_top10)
                .add(R.id.records_LAY_maps, mapsFragment)
                .commit();

        findViews();
        initViews();
    }

    private void initViews() {
        records_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGamePage();
            }
        });
    }

    private void openGamePage() {
        Intent intent = new Intent(this, Menu_Activity.class);
        startActivity(intent);
        finish();
    }

    private void findViews() {
        records_BTN_back = findViewById(R.id.records_BTN_back);
    }
}