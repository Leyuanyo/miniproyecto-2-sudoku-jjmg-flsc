package com.example.miniproyecto_sudoku.model.board;

import java.io.Serializable;

/**
 * Class that represents a saved state of a Sudoku board.
 * Stores a copy of the board grid and the current position
 * associated with the game state.
 *
 * @author Juan José Morera Gómez
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 * @since 1.0
 */
public class SudokuBoardState implements ISudokuBoardState, Serializable {

    /**
     * Serial version identifier for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Matrix representing the Sudoku board grid.
     */
    private final int[][] grid;

    /**
     * Position associated with the board state.
     */
    private final int position;

    /**
     * Creates a new SudokuBoardState with the specified grid and position.
     *
     * @param grid matrix representing the Sudoku board.
     * @param position current position associated with the state.
     */
    public SudokuBoardState(int[][] grid, int position) {
        this.grid = new int[6][6];
        for (int i = 0; i < 6; i++) {
            System.arraycopy(grid[i], 0, this.grid[i], 0, 6);
        }
        this.position = position;
    }

    /**
     * Returns the grid associated with the board state.
     *
     * @return two-dimensional array representing the Sudoku grid.
     */
    @Override
    public int[][] getGrid() { return grid; }

    /**
     * Returns the position associated with the board state.
     *
     * @return position value of the board state.
     */
    @Override
    public int getPosition() { return position; }
}