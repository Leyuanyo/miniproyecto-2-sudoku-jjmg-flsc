package com.example.miniproyecto_sudoku.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SudokuGameSession implements ISudokuGameSession {

    private static final String SESSION_FILE = "sudoku_session.dat";

    @Override
    public void saveSession(SudokuBoard board) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(SESSION_FILE))) {
            oos.writeObject(board);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        new File(SESSION_FILE).delete();
    }
}