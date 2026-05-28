package com.example.miniproyecto_sudoku.model.generator;

import com.example.miniproyecto_sudoku.model.board.SudokuBoard;

/**
 * Interface responsible for generating Sudoku boards.
 * Defines the operation required to create and return
 * a new Sudoku board instance.
 *
 * @author Juan José Morera Gómez
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 * @since 1.0
 */
public interface ISudokuBoardGenerator {

    /**
     * Generates and returns a new Sudoku board.
     *
     * @return generated SudokuBoard instance.
     */
    SudokuBoard generateBoard();
}