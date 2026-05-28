package com.example.miniproyecto_sudoku.model;

/**
 * Enumeration representing the possible states
 * of a Sudoku game.
 *
 * @author Juan José Morera Gómez
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 * @since 1.0
 */
public enum SudokuGameState {

    /**
     * Indicates that the game is still in progress.
     */
    IN_PROGRESS,

    /**
     * Indicates that the Sudoku board has been completed successfully.
     */
    COMPLETED,

    /**
     * Indicates that the board contains invalid values
     * according to Sudoku rules.
     */
    INVALID
}