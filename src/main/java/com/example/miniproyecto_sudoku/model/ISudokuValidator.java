package com.example.miniproyecto_sudoku.model;

public interface ISudokuValidator {
    boolean isMoveValid(int row, int col, int value);
    boolean isRowValid(int row);
    boolean isColumnValid(int col);
    boolean isBlockValid(int row, int col);
    boolean isBoardComplete();
}