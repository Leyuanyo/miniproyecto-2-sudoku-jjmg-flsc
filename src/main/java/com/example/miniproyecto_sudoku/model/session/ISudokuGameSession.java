package com.example.miniproyecto_sudoku.model.session;

import com.example.miniproyecto_sudoku.model.board.SudokuBoard;

public interface ISudokuGameSession {

    void saveSession(SudokuBoard board);

    SudokuBoard loadSession();

    boolean hasSavedSession();

    void clearSession();
}