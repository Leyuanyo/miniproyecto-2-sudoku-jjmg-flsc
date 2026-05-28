package com.example.miniproyecto_sudoku.model.board;

import com.example.miniproyecto_sudoku.model.SudokuGameState;
import com.example.miniproyecto_sudoku.model.move.SudokuMove;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Deque;

public class SudokuBoard implements ISudokuBoard, Serializable {

    private static final long serialVersionUID = 1L;

    public static final int SIZE = 6;
    public static final int BLOCK_ROWS = 2;
    public static final int BLOCK_COLS = 3;

    private final int[][] grid;
    /**
     * Matrix that stores the fixed cells of the Sudoku board.
     */
    private final boolean[][] fixed;

    /**
     * Stack containing the history of moves made during the game.
     */
    private final Deque<SudokuMove> moveHistory;

    /**
     * Current state of the Sudoku game.
     */
    private SudokuGameState gameState;

    /**
     * Matrix containing the complete solution of the Sudoku board.
     */
    private int[][] solution;

    /**
     * Creates a new SudokuBoard with an empty grid,
     * initialized fixed cells, and an empty move history.
     */
    public SudokuBoard() {
        this.grid = new int[SIZE][SIZE];
        this.fixed = new boolean[SIZE][SIZE];
        this.moveHistory = new LinkedList<>();
        this.gameState = SudokuGameState.IN_PROGRESS;
    }

    /**
     * Returns the value stored in a specific board cell.
     *
     * @param row row index of the cell.
     * @param col column index of the cell.
     * @return value stored in the specified cell.
     */
    @Override
    public int getCell(int row, int col) {
        return grid[row][col];
    }

    /**
     * Sets a value in a specific board cell and stores
     * the move in the move history.
     *
     * @param row row index of the cell.
     * @param col column index of the cell.
     * @param value value to assign to the cell.
     */
    @Override
    public void setCell(int row, int col, int value) {
        if (fixed[row][col]) return;
        int previous = grid[row][col];
        grid[row][col] = value;
        moveHistory.push(new SudokuMove(row, col, previous, value));
    }

    /**
     * Assigns a fixed value to a specific board cell.
     *
     * @param row row index of the cell.
     * @param col column index of the cell.
     * @param value fixed value assigned to the cell.
     */
    @Override
    public void setFixed(int row, int col, int value) {
        grid[row][col] = value;
        fixed[row][col] = true;
    }

    /**
     * Determines whether a specific cell is fixed.
     *
     * @param row row index of the cell.
     * @param col column index of the cell.
     * @return true if the cell is fixed, otherwise false.
     */
    @Override
    public boolean isFixed(int row, int col) {
        return fixed[row][col];
    }

    /**
     * Returns a copy of the current Sudoku grid.
     *
     * @return two-dimensional array containing a copy of the grid.
     */
    @Override
    public int[][] getGridCopy() {
        int[][] copy = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, SIZE);
        }
        return copy;
    }

    /**
     * Undoes the last move made on the board and restores
     * the previous value of the affected cell.
     *
     * @return the undone SudokuMove, or null if no moves exist.
     */
    public SudokuMove undoLastMove() {
        if (moveHistory.isEmpty()) return null;
        SudokuMove last = moveHistory.pop();
        grid[last.getRow()][last.getCol()] = last.getPreviousValue();
        return last;
    }

    /**
     * Determines whether there are moves available to undo.
     *
     * @return true if at least one move can be undone, otherwise false.
     */
    public boolean canUndo() {
        return !moveHistory.isEmpty();
    }

    /**
     * Resets all editable cells on the board and clears
     * the move history.
     */
    public void resetBoard() {
        moveHistory.clear();
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (!fixed[r][c]) grid[r][c] = 0;
            }
        }
        this.gameState = SudokuGameState.IN_PROGRESS;
    }

    /**
     * Sets the complete solution matrix for the Sudoku board.
     *
     * @param solution matrix containing the solved Sudoku board.
     */
    public void setSolution(int[][] solution) {
        this.solution = solution;
    }

    /**
     * Returns the solution value for a specific board cell.
     *
     * @param row row index of the cell.
     * @param col column index of the cell.
     * @return solution value stored in the specified position.
     */
    public int getSolutionValue(int row, int col) {
        return solution[row][col];
    }

    /**
     * Returns the current state of the Sudoku game.
     *
     * @return current game state.
     */
    @Override
    public SudokuGameState getGameState() { return gameState; }

    /**
     * Updates the current state of the Sudoku game.
     *
     * @param gameState new game state to assign.
     */
    @Override
    public void setGameState(SudokuGameState gameState) { this.gameState = gameState; }
}