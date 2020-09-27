 package com.spirituspoland.memogame.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.spirituspoland.memogame.R;

 public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animate();
    }


    public void animate()
    {
       ImageView pokeball= findViewById(R.id.pokeball);
        TranslateAnimation mAnimation = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE,
                0f,
                TranslateAnimation.ABSOLUTE,
                0f,
                TranslateAnimation.RELATIVE_TO_SELF,
                0.0f,
                TranslateAnimation.RELATIVE_TO_PARENT,
                0.1f);
        mAnimation.setDuration(2000);
        mAnimation.setRepeatCount(-1);
        mAnimation.setRepeatMode(Animation.REVERSE);
        mAnimation.setInterpolator(new LinearInterpolator());
        pokeball.startAnimation(mAnimation);
    }
    public void startGame(View view)
    {
        Intent intent = new Intent(this, ChooseDifficultyActivity.class);
        startActivity(intent);
    }
}