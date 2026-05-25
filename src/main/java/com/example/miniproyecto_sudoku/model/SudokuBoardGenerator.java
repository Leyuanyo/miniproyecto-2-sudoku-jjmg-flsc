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
        boolean[] used = new boolean[SIZE + 1];

        for (int c = 0; c < SIZE; c++) used[grid[row][c]] = true;
        for (int r = 0; r < SIZE; r++) used[grid[r][col]] = true;

        int blockRowStart = (row / BLOCK_ROWS) * BLOCK_ROWS;
        int blockColStart = (col / BLOCK_COLS) * BLOCK_COLS;
        for (int r = blockRowStart; r < blockRowStart + BLOCK_ROWS; r++) {
            for (int c = blockColStart; c < blockColStart + BLOCK_COLS; c++) {
                used[grid[r][c]] = true;
            }
        }

        List<Integer> candidates = new ArrayList<>();
        for (int num = 1; num <= SIZE; num++) {
            if (!used[num]) candidates.add(num);
        }

        Collections.shuffle(candidates);
        return candidates;
    }

    private void placeFixedCells(SudokuBoard board, int[][] solvedGrid) {
        for (int blockRow = 0; blockRow < SIZE / BLOCK_ROWS; blockRow++) {
            for (int blockCol = 0; blockCol < SIZE / BLOCK_COLS; blockCol++) {

                List<int[]> cellsInBlock = new ArrayList<>();
                int rowStart = blockRow * BLOCK_ROWS;
                int colStart = blockCol * BLOCK_COLS;

                for (int r = rowStart; r < rowStart + BLOCK_ROWS; r++) {
                    for (int c = colStart; c < colStart + BLOCK_COLS; c++) {
                        cellsInBlock.add(new int[]{r, c});
                    }
                }

                Collections.shuffle(cellsInBlock);

                for (int i = 0; i < FIXED_PER_BLOCK; i++) {
                    int r = cellsInBlock.get(i)[0];
                    int c = cellsInBlock.get(i)[1];
                    board.setFixed(r, c, solvedGrid[r][c]);
                }
            }
        }
    }
}