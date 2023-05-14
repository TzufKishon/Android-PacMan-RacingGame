package com.example.pacmanracinggame;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameManager extends Activity {
    private int[][] ghostsCoinsMatrix;
    private int[] pacmansArr;
    private int ifRandomCalled;
    private int lives;
    private int intOdometer = 0;
    private boolean gameOverFlag;
    private AppCompatImageView[] ghosts;
    private AppCompatImageView[] hearts;
    private AppCompatImageView[] pacmans;
    private AppCompatImageView[] coins;
    private AppCompatTextView strOdometer;
    Racing_Game rGame;
    private String gameType;
    private int ifStart = 0;
    private MediaPlayer introSound;

    public int getIntOdometer() {
        return intOdometer;
    }

    public GameManager(Racing_Game rGame, int[][] ghostsCoinsMatrix, int[] pacmansArr, int ifRandomCalled, int lives, boolean gameOverFlag,
                       AppCompatImageView[] ghosts, AppCompatImageView[] hearts, AppCompatImageView[] pacmans, AppCompatImageView[] coins, AppCompatTextView odometer, String gameType) {
        this.ghostsCoinsMatrix = ghostsCoinsMatrix;
        this.pacmansArr = pacmansArr;
        this.ifRandomCalled = ifRandomCalled;
        this.lives = lives;
        this.gameOverFlag = gameOverFlag;
        this.ghosts = ghosts;
        this.hearts = hearts;
        this.pacmans = pacmans;
        this.coins = coins;
        this.strOdometer = odometer;
        this.rGame = rGame;
        this.gameType = gameType;
        introSound = MediaPlayer.create(rGame.getApplicationContext(), R.raw.intro);
    }

    void updateGhostsCoins(Racing_Game rGame, Timer timer) {
        if (ifStart == 0) {
            introSound.start();
            ifStart++;
        }
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                updateGhostsCoinsMatrix(rGame);
            }
        };
        if (gameType.equals("SLOW_MODE")) {
            enabledBtn();
            timer.scheduleAtFixedRate(task, 0, 1000);
        }
        if (gameType.equals("FAST_MODE")) {
            enabledBtn();
            timer.scheduleAtFixedRate(task, 0, 500);
        }
        if (gameType.equals("SENSORS_MODE")) {
            initViewsSensors(timer, task, rGame);
        }
    }

    private void enabledBtn() {
        rGame.getGameBtnLeft().setVisibility(View.VISIBLE);
        rGame.getGameBtnRight().setVisibility(View.VISIBLE);
        rGame.getGameBtnLeft().setEnabled(true);
        rGame.getGameBtnRight().setEnabled(true);
    }

    private void reset() {
        lives = 3;
        resetGhostsCoinsMatrixPositions();
        refreshHeartsUI();
        for (int i = 0; i < 5; i++) {
            pacmansArr[i] = 0;
            pacmans[i].setVisibility(View.INVISIBLE);
        }
        pacmansArr[2] = 1;
        pacmans[2].setVisibility(View.VISIBLE);
        refreshHeartsUI();
    }

    private void initViewsSensors(Timer timer, TimerTask task, Racing_Game rGame) {
        rGame.getGameBtnLeft().setVisibility(View.INVISIBLE);
        rGame.getGameBtnRight().setVisibility(View.INVISIBLE);
        rGame.getGameBtnLeft().setEnabled(false);
        rGame.getGameBtnRight().setEnabled(false);
        rGame.initEventListener();
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    private void updateGhostsCoinsMatrix(Racing_Game rGame) {
        updateGhostsCoinsMatrixPositions();
        randomGhostCoinsMatrixPosition();
        updateGhostsCoinsPositions();
        checkIfCrash(rGame);
        checkIfGameOver(rGame);
        updateOdometer(1);
    }

    private void updateOdometer(int increase) {
        intOdometer = intOdometer + increase;
        final String Odometer = String.valueOf(intOdometer);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                strOdometer.setText(Odometer);
            }
        });
    }

    private void updateGhostsCoinsMatrixPositions() {
        for (int i = 6; i > 0; i--)
            for (int j = 0; j < 5; j++) {
                ghostsCoinsMatrix[i][j] = ghostsCoinsMatrix[i - 1][j];
            }
    }

    private void resetGhostsCoinsMatrixPositions() {
        for (int i = 0; i > 6; i++)
            for (int j = 0; j < 5; j++) {
                ghostsCoinsMatrix[i][j] = 0;
            }
        updateGhostsCoinsMatrixPositions();
    }

    private void randomGhostCoinsMatrixPosition() {
        for (int i = 0; i < 5; i++) {
            ghostsCoinsMatrix[0][i] = 0;
        }
        if (ifRandomCalled == 0) {
            ifRandomCalled = 1;
            Random rand1 = new Random();
            int randomNum1 = rand1.nextInt(5);
            Random rand2 = new Random();
            int randomNum2 = rand2.nextInt(2);
            randomNum2++;
            ghostsCoinsMatrix[0][randomNum1] = randomNum2;
        } else
            ifRandomCalled = 0;
    }

    private void updateGhostsCoinsPositions() {
        for (int i = 0; i < 35; i++) {
            ghosts[i].setVisibility(View.INVISIBLE);
            coins[i].setVisibility(View.INVISIBLE);
        }
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 5; j++) {
                if (ghostsCoinsMatrix[i][j] == 1) {
                    final int ghostIndex = 5 * i + j;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ghosts[ghostIndex].setVisibility(View.VISIBLE);
                        }
                    });
                }
                if (ghostsCoinsMatrix[i][j] == 2) {
                    final int coinsIndex = 5 * i + j;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            coins[coinsIndex].setVisibility(View.VISIBLE);
                        }
                    });
                }
            }
    }

    void clicked(int side, Racing_Game rGame) {
        int index = getPacmanIndex();
        if (side == 0) {
            if (index > 0)
                updatePacmansUI(side, index);
        } else {
            if (index < 4)
                updatePacmansUI(side, index);
        }
        checkIfCrash(rGame);
        checkIfGameOver(rGame);
    }

    void updatePacmansUI(int side, int index) {
        if (side == 0) {
            pacmansArr[index] = 0;
            pacmans[index].setVisibility(View.INVISIBLE);
            pacmansArr[index - 1] = 1;
            pacmans[index - 1].setVisibility(View.VISIBLE);
        } else {
            pacmansArr[index] = 0;
            pacmans[index].setVisibility(View.INVISIBLE);
            pacmansArr[index + 1] = 1;
            pacmans[index + 1].setVisibility(View.VISIBLE);
        }
    }

    void checkIfCrash(Racing_Game rGame) {
        for (int i = 0; i < ghostsCoinsMatrix[6].length; i++) {
            if (ghostsCoinsMatrix[6][i] == 1 && pacmansArr[i] == 1) {
                if (lives > 1) {
                    crash(rGame);
                    lives--;
                    GameServices.crashSound(rGame.getApplicationContext());
                    refreshHeartsUI();
                } else {
                    lives--;
                    refreshHeartsUI();
                }
            }
            if (ghostsCoinsMatrix[6][i] == 2 && pacmansArr[i] == 1) {
                GameServices.coinSound(rGame.getApplicationContext());
                GameServices.showToast(rGame.getApplicationContext(), "+10");
                updateOdometer(10);
            }
        }
    }

    void checkIfGameOver(Racing_Game rGame) {
        if (lives == 0 && !gameOverFlag) {
            gameOverFlag = true;
            gameOver(rGame);
            refreshHeartsUI();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            gameOverFlag = false;
                            reset();
                        }
                    }, 1500);
                }
            });
        }
    }

    int getPacmanIndex() {
        for (int i = 0; i < pacmans.length; i++) {
            if (pacmansArr[i] == 1) {
                return i;
            }
        }
        return -1;
    }

    private void crash(Racing_Game rGame) {
        GameServices.vibrate(rGame.getApplicationContext());
        GameServices.showToast(rGame.getApplicationContext(), "Crash");
    }

    private void gameOver(Racing_Game rGame) {
        GameServices.vibrate(rGame.getApplicationContext());
        GameServices.showToast(rGame.getApplicationContext(), "Game Over");
        GameServices.gameOverSound(rGame.getApplicationContext());
        rGame.gameOver();
    }

    private void refreshHeartsUI() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < hearts.length; i++) {
                    hearts[i].setVisibility(View.INVISIBLE);
                }
                for (int i = 0; i < lives; i++) {
                    hearts[i].setVisibility(View.VISIBLE);
                }
            }
        });
    }
}