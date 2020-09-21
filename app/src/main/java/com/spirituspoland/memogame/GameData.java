package com.spirituspoland.memogame;

import com.spirituspoland.memogame.data.GameDifficulty;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GameData implements Serializable {

    private final int rows;
    private final int columns;
    private final LocalDateTime created;
    private final GameDifficulty difficulty;

    private int moves;
    private LocalDateTime ended;


    public void increaseMoveNumber() {
        moves++;
    }

}