package com.spirituspoland.memogame.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.core.app.NavUtils;

import com.spirituspoland.memogame.GameData;
import com.spirituspoland.memogame.R;
import com.spirituspoland.memogame.data.GameDifficulty;


public class CustomGameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_game);
        prepareValues();
    }
    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }


    private void prepareValues() {
        Spinner rowsDropdown = findViewById(R.id.rowsDropdown);
        Spinner columnsDropdown = findViewById(R.id.columnsDropdown);
        Integer[] rows = {2, 3, 4, 5, 6};
        Integer[] columns = {2, 3, 4, 5, 6};
        ArrayAdapter<Integer> rowAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, rows);
        rowsDropdown.setAdapter(rowAdapter);
        ArrayAdapter<Integer> columnAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, columns);
        columnsDropdown.setAdapter(columnAdapter);
    }

    public int getSelectedRows() {
        Spinner rowSpinner = findViewById(R.id.rowsDropdown);
        return (Integer) rowSpinner.getSelectedItem();
    }
    public int getSelectedColumns() {
        Spinner columnSpinner = findViewById(R.id.columnsDropdown);
        return (Integer) columnSpinner.getSelectedItem();
    }

    public void createGame(View view){
        getSelectedColumns();
        getSelectedRows();
        startGame(getSelectedRows(),getSelectedColumns());
    }

    public void startGame(int rows, int columns)
    {
        Intent gameIntent= new Intent(this, GameActivity.class);
        GameDifficulty.GameDifficultyBuilder gameDifficultyBuilder=new GameDifficulty.GameDifficultyBuilder(rows,columns);
        gameIntent.putExtra("game",new GameData(gameDifficultyBuilder.build()));
        startActivity(gameIntent);
    }

}