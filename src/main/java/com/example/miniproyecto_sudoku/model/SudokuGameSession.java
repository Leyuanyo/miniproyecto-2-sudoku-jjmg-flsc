package com.example.miniproyecto_sudoku.model;

import java.io.*;

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
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(SESSION_FILE))) {
            return (SudokuBoard) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
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