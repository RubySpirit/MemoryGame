package com.spirituspoland.memogame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.spirituspoland.memogame.GameData;
import com.spirituspoland.memogame.R;
import com.spirituspoland.memogame.data.GameDifficulty;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ChooseDifficultyActivity extends AppCompatActivity {
    private static final int EASY_ROWS = 3;
    private static final int EASY_COLUMNS = 2;

    private static final int MEDIUM_ROWS = 4;
    private static final int MEDIUM_COLUMNS = 3;

    private static final int HARD_ROWS = 5;
    private static final int HARD_COLUMNS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_dificulty);
    }

    public void startEasyGame(View view) {
        startActivity(prepareIntent(EASY_ROWS, EASY_COLUMNS, GameDifficulty.EASY));
    }

    public void startMediumGame(View view) {
        startActivity(prepareIntent(MEDIUM_ROWS, MEDIUM_COLUMNS, GameDifficulty.MEDIUM));
    }

    public void startHardGame(View view) {
        startActivity(prepareIntent(HARD_ROWS, HARD_COLUMNS, GameDifficulty.HARD));
    }

    public void startCustomGame(View view) {
        Intent intent = new Intent(getApplicationContext(), CustomGameActivity.class);
        startActivity(intent);
    }


    private Intent prepareIntent(int rows, int columns, GameDifficulty difficulty) {
        GameData game = new GameData(rows, columns, LocalDateTime.now(), difficulty);
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        intent.putExtra("game", game);
        return intent;
    }
}