package com.example.miniproyecto_sudoku.model.session;

import com.example.miniproyecto_sudoku.model.board.SudokuBoard;

import java.io.*;

/**
 * Class responsible for managing Sudoku game sessions.
 * Handles saving, loading, checking, and clearing
 * game session data using file serialization.
 *
 * @author Juan José Morera Gómez
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 * @since 1.0
 */
public class SudokuGameSession implements ISudokuGameSession {

    /**
     * Name of the file used to store the game session.
     */
    private static final String SESSION_FILE = "sudoku_session.dat";

    /**
     * Saves the current Sudoku game session into a file.
     *
     * @param board Sudoku board to save.
     */
    @Override
    public void saveSession(SudokuBoard board) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(SESSION_FILE))) {
            oos.writeObject(board);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a previously saved Sudoku game session from a file.
     *
     * @return loaded SudokuBoard instance, or null if loading fails.
     */
    @Override
    public SudokuBoard loadSession() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(SESSION_FILE))) {
            return (SudokuBoard) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Determines whether a saved game session file exists.
     *
     * @return true if a saved session exists, otherwise false.
     */
    @Override
    public boolean hasSavedSession() {
        return new File(SESSION_FILE).exists();
    }

    /**
     * Deletes the saved game session file.
     */
    @Override
    public void clearSession() {
        new File(SESSION_FILE).delete();
    }
}