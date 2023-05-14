package com.example.pacmanracinggame;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.button.MaterialButton;

public class Menu_Activity extends AppCompatActivity {
    private MaterialButton menu_BTN_slowMode;
    private MaterialButton menu_BTN_fastMode;
    private MaterialButton menu_BTN_sensorsMode;
    private MaterialButton menu_BTN_top10;
    private eGameType gameType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findView();
        initView();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    GPS.MY_PERMISSIONS_REQUEST_LOCATION
            );
        }
    }

    private void initView() {
        menu_BTN_slowMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameType = eGameType.SLOW_MODE;
                openGame();
            }
        });
        menu_BTN_fastMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameType = eGameType.FAST_MODE;
                openGame();
            }
        });
        menu_BTN_sensorsMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameType = eGameType.SENSORS_MODE;
                openGame();
            }
        });
        menu_BTN_top10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRecords();

            }
        });
    }

    private void openRecords() {
        Intent intent = new Intent(this, RecordsActivity.class);
        startActivity(intent);
        finish();
    }

    private void openGame() {
        Intent intent = new Intent(this, Racing_Game.class);
        intent.putExtra(DataManager.GAME_TYPE, gameType.name());
        startActivity(intent);
        finish();
    }

    private void findView() {
        menu_BTN_fastMode = findViewById(R.id.menu_BTN_fastMode);
        menu_BTN_slowMode = findViewById(R.id.menu_BTN_slowMode);
        menu_BTN_sensorsMode = findViewById(R.id.menu_BTN_sensorsMode);
        menu_BTN_top10 = findViewById(R.id.menu_BTN_top10);
    }
}