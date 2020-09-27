package com.spirituspoland.memogame.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.spirituspoland.memogame.GameData;
import com.spirituspoland.memogame.R;
import com.spirituspoland.memogame.data.GameDifficulty;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ChooseDifficultyActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_dificulty);
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }

    public void startEasyGame(View view) {
        startActivity(prepareIntent(GameDifficulty.EASY));
    }

    public void startMediumGame(View view) {
        startActivity(prepareIntent(GameDifficulty.MEDIUM));
    }

    public void startHardGame(View view) {
        startActivity(prepareIntent(GameDifficulty.HARD));
    }

    public void startCustomGame(View view) {
        Intent intent = new Intent(getApplicationContext(), CustomGameActivity.class);
        startActivity(intent);
    }

    private Intent prepareIntent( GameDifficulty difficulty) {
        GameData game = new GameData(difficulty);
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        intent.putExtra("game", game);
        return intent;
    }
}