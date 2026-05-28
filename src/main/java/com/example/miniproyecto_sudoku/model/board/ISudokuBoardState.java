package com.example.miniproyecto_sudoku.model.board;

/**
 * Interface that represents the state of a Sudoku board.
 * Provides access to the grid data and the current position
 * associated with the board state.
 *
 * @author Juan José Morera Gómez
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 * @since 1.0
 */
public interface ISudokuBoardState {

    /**
     * Returns the grid associated with the board state.
     *
     * @return two-dimensional array representing the Sudoku grid.
     */
    int[][] getGrid();

    /**
     * Returns the current position associated with the board state.
     *
     * @return position value of the board state.
     */
    int getPosition();
}