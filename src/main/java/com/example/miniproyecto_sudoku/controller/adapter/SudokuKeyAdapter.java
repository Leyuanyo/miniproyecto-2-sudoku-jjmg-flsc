package com.example.miniproyecto_sudoku.controller.adapter;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class SudokuKeyAdapter implements EventHandler<KeyEvent>, ISudokuCellInputHandler {

    private int selectedRow;
    private int selectedCol;
    private TriConsumer onInput;
    private java.util.function.BiConsumer<Integer, Integer> onClear;

    @FunctionalInterface
    public interface TriConsumer {
        void accept(int row, int col, int value);
    }

    public SudokuKeyAdapter() {
        this.selectedRow = -1;
        this.selectedCol = -1;
    }

    public void setSelectedCell(int row, int col) {
        this.selectedRow = row;
        this.selectedCol = col;
    }

    public void setOnInput(TriConsumer onInput) {
        this.onInput = onInput;
    }

    public void setOnClear(java.util.function.BiConsumer<Integer, Integer> onClear) {
        this.onClear = onClear;
    }

    @Override
    public void handle(KeyEvent event) {
        if (selectedRow == -1 || selectedCol == -1) return;

        String key = event.getText();

        switch (event.getCode()) {
            case BACK_SPACE:
            case DELETE:
                handleClear(selectedRow, selectedCol);
                break;
            default:
                if (key.matches("[1-6]")) {
                    handleInput(selectedRow, selectedCol, Integer.parseInt(key));
                }
                break;
        }
    }

    @Override
    public void handleInput(int row, int col, int value) {
        if (onInput != null) {
            onInput.accept(row, col, value);
        }
    }

    @Override
    public void handleClear(int row, int col) {
        if (onClear != null) {
            onClear.accept(row, col);
        }
    }
}