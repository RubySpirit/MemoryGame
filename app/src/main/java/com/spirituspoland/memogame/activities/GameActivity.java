package com.spirituspoland.memogame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.spirituspoland.memogame.GameData;
import com.spirituspoland.memogame.R;

public class GameActivity extends AppCompatActivity {

    private GameData gameData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        gameData = getGameData();
        showGameInfo();
    }

    private GameData getGameData() {
        Intent intent = getIntent();
        return (GameData) intent.getSerializableExtra("game");
    }
    private void showGameInfo()
    {
        Toast.makeText(this, "YOUR columns : " + gameData.getColumns(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "YOUR crows : " + gameData.getRows(), Toast.LENGTH_SHORT).show();
    }

    private void generateTiles()
    {

    }


}