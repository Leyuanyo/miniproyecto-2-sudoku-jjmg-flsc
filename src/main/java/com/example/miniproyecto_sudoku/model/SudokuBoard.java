package com.example.miniproyecto_sudoku.model;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

public class SudokuBoard implements ISudokuBoard, Serializable {

    private static final long serialVersionUID = 1L;

    public static final int SIZE = 6;
    public static final int BLOCK_ROWS = 2;
    public static final int BLOCK_COLS = 3;

    private final int[][] grid;
    private final boolean[][] fixed;
    private final Deque<SudokuMove> moveHistory;
    private SudokuGameState gameState;

    public SudokuBoard() {
        this.grid = new int[SIZE][SIZE];
        this.fixed = new boolean[SIZE][SIZE];
        this.moveHistory = new ArrayDeque<>();
        this.gameState = SudokuGameState.IN_PROGRESS;
    }

    @Override
    public int getCell(int row, int col) {
        return grid[row][col];
    }

    @Override
    public void setCell(int row, int col, int value) {
        if (fixed[row][col]) return;
        int previous = grid[row][col];
        grid[row][col] = value;
        moveHistory.push(new SudokuMove(row, col, previous, value));
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

    public SudokuMove undoLastMove() {
        if (moveHistory.isEmpty()) return null;
        SudokuMove last = moveHistory.pop();
        grid[last.getRow()][last.getCol()] = last.getPreviousValue();
        return last;
    }

    public boolean canUndo() {
        return !moveHistory.isEmpty();
    }

    public void resetBoard() {
        moveHistory.clear();
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (!fixed[r][c]) grid[r][c] = 0;
            }
        }
        this.gameState = SudokuGameState.IN_PROGRESS;
    }

    @Override
    public SudokuGameState getGameState() { return gameState; }

    @Override
    public void setGameState(SudokuGameState gameState) { this.gameState = gameState; }
}