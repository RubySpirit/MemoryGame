package com.spirituspoland.memogame.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.NavUtils;

import com.spirituspoland.memogame.GameData;
import com.spirituspoland.memogame.R;
import com.spirituspoland.memogame.data.Pokemon;
import com.spirituspoland.memogame.data.Tile;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class GameActivity extends Activity {

    private static final double MARGIN_PROPORTION = 0.1;
    private static final double SIZE_PROPORTION = 0.9;
    private static final int MILLISECONDS_BEFORE_HIDE = 1500;
    private static final int MILLISECONDS_AFTER_MOVE = 400;
    private GameData gameData;
    private Drawable questionMark;
    private ConstraintLayout tilesLayout;
    private ConstraintLayout gameLayout;
    private TextView moveTextView;
    private int size;
    private int margin;
    private Tile[][] tiles;
    private Tile first;
    private Tile second;
    private boolean canMove = true;
    private String movesTemplate;
    private boolean isEnded = false;
    private int backpressed = 0;

    @Override
    public void onBackPressed() {
        backpressed++;
        Handler handler = new Handler();
        handler.postDelayed(() -> backpressed = 0, 3000);
        if (backpressed < 2) {
            Toast toast = Toast.makeText(getApplicationContext(), "To go to Game Difficulty press back again", Toast.LENGTH_SHORT);
            toast.setMargin(50, 50);
            toast.show();
        }
        if (backpressed == 2) {
            NavUtils.navigateUpFromSameTask(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        canMove = false;
        setContentView(R.layout.game);
        questionMark = getDrawable(R.drawable.question_mark);
        gameData = getGameData();
        tiles = new Tile[gameData.getColumns()][gameData.getRows()];
        moveTextView = findViewById(R.id.movesNumber);
        movesTemplate = getString(R.string.moves) + "%d";
    }

    @Override
    protected void onStart() {
        super.onStart();
        prepareGameLayout();
        generateTiles();
        arrangeTiles();
        finishGamePreparing();
    }

    @Override
    protected void onResume() {
        super.onResume();
        moveTextView.setText(String.format(movesTemplate, gameData.getMoves()));
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            List<Tile> tileList = Arrays.stream(tiles).flatMap(Arrays::stream).collect(Collectors.toList());
            tileList.forEach(Tile::hidePokemon);
            canMove = true;
        }, MILLISECONDS_BEFORE_HIDE);
    }

    private void finishGamePreparing() {
        tilesLayout.getLayoutParams().width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(gameLayout);
        constraintSet.centerHorizontally(tilesLayout.getId(), gameLayout.getId());
        constraintSet.applyTo(gameLayout);
    }

    private GameData getGameData() {
        Intent intent = getIntent();
        return (GameData) intent.getSerializableExtra("game");
    }

    private void prepareGameLayout() {
        tilesLayout = findViewById(R.id.tilesLayout);
        gameLayout = findViewById(R.id.gameLayout);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) tilesLayout.getLayoutParams();
        params.height = (int) (getScreenHeight() * 0.8);
        params.width = getScreenWidth();
        size = gameData.getColumns() > gameData.getRows() ? (int) ((tilesLayout.getLayoutParams().width / (gameData.getColumns()) * SIZE_PROPORTION)) : (int) ((tilesLayout.getLayoutParams().width / (gameData.getRows()) * SIZE_PROPORTION));
        margin = gameData.getColumns() > gameData.getRows() ? (int) ((tilesLayout.getLayoutParams().width / (gameData.getColumns()) * MARGIN_PROPORTION)) : (int) ((tilesLayout.getLayoutParams().width / (gameData.getRows()) * MARGIN_PROPORTION));
        tilesLayout.setLayoutParams(params);
    }

    private void generateTiles() {
        List<Pokemon> pokemons = createPokemons();
        boolean isOddNumber = (gameData.getRows() * gameData.getColumns()) % 2 == 1;

        for (int i = 0, pokemonIterator = 0; i < gameData.getColumns(); i++) {
            for (int j = 0; j < gameData.getRows(); j++) {
                if (isOddNumber && i == gameData.getColumns() / 2 && j == gameData.getRows() / 2) {
                    tiles[i][j] = new Tile(getDrawable(R.drawable.ic_launcher_background), getDrawable(R.drawable.ic_launcher_background), new ImageView(this), size, "blank", false, getDrawable(R.drawable.rounded_corners));
                    Tile tile = tiles[i][j];
                    tilesLayout.addView(tile.getImageView());
                } else {
                    tiles[i][j] = new Tile(pokemons.get(pokemonIterator).getDrawable(), questionMark, new ImageView(this), size, pokemons.get(pokemonIterator).getName(), true, getDrawable(R.drawable.rounded_corners));
                    Tile tile = tiles[i][j];
                    tile.getImageView().setOnClickListener(view -> move(tile));
                    tilesLayout.addView(tile.getImageView());
                    pokemonIterator++;
                }
            }
        }
    }

    private List<Pokemon> createPokemons() {
        List<Pokemon> pokemons1 = new ArrayList<>(getPokemons(getPokemonsNumber()));
        List<Pokemon> pokemons2 = new ArrayList<>(pokemons1);
        pokemons1.addAll(pokemons2);
        Collections.shuffle(pokemons1);
        return pokemons1;
    }

    private void arrangeTiles() {
        for (int column = 0; column < gameData.getColumns(); column++) {
            for (int row = 0; row < gameData.getRows(); row++) {
                if (tiles[column][row] == null) {
                    continue;
                }
                ImageView img = tiles[column][row].getImageView();
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(tilesLayout);

                if (column == 0) {
                    int leftMargin = (getScreenWidth() - gameData.getColumns() * (size + margin)) / 2;
                    constraintSet.connect(tilesLayout.getId(), ConstraintSet.LEFT, img.getId(), ConstraintSet.LEFT, 8000);
                }
                if (row == 0) {
                    constraintSet.connect(tilesLayout.getId(), ConstraintSet.TOP, img.getId(), ConstraintSet.TOP, margin);
                }
                if (column > 0) {
                    constraintSet.connect(img.getId(), ConstraintSet.LEFT, tiles[column - 1][row].getImageView().getId(), ConstraintSet.RIGHT, margin);
                }
                if (row > 0) {
                    constraintSet.connect(img.getId(), ConstraintSet.TOP, tiles[column][row - 1].getImageView().getId(), ConstraintSet.BOTTOM, margin);
                }
                constraintSet.applyTo(tilesLayout);
            }
        }
    }


    private int getPokemonsNumber() {
        return gameData.getRows() * gameData.getColumns() / 2;
    }

    private Set<Pokemon> getPokemons(int pokemonListSize) {
        Set<Pokemon> pokemons = new HashSet<>();
        do {
            pokemons.add(getRandomPokemon());
        } while (pokemons.size() < pokemonListSize);
        return pokemons;
    }

    private Pokemon getRandomPokemon() {
        String[] pokemons = this.getApplicationContext().getResources().getStringArray(R.array.pokemons);
        String poke = pokemons[getRandomItemIndex(pokemons.length)];
        int pokemonIdentifier = this.getApplicationContext().getResources().getIdentifier(poke, "drawable", getApplicationContext().getPackageName());
        return new Pokemon(poke, getDrawable(pokemonIdentifier));
    }

    private int getRandomItemIndex(int collectionSize) {
        Random rn = new Random();
        return rn.nextInt(collectionSize + 1);
    }

    private static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    private static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    private void move(Tile tile) {
        if (canMove) {
            canMove = false;
            if (tile.clicked()) {
                gameData.increaseMoveNumber();
                moveTextView.setText(String.format(movesTemplate, gameData.getMoves()));
            }
            if (first == null) {
                first = tile;
                canMove = true;
            } else {
                Handler handler = new Handler();
                second = tile;
                if (first.getPokemonName().equals(second.getPokemonName()) && !first.equals(second)) {
                    handler.postDelayed(() -> {
                        first.foundPair();
                        second.foundPair();
                        first = null;
                        second = null;
                        canMove = true;
                        checkEnd();
                    }, MILLISECONDS_AFTER_MOVE);
                } else {
                    handler.postDelayed(() -> {
                        first.hidePokemon();
                        second.hidePokemon();
                        first = null;
                        second = null;
                        canMove = true;
                    }, MILLISECONDS_AFTER_MOVE);
                }
            }
        }
    }

    private void checkEnd() {
        if (isEnd()) {
            isEnded = true;
            canMove = false;
            ConstraintLayout endLayout = findViewById(R.id.endGame);
            endLayout.setVisibility(View.VISIBLE);
            TextView finishGame = findViewById(R.id.finishGameTextView);
            String endGameText = String.format(getString(R.string.finishedGame), gameData.getMoves());
            Button btn = findViewById(R.id.startAgain);
            btn.setClickable(true);
            finishGame.setText(endGameText);
        }
    }

    public void newGame(View view) {
        if (isEnded) {
            NavUtils.navigateUpFromSameTask(this);
        }
    }

    private boolean isEnd() {
        List<Tile> tileList = Arrays.stream(tiles).flatMap(Arrays::stream).collect(Collectors.toList());
        return tileList.stream().noneMatch(Tile::isActive);
    }

}