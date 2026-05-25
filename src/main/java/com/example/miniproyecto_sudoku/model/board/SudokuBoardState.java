package com.example.miniproyecto_sudoku.model.board;

import java.io.Serializable;

public class SudokuBoardState implements ISudokuBoardState, Serializable {

    private static final long serialVersionUID = 1L;

    private final int[][] grid;
    private final int position;

    public SudokuBoardState(int[][] grid, int position) {
        this.grid = new int[6][6];
        for (int i = 0; i < 6; i++) {
            System.arraycopy(grid[i], 0, this.grid[i], 0, 6);
        }
        this.position = position;
    }

    @Override
    public int[][] getGrid() { return grid; }

    @Override
    public int getPosition() { return position; }
}