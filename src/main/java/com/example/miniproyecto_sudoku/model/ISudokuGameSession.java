package com.example.miniproyecto_sudoku.model;

public interface ISudokuGameSession {

    void saveSession(SudokuBoard board);

    SudokuBoard loadSession();

    boolean hasSavedSession();

    void clearSession();
}