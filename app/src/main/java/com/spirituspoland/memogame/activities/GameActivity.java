package com.spirituspoland.memogame.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.spirituspoland.memogame.GameData;
import com.spirituspoland.memogame.R;

import java.time.LocalDateTime;

public class GameActivity extends AppCompatActivity {

    private GameData gameData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        gameData = getGameData();
    }

    private GameData getGameData() {
        Intent intent = getIntent();
        return (GameData) intent.getSerializableExtra("game");
    }


}