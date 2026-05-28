package com.example.miniproyecto_sudoku.model.validator;

/**
 * Interface responsible for validating Sudoku rules and board state.
 * Defines methods for validating moves, rows, columns,
 * blocks, and board completion.
 *
 * @author Juan José Morera Gómez
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 * @since 1.0
 */
public interface ISudokuValidator {

    /**
     * Determines whether a move is valid according to Sudoku rules.
     *
     * @param row row index of the cell.
     * @param col column index of the cell.
     * @param value value to validate.
     * @return true if the move is valid, otherwise false.
     */
    boolean isMoveValid(int row, int col, int value);

    /**
     * Determines whether a specific row is valid.
     *
     * @param row row index to validate.
     * @return true if the row is valid, otherwise false.
     */
    boolean isRowValid(int row);

    /**
     * Determines whether a specific column is valid.
     *
     * @param col column index to validate.
     * @return true if the column is valid, otherwise false.
     */
    boolean isColumnValid(int col);

    /**
     * Determines whether a specific block is valid.
     *
     * @param row row index belonging to the block.
     * @param col column index belonging to the block.
     * @return true if the block is valid, otherwise false.
     */
    boolean isBlockValid(int row, int col);

    /**
     * Determines whether the Sudoku board is completely solved.
     *
     * @return true if the board is complete, otherwise false.
     */
    boolean isBoardComplete();
}