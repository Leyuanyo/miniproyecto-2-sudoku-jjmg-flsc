package com.example.miniproyecto_sudoku.model.board;

import com.example.miniproyecto_sudoku.model.SudokuGameState;

public interface ISudokuBoard {

    int getCell(int row, int col);

    void setCell(int row, int col, int value);

    void setFixed(int row, int col, int value);

    boolean isFixed(int row, int col);

    int[][] getGridCopy();

    SudokuGameState getGameState();

    void setGameState(SudokuGameState gameState);
}