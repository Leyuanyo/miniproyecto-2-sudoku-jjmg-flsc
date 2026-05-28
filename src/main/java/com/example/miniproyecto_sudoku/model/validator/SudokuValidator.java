package com.example.miniproyecto_sudoku.model.validator;

import com.example.miniproyecto_sudoku.model.SudokuGameState;
import com.example.miniproyecto_sudoku.model.board.ISudokuBoard;
import com.example.miniproyecto_sudoku.model.board.SudokuBoard;

import java.util.HashSet;
import java.util.Set;

/**
 * Class responsible for validating Sudoku rules and board states.
 * Provides methods for validating rows, columns, blocks,
 * moves, and determining the current game state.
 *
 * @author Juan José Morera Gómez
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 * @since 1.0
 */
public class SudokuValidator implements ISudokuValidator {

    /**
     * Sudoku board used for validation operations.
     */
    private final ISudokuBoard board;

    /**
     * Creates a new SudokuValidator associated with a board.
     *
     * @param board Sudoku board to validate.
     */
    public SudokuValidator(ISudokuBoard board) {
        this.board = board;
    }

    /**
     * Determines whether a specific row satisfies Sudoku rules.
     *
     * @param row row index to validate.
     * @return true if the row is valid, otherwise false.
     */
    @Override
    public boolean isRowValid(int row) {
        Set<Integer> seen = new HashSet<>();
        for (int col = 0; col < SudokuBoard.SIZE; col++) {
            int value = board.getCell(row, col);
            if (value != 0 && !seen.add(value)) return false;
        }
        return true;
    }

    /**
     * Determines whether a specific column satisfies Sudoku rules.
     *
     * @param col column index to validate.
     * @return true if the column is valid, otherwise false.
     */
    @Override
    public boolean isColumnValid(int col) {
        Set<Integer> seen = new HashSet<>();
        for (int row = 0; row < SudokuBoard.SIZE; row++) {
            int value = board.getCell(row, col);
            if (value != 0 && !seen.add(value)) return false;
        }
        return true;
    }

    /**
     * Determines whether a specific block satisfies Sudoku rules.
     *
     * @param row row index belonging to the block.
     * @param col column index belonging to the block.
     * @return true if the block is valid, otherwise false.
     */
    @Override
    public boolean isBlockValid(int row, int col) {
        Set<Integer> seen = new HashSet<>();
        int blockRowStart = (row / SudokuBoard.BLOCK_ROWS) * SudokuBoard.BLOCK_ROWS;
        int blockColStart = (col / SudokuBoard.BLOCK_COLS) * SudokuBoard.BLOCK_COLS;

        for (int r = blockRowStart; r < blockRowStart + SudokuBoard.BLOCK_ROWS; r++) {
            for (int c = blockColStart; c < blockColStart + SudokuBoard.BLOCK_COLS; c++) {
                int value = board.getCell(r, c);
                if (value != 0 && !seen.add(value)) return false;
            }
        }
        return true;
    }

    /**
     * Determines whether a move is valid according to Sudoku rules.
     *
     * @param row row index of the selected cell.
     * @param col column index of the selected cell.
     * @param value value to validate.
     * @return true if the move is valid, otherwise false.
     */
    @Override
    public boolean isMoveValid(int row, int col, int value) {
        if (value < 1 || value > SudokuBoard.SIZE) return false;
        if (board.isFixed(row, col)) return false;

        SudokuBoard concreteBoard = (SudokuBoard) board;
        int previous = board.getCell(row, col);
        concreteBoard.setCell(row, col, value);

        boolean valid = isRowValid(row)
                && isColumnValid(col)
                && isBlockValid(row, col);

        concreteBoard.setCell(row, col, previous);
        concreteBoard.undoLastMove();
        concreteBoard.undoLastMove();

        return valid;
    }

    /**
     * Determines whether the Sudoku board is completely solved
     * and satisfies all validation rules.
     *
     * @return true if the board is complete and valid, otherwise false.
     */
    @Override
    public boolean isBoardComplete() {
        for (int row = 0; row < SudokuBoard.SIZE; row++) {
            for (int col = 0; col < SudokuBoard.SIZE; col++) {
                if (board.getCell(row, col) == 0) return false;
            }
        }
        for (int i = 0; i < SudokuBoard.SIZE; i++) {
            if (!isRowValid(i) || !isColumnValid(i)) return false;
        }
        for (int blockRow = 0; blockRow < SudokuBoard.SIZE / SudokuBoard.BLOCK_ROWS; blockRow++) {
            for (int blockCol = 0; blockCol < SudokuBoard.SIZE / SudokuBoard.BLOCK_COLS; blockCol++) {
                if (!isBlockValid(
                        blockRow * SudokuBoard.BLOCK_ROWS,
                        blockCol * SudokuBoard.BLOCK_COLS)) return false;
            }
        }
        return true;
    }

    /**
     * Evaluates and updates the current state of the Sudoku game.
     *
     * @return current SudokuGameState after evaluation.
     */
    public SudokuGameState evaluateGameState() {
        if (isBoardComplete()) {
            board.setGameState(SudokuGameState.COMPLETED);
            return SudokuGameState.COMPLETED;
        }
        for (int row = 0; row < SudokuBoard.SIZE; row++) {
            for (int col = 0; col < SudokuBoard.SIZE; col++) {
                int value = board.getCell(row, col);
                if (value != 0) {
                    if (!isRowValid(row)
                            || !isColumnValid(col)
                            || !isBlockValid(row, col)) {
                        board.setGameState(SudokuGameState.INVALID);
                        return SudokuGameState.INVALID;
                    }
                }
            }
        }
        board.setGameState(SudokuGameState.IN_PROGRESS);
        return SudokuGameState.IN_PROGRESS;
    }
}