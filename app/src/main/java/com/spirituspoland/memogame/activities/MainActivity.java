 package com.spirituspoland.memogame.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.spirituspoland.memogame.R;

 public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void startGame(View view)
    {
        Intent intent = new Intent(this, ChooseDifficultyActivity.class);
        startActivity(intent);
    }
}