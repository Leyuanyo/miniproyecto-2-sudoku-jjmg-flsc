package com.example.miniproyecto_sudoku.model;

public class SudokuBoard implements ISudokuBoard {

    public static final int SIZE = 6;
    public static final int BLOCK_ROWS = 2;
    public static final int BLOCK_COLS = 3;

    private final int[][] grid;
    private final boolean[][] fixed;
    private SudokuGameState gameState;

    public SudokuBoard() {
        this.grid = new int[SIZE][SIZE];
        this.fixed = new boolean[SIZE][SIZE];
        this.gameState = SudokuGameState.IN_PROGRESS;
    }

    @Override
    public int getCell(int row, int col) {
        return grid[row][col];
    }

    @Override
    public void setCell(int row, int col, int value) {
        if (fixed[row][col]) return;
        grid[row][col] = value;
    }

    @Override
    public void setFixed(int row, int col, int value) {
        grid[row][col] = value;
        fixed[row][col] = true;
    }

    @Override
    public boolean isFixed(int row, int col) {
        return fixed[row][col];
    }

    @Override
    public int[][] getGridCopy() {
        int[][] copy = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, SIZE);
        }
        return copy;
    }

    @Override
    public SudokuGameState getGameState() { return gameState; }

    @Override
    public void setGameState(SudokuGameState gameState) { this.gameState = gameState; }
}