package com.example.miniproyecto_sudoku.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class SudokuBoardGenerator implements ISudokuBoardGenerator {

    private static final int SIZE = SudokuBoard.SIZE;
    private static final int BLOCK_ROWS = SudokuBoard.BLOCK_ROWS;
    private static final int BLOCK_COLS = SudokuBoard.BLOCK_COLS;
    private static final int FIXED_PER_BLOCK = 2;

    @Override
    public SudokuBoard generateBoard() {
        int[][] solvedGrid = buildSolvedGrid();
        SudokuBoard board = new SudokuBoard();
        placeFixedCells(board, solvedGrid);
        return board;
    }

    private int[][] buildSolvedGrid() {
        int[][] grid = new int[SIZE][SIZE];
        Deque<ISudokuBoardState> stack = new ArrayDeque<>();
        stack.push(new SudokuBoardState(grid, 0));

        while (!stack.isEmpty()) {
            ISudokuBoardState current = stack.pop();
            int position = current.getPosition();

            if (position == SIZE * SIZE) {
                return current.getGrid();
            }

            int row = position / SIZE;
            int col = position % SIZE;
            int[][] currentGrid = current.getGrid();

            if (currentGrid[row][col] != 0) {
                stack.push(new SudokuBoardState(currentGrid, position + 1));
                continue;
            }

            List<Integer> candidates = getShuffledCandidates(currentGrid, row, col);
            for (int candidate : candidates) {
                int[][] nextGrid = deepCopy(currentGrid);
                nextGrid[row][col] = candidate;
                stack.push(new SudokuBoardState(nextGrid, position + 1));
            }
        }

        return grid;
    }

    private int[][] deepCopy(int[][] original) {
        int[][] copy = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, SIZE);
        }
        return copy;
    }

    private List<Integer> getShuffledCandidates(int[][] grid, int row, int col) {
        return new ArrayList<>();
    }

    private void placeFixedCells(SudokuBoard board, int[][] solvedGrid) {
    }
}