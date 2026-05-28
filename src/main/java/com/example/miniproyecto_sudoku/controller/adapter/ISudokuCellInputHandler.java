package com.example.miniproyecto_sudoku.controller.adapter;

/**
 * Interface responsible for handling inputs made in Sudoku cells.
 * Defines the required operations to enter and clear values within the board.
 *
 * @author Juan José Morera Gómez
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 * @since 1.0
 */
public interface ISudokuCellInputHandler {

    /**
     * Handles the input of a value in a specific board cell.
     *
     * @param row row of the selected cell.
     * @param col column of the selected cell.
     * @param value value entered in the cell.
     */
    void handleInput(int row, int col, int value);

    /**
     * Handles clearing the content of a specific board cell.
     *
     * @param row row of the selected cell.
     * @param col column of the selected cell.
     */
    void handleClear(int row, int col);
}