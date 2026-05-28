package com.example.miniproyecto_sudoku.model.board;

import com.example.miniproyecto_sudoku.model.SudokuGameState;

/**
 * Interface that defines the operations and behaviors
 * required for managing a Sudoku board.
 * Provides methods for accessing, modifying, and retrieving
 * the current game state.
 *
 * @author Juan José Morera Gómez
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 * @since 1.0
 */
public interface ISudokuBoard {

    /**
     * Returns the value stored in a specific cell of the board.
     *
     * @param row row index of the cell.
     * @param col column index of the cell.
     * @return value stored in the specified cell.
     */
    int getCell(int row, int col);

    /**
     * Sets a value in a specific cell of the board.
     *
     * @param row row index of the cell.
     * @param col column index of the cell.
     * @param value value to assign to the cell.
     */
    void setCell(int row, int col, int value);

    /**
     * Marks a cell as fixed and assigns its value.
     *
     * @param row row index of the cell.
     * @param col column index of the cell.
     * @param value fixed value assigned to the cell.
     */
    void setFixed(int row, int col, int value);

    /**
     * Determines whether a specific cell is fixed.
     *
     * @param row row index of the cell.
     * @param col column index of the cell.
     * @return true if the cell is fixed, otherwise false.
     */
    boolean isFixed(int row, int col);

    /**
     * Returns a copy of the current Sudoku grid.
     *
     * @return two-dimensional array containing the board values.
     */
    int[][] getGridCopy();

    /**
     * Returns the current game state associated with the board.
     *
     * @return current Sudoku game state.
     */
    SudokuGameState getGameState();

    /**
     * Updates the current game state of the board.
     *
     * @param gameState new game state to assign.
     */
    void setGameState(SudokuGameState gameState);
}