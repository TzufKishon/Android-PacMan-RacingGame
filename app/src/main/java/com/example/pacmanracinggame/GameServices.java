package com.example.pacmanracinggame;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Toast;

public class GameServices {

    private static GameServices _instance = null;
    private Context context;

    public static void vibrate(Context context) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(500);
        }
    }

    private GameServices(Context context) {
        this.context = context;
    }

    public static void initHelper(Context context) {
        if (_instance == null) {
            _instance = new GameServices(context);
        }
    }

    public static void crashSound(Context applicationContext) {
        MediaPlayer crashSound = MediaPlayer.create(applicationContext, R.raw.fruit);
        crashSound.start();
    }

    public static void coinSound(Context applicationContext) {
        MediaPlayer coinSound = MediaPlayer.create(applicationContext, R.raw.ghost);
        coinSound.start();
    }

    public static void gameOverSound(Context applicationContext) {
        MediaPlayer gameOverSound = MediaPlayer.create(applicationContext, R.raw.gameover);
        gameOverSound.start();
    }

    public static void showToast(final Context context, final String message) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

}