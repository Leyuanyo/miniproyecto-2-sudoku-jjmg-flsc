package com.example.miniproyecto_sudoku.model.move;

import java.io.Serializable;

public class SudokuMove implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int row;
    private final int col;
    private final int previousValue;
    private final int newValue;

    public SudokuMove(int row, int col, int previousValue, int newValue) {
        this.row = row;
        this.col = col;
        this.previousValue = previousValue;
        this.newValue = newValue;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public int getPreviousValue() { return previousValue; }
    public int getNewValue() { return newValue; }
}