package com.example.pacmanracinggame;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.button.MaterialButton;
import java.util.Timer;

public class Racing_Game extends AppCompatActivity {
    private int[][] ghostsCoinsMatrix = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
    private int[] pacmansArr = {0, 0, 1, 0, 0};
    private int ifRandomCalled = 0;
    private int lives = 3;
    private boolean gameOverFlag = false;
    private AppCompatImageView[] ghosts;
    private AppCompatImageView[] hearts;
    private AppCompatImageView[] pacmans;
    private AppCompatImageView[] coins;
    private AppCompatImageView game_IMG_heart1;
    private AppCompatImageView game_IMG_heart2;
    private AppCompatImageView game_IMG_heart3;
    private AppCompatImageView ghost_image1;
    private AppCompatImageView ghost_image2;
    private AppCompatImageView ghost_image3;
    private AppCompatImageView ghost_image4;
    private AppCompatImageView ghost_image5;
    private AppCompatImageView ghost_image6;
    private AppCompatImageView ghost_image7;
    private AppCompatImageView ghost_image8;
    private AppCompatImageView ghost_image9;
    private AppCompatImageView ghost_image10;
    private AppCompatImageView ghost_image11;
    private AppCompatImageView ghost_image12;
    private AppCompatImageView ghost_image13;
    private AppCompatImageView ghost_image14;
    private AppCompatImageView ghost_image15;
    private AppCompatImageView ghost_image16;
    private AppCompatImageView ghost_image17;
    private AppCompatImageView ghost_image18;
    private AppCompatImageView ghost_image19;
    private AppCompatImageView ghost_image20;
    private AppCompatImageView ghost_image21;
    private AppCompatImageView ghost_image22;
    private AppCompatImageView ghost_image23;
    private AppCompatImageView ghost_image24;
    private AppCompatImageView ghost_image25;
    private AppCompatImageView ghost_image26;
    private AppCompatImageView ghost_image27;
    private AppCompatImageView ghost_image28;
    private AppCompatImageView ghost_image29;
    private AppCompatImageView ghost_image30;
    private AppCompatImageView ghost_image31;
    private AppCompatImageView ghost_image32;
    private AppCompatImageView ghost_image33;
    private AppCompatImageView ghost_image34;
    private AppCompatImageView ghost_image35;
    private AppCompatImageView coin_image1;
    private AppCompatImageView coin_image2;
    private AppCompatImageView coin_image3;
    private AppCompatImageView coin_image4;
    private AppCompatImageView coin_image5;
    private AppCompatImageView coin_image6;
    private AppCompatImageView coin_image7;
    private AppCompatImageView coin_image8;
    private AppCompatImageView coin_image9;
    private AppCompatImageView coin_image10;
    private AppCompatImageView coin_image11;
    private AppCompatImageView coin_image12;
    private AppCompatImageView coin_image13;
    private AppCompatImageView coin_image14;
    private AppCompatImageView coin_image15;
    private AppCompatImageView coin_image16;
    private AppCompatImageView coin_image17;
    private AppCompatImageView coin_image18;
    private AppCompatImageView coin_image19;
    private AppCompatImageView coin_image20;
    private AppCompatImageView coin_image21;
    private AppCompatImageView coin_image22;
    private AppCompatImageView coin_image23;
    private AppCompatImageView coin_image24;
    private AppCompatImageView coin_image25;
    private AppCompatImageView coin_image26;
    private AppCompatImageView coin_image27;
    private AppCompatImageView coin_image28;
    private AppCompatImageView coin_image29;
    private AppCompatImageView coin_image30;
    private AppCompatImageView coin_image31;
    private AppCompatImageView coin_image32;
    private AppCompatImageView coin_image33;
    private AppCompatImageView coin_image34;
    private AppCompatImageView coin_image35;
    private AppCompatImageView pacman_image1;
    private AppCompatImageView pacman_image2;
    private AppCompatImageView pacman_image3;
    private AppCompatImageView pacman_image4;
    private AppCompatImageView pacman_image5;
    private AppCompatTextView game_TEXT_odometer;
    private MaterialButton game_BTN_left;
    private MaterialButton game_BTN_right;
    private GameManager gameM;
    Timer timer;
    private boolean isPaused = false;
    private String gameType;
    private Sensor sensor;
    private SensorManager sensorManager;
    private SensorEventListener sensorEventListener;
    private long timestamp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_racing_game);
        findViews();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gameType = String.valueOf(eGameType.valueOf(getIntent().getStringExtra(DataManager.GAME_TYPE)));
        gameM = new GameManager(this, ghostsCoinsMatrix, pacmansArr, ifRandomCalled, lives, gameOverFlag, ghosts, hearts, pacmans, coins, game_TEXT_odometer, gameType);
        initViews(gameM);
        timer = new Timer();
        gameM.updateGhostsCoins(Racing_Game.this, timer);
    }

    public void initEventListener() {
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                calculateStep(x);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
    }

    private void calculateStep(float x) {
        if (System.currentTimeMillis() - timestamp > 500) {
            timestamp = System.currentTimeMillis();
            if (x > 2.0) {
                gameM.clicked(0, this);
            }
            if (x < -2.0) {
                gameM.clicked(1, this);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
        timer.cancel();
        isPaused = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        if (isPaused) {
            timer = new Timer();
            gameM.updateGhostsCoins(Racing_Game.this, timer);
            isPaused = false;
        }
    }

    public void gameOver() {
        Intent intent = new Intent(this, GetName.class);
        intent.putExtra(GetName.KEY_SCORE, gameM.getIntOdometer());
        startActivity(intent);
        finish();
    }

    private void initViews(GameManager gameM) {
        game_BTN_left.setOnClickListener(v -> gameM.clicked(0, this));
        game_BTN_right.setOnClickListener(v -> gameM.clicked(1, this));
    }

    public MaterialButton getGameBtnLeft() {
        return game_BTN_left;
    }

    public MaterialButton getGameBtnRight() {
        return game_BTN_right;
    }

    private void findViews() {
        game_BTN_left = findViewById(R.id.game_BTN_left);
        game_BTN_right = findViewById(R.id.game_BTN_right);
        game_TEXT_odometer = findViewById(R.id.game_TEXT_odometer);
        hearts = new AppCompatImageView[]{
                game_IMG_heart1 = findViewById(R.id.game_IMG_heart1),
                game_IMG_heart2 = findViewById(R.id.game_IMG_heart2),
                game_IMG_heart3 = findViewById(R.id.game_IMG_heart3),
        };
        pacmans = new AppCompatImageView[]{
                pacman_image1 = findViewById(R.id.pacman_image1),
                pacman_image2 = findViewById(R.id.pacman_image2),
                pacman_image3 = findViewById(R.id.pacman_image3),
                pacman_image4 = findViewById(R.id.pacman_image4),
                pacman_image5 = findViewById(R.id.pacman_image5),
        };
        ghosts = new AppCompatImageView[]{
                ghost_image1 = findViewById(R.id.ghost_image1),
                ghost_image2 = findViewById(R.id.ghost_image2),
                ghost_image3 = findViewById(R.id.ghost_image3),
                ghost_image4 = findViewById(R.id.ghost_image4),
                ghost_image5 = findViewById(R.id.ghost_image5),
                ghost_image6 = findViewById(R.id.ghost_image6),
                ghost_image7 = findViewById(R.id.ghost_image7),
                ghost_image8 = findViewById(R.id.ghost_image8),
                ghost_image9 = findViewById(R.id.ghost_image9),
                ghost_image10 = findViewById(R.id.ghost_image10),
                ghost_image11 = findViewById(R.id.ghost_image11),
                ghost_image12 = findViewById(R.id.ghost_image12),
                ghost_image13 = findViewById(R.id.ghost_image13),
                ghost_image14 = findViewById(R.id.ghost_image14),
                ghost_image15 = findViewById(R.id.ghost_image15),
                ghost_image16 = findViewById(R.id.ghost_image16),
                ghost_image17 = findViewById(R.id.ghost_image17),
                ghost_image18 = findViewById(R.id.ghost_image18),
                ghost_image19 = findViewById(R.id.ghost_image19),
                ghost_image20 = findViewById(R.id.ghost_image20),
                ghost_image21 = findViewById(R.id.ghost_image21),
                ghost_image22 = findViewById(R.id.ghost_image22),
                ghost_image23 = findViewById(R.id.ghost_image23),
                ghost_image24 = findViewById(R.id.ghost_image24),
                ghost_image25 = findViewById(R.id.ghost_image25),
                ghost_image26 = findViewById(R.id.ghost_image26),
                ghost_image27 = findViewById(R.id.ghost_image27),
                ghost_image28 = findViewById(R.id.ghost_image28),
                ghost_image29 = findViewById(R.id.ghost_image29),
                ghost_image30 = findViewById(R.id.ghost_image30),
                ghost_image31 = findViewById(R.id.ghost_image31),
                ghost_image32 = findViewById(R.id.ghost_image32),
                ghost_image33 = findViewById(R.id.ghost_image33),
                ghost_image34 = findViewById(R.id.ghost_image34),
                ghost_image35 = findViewById(R.id.ghost_image35),
        };
        coins = new AppCompatImageView[]{
                coin_image1 = findViewById(R.id.coin_image1),
                coin_image2 = findViewById(R.id.coin_image2),
                coin_image3 = findViewById(R.id.coin_image3),
                coin_image4 = findViewById(R.id.coin_image4),
                coin_image5 = findViewById(R.id.coin_image5),
                coin_image6 = findViewById(R.id.coin_image6),
                coin_image7 = findViewById(R.id.coin_image7),
                coin_image8 = findViewById(R.id.coin_image8),
                coin_image9 = findViewById(R.id.coin_image9),
                coin_image10 = findViewById(R.id.coin_image10),
                coin_image11 = findViewById(R.id.coin_image11),
                coin_image12 = findViewById(R.id.coin_image12),
                coin_image13 = findViewById(R.id.coin_image13),
                coin_image14 = findViewById(R.id.coin_image14),
                coin_image15 = findViewById(R.id.coin_image15),
                coin_image16 = findViewById(R.id.coin_image16),
                coin_image17 = findViewById(R.id.coin_image17),
                coin_image18 = findViewById(R.id.coin_image18),
                coin_image19 = findViewById(R.id.coin_image19),
                coin_image20 = findViewById(R.id.coin_image20),
                coin_image21 = findViewById(R.id.coin_image21),
                coin_image22 = findViewById(R.id.coin_image22),
                coin_image23 = findViewById(R.id.coin_image23),
                coin_image24 = findViewById(R.id.coin_image24),
                coin_image25 = findViewById(R.id.coin_image25),
                coin_image26 = findViewById(R.id.coin_image26),
                coin_image27 = findViewById(R.id.coin_image27),
                coin_image28 = findViewById(R.id.coin_image28),
                coin_image29 = findViewById(R.id.coin_image29),
                coin_image30 = findViewById(R.id.coin_image30),
                coin_image31 = findViewById(R.id.coin_image31),
                coin_image32 = findViewById(R.id.coin_image32),
                coin_image33 = findViewById(R.id.coin_image33),
                coin_image34 = findViewById(R.id.coin_image34),
                coin_image35 = findViewById(R.id.coin_image35),
        };
    }
}