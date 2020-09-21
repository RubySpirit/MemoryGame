package com.spirituspoland.memogame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.spirituspoland.memogame.R;

public class CustomGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_game);
        prepareValues();
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
        Integer rowNumber = (Integer) rowSpinner.getSelectedItem();
        Toast.makeText(this, "YOUR SELECTION IS : " + rowNumber, Toast.LENGTH_SHORT).show();
        return rowNumber;
    }
    public int getSelectedColumns() {
        Spinner columnSpinner = findViewById(R.id.columnsDropdown);
        Integer columnNumber = (Integer) columnSpinner.getSelectedItem();
        Toast.makeText(this, "YOUR SELECTION IS : " + columnNumber, Toast.LENGTH_SHORT).show();
        return columnNumber;
    }

    public void createGame(View view){
        getSelectedColumns();
        getSelectedRows();
    }

}