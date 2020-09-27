package com.spirituspoland.memogame.data;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Tile {

    private ImageView imageView;
    private Drawable pokemonImg;
    private Drawable questionMarkImg;
    private String pokemonName;
    private boolean isActive;
    private boolean isShowed;

    public Tile(Drawable pokemon, Drawable questionMarkImg, ImageView imageView, int size, String pokemonName, boolean isActive, Drawable background) {
        this.imageView = imageView;
        this.pokemonImg = pokemon;
        this.pokemonName = pokemonName;
        this.questionMarkImg = questionMarkImg;
        this.imageView.setId(View.generateViewId());
        if (!isActive) {
            this.imageView.setImageDrawable(null);
            this.imageView.setVisibility(View.INVISIBLE);
            pokemonImg = null;
            this.questionMarkImg = null;
        }
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(size, size);
        this.imageView.setLayoutParams(params);
        this.imageView.getClipToOutline();
        this.imageView.setBackgroundColor(Color.argb(255, 255, 255, 255));
        this.imageView.setBackground(background);
        this.isActive = isActive;
        this.isShowed = true;
        if(this.isActive)
        {
            showPokemon();
        }
    }

    public void showPokemon() {
        if (isActive) {
            imageView.setImageDrawable(pokemonImg);
            isShowed = true;
        }
    }

    public void hidePokemon() {
        if (isActive) {
            imageView.setImageDrawable(questionMarkImg);
            isShowed = false;
        }
    }

    public boolean clicked() {
        if (isActive) {
            if (isShowed) {
                hidePokemon();
            } else {
                showPokemon();
            }
            return true;
        }
        return false;
    }

    public void foundPair() {
        this.imageView.setImageDrawable(null);
        this.imageView.setVisibility(View.INVISIBLE);
        isActive = false;
    }

}
