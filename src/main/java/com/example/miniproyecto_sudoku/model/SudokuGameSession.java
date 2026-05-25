package com.example.miniproyecto_sudoku.model;

import java.io.File;

public class SudokuGameSession implements ISudokuGameSession {

    private static final String SESSION_FILE = "sudoku_session.dat";

    @Override
    public void saveSession(SudokuBoard board) {
    }

    @Override
    public SudokuBoard loadSession() {
        return null;
    }

    @Override
    public boolean hasSavedSession() {
        return new File(SESSION_FILE).exists();
    }

    @Override
    public void clearSession() {
    }
}