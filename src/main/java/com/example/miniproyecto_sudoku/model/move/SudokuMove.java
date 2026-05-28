package com.example.miniproyecto_sudoku.model.move;

import java.io.Serializable;

/**
 * Class that represents a move made on the Sudoku board.
 * Stores the affected cell position, the previous value,
 * and the new assigned value.
 *
 * @author Juan José Morera Gómez
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 * @since 1.0
 */
public class SudokuMove implements Serializable {

    /**
     * Serial version identifier for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Row index of the affected cell.
     */
    private final int row;

    /**
     * Column index of the affected cell.
     */
    private final int col;

    /**
     * Previous value stored in the cell before the move.
     */
    private final int previousValue;

    /**
     * New value assigned to the cell after the move.
     */
    private final int newValue;

    /**
     * Creates a new SudokuMove with the specified information.
     *
     * @param row row index of the affected cell.
     * @param col column index of the affected cell.
     * @param previousValue value stored before the move.
     * @param newValue value assigned after the move.
     */
    public SudokuMove(int row, int col, int previousValue, int newValue) {
        this.row = row;
        this.col = col;
        this.previousValue = previousValue;
        this.newValue = newValue;
    }

    /**
     * Returns the row index of the affected cell.
     *
     * @return row index of the move.
     */
    public int getRow() { return row; }

    /**
     * Returns the column index of the affected cell.
     *
     * @return column index of the move.
     */
    public int getCol() { return col; }

    /**
     * Returns the previous value stored in the cell.
     *
     * @return previous value before the move.
     */
    public int getPreviousValue() { return previousValue; }

    /**
     * Returns the new value assigned to the cell.
     *
     * @return new value after the move.
     */
    public int getNewValue() { return newValue; }
}