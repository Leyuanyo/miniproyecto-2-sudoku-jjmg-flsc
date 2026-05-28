package com.example.miniproyecto_sudoku.model.generator;

import com.example.miniproyecto_sudoku.model.board.ISudokuBoardState;
import com.example.miniproyecto_sudoku.model.board.SudokuBoard;
import com.example.miniproyecto_sudoku.model.board.SudokuBoardState;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * Class responsible for generating valid Sudoku boards.
 * Creates solved Sudoku grids and assigns fixed cells
 * to initialize playable game boards.
 *
 * @author Juan José Morera Gómez
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 * @since 1.0
 */
public class SudokuBoardGenerator implements ISudokuBoardGenerator {

    /**
     * Size of the Sudoku board.
     */
    private static final int SIZE = SudokuBoard.SIZE;

    /**
     * Number of rows per Sudoku block.
     */
    private static final int BLOCK_ROWS = SudokuBoard.BLOCK_ROWS;

    /**
     * Number of columns per Sudoku block.
     */
    private static final int BLOCK_COLS = SudokuBoard.BLOCK_COLS;

    /**
     * Number of fixed cells assigned per block.
     */
    private static final int FIXED_PER_BLOCK = 2;

    /**
     * Generates a new Sudoku board with a valid solution
     * and predefined fixed cells.
     *
     * @return generated SudokuBoard instance.
     */
    @Override
    public SudokuBoard generateBoard() {
        int[][] solvedGrid = buildSolvedGrid();
        SudokuBoard board = new SudokuBoard();
        board.setSolution(solvedGrid);
        placeFixedCells(board, solvedGrid);
        return board;
    }

    /**
     * Builds a fully solved Sudoku grid using
     * an iterative backtracking approach.
     *
     * @return solved Sudoku grid.
     */
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

    /**
     * Creates a deep copy of a Sudoku grid.
     *
     * @param original original grid to copy.
     * @return copied grid.
     */
    private int[][] deepCopy(int[][] original) {
        int[][] copy = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, SIZE);
        }
        return copy;
    }

    /**
     * Generates a shuffled list of valid candidate numbers
     * for a specific cell position.
     *
     * @param grid current Sudoku grid.
     * @param row row index of the cell.
     * @param col column index of the cell.
     * @return shuffled list of valid candidate numbers.
     */
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

    /**
     * Places fixed cells into the generated Sudoku board
     * using values from the solved grid.
     *
     * @param board Sudoku board where fixed cells will be assigned.
     * @param solvedGrid solved Sudoku grid containing valid values.
     */
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