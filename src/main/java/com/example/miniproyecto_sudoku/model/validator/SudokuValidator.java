package com.example.miniproyecto_sudoku.model.validator;

import com.example.miniproyecto_sudoku.model.SudokuGameState;
import com.example.miniproyecto_sudoku.model.board.ISudokuBoard;
import com.example.miniproyecto_sudoku.model.board.SudokuBoard;

import java.util.HashSet;
import java.util.Set;

public class SudokuValidator implements ISudokuValidator {

    private final ISudokuBoard board;

    public SudokuValidator(ISudokuBoard board) {
        this.board = board;
    }

    @Override
    public boolean isRowValid(int row) {
        Set<Integer> seen = new HashSet<>();
        for (int col = 0; col < SudokuBoard.SIZE; col++) {
            int value = board.getCell(row, col);
            if (value != 0 && !seen.add(value)) return false;
        }
        return true;
    }

    @Override
    public boolean isColumnValid(int col) {
        Set<Integer> seen = new HashSet<>();
        for (int row = 0; row < SudokuBoard.SIZE; row++) {
            int value = board.getCell(row, col);
            if (value != 0 && !seen.add(value)) return false;
        }
        return true;
    }

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