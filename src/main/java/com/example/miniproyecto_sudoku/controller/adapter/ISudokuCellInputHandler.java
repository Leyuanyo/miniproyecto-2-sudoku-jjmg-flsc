package com.example.miniproyecto_sudoku.controller.adapter;

public interface ISudokuCellInputHandler {
    void handleInput(int row, int col, int value);
    void handleClear(int row, int col);
}