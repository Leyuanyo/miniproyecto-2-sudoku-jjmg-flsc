package com.example.miniproyecto_sudoku.model.session;

import com.example.miniproyecto_sudoku.model.board.SudokuBoard;

/**
 * Interface responsible for managing Sudoku game sessions.
 * Defines operations for saving, loading, checking,
 * and clearing game sessions.
 *
 * @author Juan José Morera Gómez
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 * @since 1.0
 */
public interface ISudokuGameSession {

    /**
     * Saves the current Sudoku game session.
     *
     * @param board Sudoku board to save.
     */
    void saveSession(SudokuBoard board);

    /**
     * Loads a previously saved Sudoku game session.
     *
     * @return loaded SudokuBoard instance.
     */
    SudokuBoard loadSession();

    /**
     * Determines whether a saved game session exists.
     *
     * @return true if a saved session exists, otherwise false.
     */
    boolean hasSavedSession();

    /**
     * Clears the currently saved game session.
     */
    void clearSession();
}