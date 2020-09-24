package com.spirituspoland.memogame.data;

public enum GameDifficulty {

    EASY(3, 2),
    MEDIUM(4, 3),
    HARD(5, 4),
    CUSTOM;
    int rows;
    int columns;


    private GameDifficulty(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    private GameDifficulty() {

    }

    private void setRows(int rows) {
        this.rows = rows;
    }

    private void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public static class GameDifficultyBuilder {
        private final int rows;
        private final int columns;

        public GameDifficultyBuilder(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
        }

        public GameDifficulty build() {
            GameDifficulty gameDifficulty = GameDifficulty.CUSTOM;
            gameDifficulty.setColumns(columns);
            gameDifficulty.setRows(rows);
            return gameDifficulty;
        }
    }
}

