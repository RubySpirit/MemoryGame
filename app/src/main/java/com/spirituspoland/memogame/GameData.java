package com.spirituspoland.memogame;

import com.spirituspoland.memogame.data.GameDifficulty;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GameData implements Serializable {

    private final LocalDateTime created;
    private final GameDifficulty difficulty;

    private int moves;
    private LocalDateTime ended;

    public GameData(GameDifficulty gameDifficulty)
    {
        this.difficulty=gameDifficulty;
        this.created=LocalDateTime.now();
        this.moves=0;
    }
    public void increaseMoveNumber() {
        moves++;
    }

    public int getRows()
    {
        return difficulty.getRows();
    }
    public int getColumns()
    {
        return difficulty.getColumns();
    }
}